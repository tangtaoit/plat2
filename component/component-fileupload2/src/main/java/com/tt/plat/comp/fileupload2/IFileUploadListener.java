package com.tt.plat.comp.fileupload2;

import org.apache.wicket.util.upload.FileItem;

import java.util.List;

/**
 * Created by tangtao on 2014/9/18.
 * Desc:文件上传监听器
 */
public interface IFileUploadListener {

    public void upload(List<FileItem> fileItems);

}
