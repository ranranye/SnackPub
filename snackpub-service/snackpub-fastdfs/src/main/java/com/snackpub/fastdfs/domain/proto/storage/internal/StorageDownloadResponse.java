package com.snackpub.fastdfs.domain.proto.storage.internal;

import com.snackpub.fastdfs.domain.proto.FdfsResponse;
import com.snackpub.fastdfs.domain.proto.storage.DownloadCallback;
import com.snackpub.fastdfs.domain.proto.storage.FdfsInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 文件下载结果
 *
 * @param <T>
 * @author tobato
 */
public class StorageDownloadResponse<T> extends FdfsResponse<T> {

    private DownloadCallback<T> callback;

    public StorageDownloadResponse(DownloadCallback<T> callback) {
        super();
        this.callback = callback;
    }

    /**
     * 解析反馈内容
     */
    @Override
    public T decodeContent(InputStream in, Charset charset) throws IOException {
        // 解析报文内容
        FdfsInputStream input = new FdfsInputStream(in, getContentLength());
        return callback.recv(input);
    }

}
