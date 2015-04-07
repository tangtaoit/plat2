package com.tt.plat.comp.fileupload2;

import org.apache.wicket.util.upload.FileItem;

import java.io.IOException;

/**
 * Created by tangtao on 2014/9/17.
 * Desc:
 */
public interface IFileManager {


    /**
     * 请求上下文
     * @return
     */
    public String getContext();
    /**
     * 保存文件
     * @param fileItem
     * @return
     */
    public String save(FileItem fileItem) throws IOException;

    /**
     * 获取文件
     * @param fileSign 文件标识
     * @return
     * @throws IOException
     */
    public byte[] get(String fileSign) throws IOException;

    /**
     * 删除文件
     * @param fileSign
     * @return
     */
    public boolean delete(String fileSign);
}
