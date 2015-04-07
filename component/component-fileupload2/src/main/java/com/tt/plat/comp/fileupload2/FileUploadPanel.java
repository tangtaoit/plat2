package com.tt.plat.comp.fileupload2;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A panel that combines all the parts of the file uploader
 */
public class FileUploadPanel extends Panel {

    private String basePath;

    private IFileManager fileManager;




    public FileUploadPanel(String id,String basePath){
       this(id,new FileManager(basePath));
        this.basePath=basePath;

    }

    public FileUploadPanel(String id,IFileManager fileManager){
        super(id,new Model<Serializable>(new UploadResult()));


        if(fileManager==null){
            this.fileManager = new FileManager(basePath);
        }
        this.fileManager = fileManager;

        // The buttons toolbar. Mandatory
        FileUploadBar fileUpload = new FileUploadBar("fileUpload",fileManager);
        add(fileUpload);

        // The gallery that can be used to view the uploaded files
        // Optional
        FileUploadGallery preview = new FileUploadGallery("preview");
        add(preview);

        // The template used by jquery.fileupload-ui.js to show the files
        // scheduled for upload (i.e. the added files).
        // Optional
        FileUploadTemplate uploadTemplate = new FileUploadTemplate("uploadTemplate");
        add(uploadTemplate);

        // The template used by jquery.fileupload-ui.js to show the uploaded files
        // Optional
        FileDownloadTemplate downloadTemplate = new FileDownloadTemplate("downloadTemplate");
        add(downloadTemplate);
    }
    public FileUploadPanel(String id) {
        this(id, "/opt/images");

    }
}
