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
package com.snackpub.core.log.feign;

import com.snackpub.core.log.model.LogApi;
import com.snackpub.core.log.model.LogError;
import com.snackpub.core.log.model.LogUsual;
import com.snackpub.core.log.service.ILogApiService;
import com.snackpub.core.log.service.ILogErrorService;
import com.snackpub.core.log.service.ILogUsualService;
import com.snackpub.core.tools.api.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志服务Feign实现类
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
public class LogClient implements ILogClient {

	ILogUsualService usualLogService;

	ILogApiService apiLogService;

	ILogErrorService errorLogService;

	@Override
	@PostMapping(API_PREFIX + "/saveUsualLog")
	public R<Boolean> saveUsualLog(@RequestBody LogUsual log) {
		log.setParams(log.getParams().replace("&amp;", "&"));
		return R.data(usualLogService.save(log));
	}

	@Override
	@PostMapping(API_PREFIX + "/saveApiLog")
	public R<Boolean> saveApiLog(@RequestBody LogApi log) {
		log.setParams(log.getParams().replace("&amp;", "&"));
		return R.data(apiLogService.save(log));
	}

	@Override
	@PostMapping(API_PREFIX + "/saveErrorLog")
	public R<Boolean> saveErrorLog(@RequestBody LogError log) {
		log.setParams(log.getParams().replace("&amp;", "&"));
		return R.data(errorLogService.save(log));
	}
}
