/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.snackpub.system.wrapper;

import com.snackpub.common.constant.CommonConstant;
import com.snackpub.core.mp.support.BaseEntityWrapper;
import com.snackpub.core.tools.node.ForestNodeMerger;
import com.snackpub.core.tools.node.INode;
import com.snackpub.core.tools.utils.BeanUtil;
import com.snackpub.core.tools.utils.Func;
import com.snackpub.core.tools.utils.SpringUtil;
import com.snackpub.system.entity.Dept;
import com.snackpub.system.vo.DeptVO;
import com.snackpub.system.service.IDeptService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class DeptWrapper extends BaseEntityWrapper<Dept, DeptVO> {

	private static IDeptService deptService;

	static {
		deptService = SpringUtil.getBean(IDeptService.class);
	}

	public static DeptWrapper build() {
		return new DeptWrapper();
	}

	@Override
	public DeptVO entityVO(Dept dept) {
		DeptVO deptVO = BeanUtil.copy(dept, DeptVO.class);
		if (Func.equals(dept.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			deptVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Dept parent = deptService.getById(dept.getParentId());
			deptVO.setParentName(parent.getDeptName());
		}
		return deptVO;
	}

	public List<INode> listNodeVO(List<Dept> list) {
		List<INode> collect = list.stream().map(dept -> BeanUtil.copy(dept, DeptVO.class)).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
