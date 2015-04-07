package com.tt.plat.comp.fileupload2;

import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.upload.FileItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;


/**
 * A simple file manager that knows how to store, read and delete files
 * from the file system.
 */
public class FileManager implements IFileManager
{
    private  Folder baseFolder;

    public FileManager( String basePath)
    {
        this.baseFolder = new Folder(basePath);
        if(!baseFolder.exists()){
            baseFolder.mkdirs();
        }
    }


    @Override
    public String getContext() {
        return "/";
    }

    public String save(FileItem fileItem) throws IOException
    {


        File file = new File(baseFolder, getRandom());
        FileOutputStream fileOS = new FileOutputStream(file, false);
         IOUtils.copy(fileItem.getInputStream(), fileOS);

        return file.getName();
    }

    private String getRandom(){

        return  UUID.randomUUID().toString();
    }

    public byte[] get(String fileSign) throws IOException {
        File file = new File(baseFolder, fileSign);
        return IOUtils.toByteArray(new FileInputStream(file));
    }

    public boolean delete(String fileSign)
    {
        File file = new File(baseFolder, fileSign);
        return Files.remove(file);
    }
}
