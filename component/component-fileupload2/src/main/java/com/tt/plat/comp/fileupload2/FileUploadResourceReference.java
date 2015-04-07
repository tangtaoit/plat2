package com.tt.plat.comp.fileupload2;


import org.apache.wicket.ajax.json.JSONArray;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.upload.FileItem;

import java.util.List;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;


/**
 * A resource reference provides default implementation of AbstractFileUploadResource.
 * The implementation generates JSON response as expected by the demo at
 * <a href="http://blueimp.github.com/jQuery-File-Upload/">http://blueimp.github.com/jQuery-File-Upload</a>
 */
public class FileUploadResourceReference extends ResourceReference
{

    private  IFileManager fileManager;


    public FileUploadResourceReference(){
        super(FileUploadResourceReference.class, "file-upload");

    }

    public FileUploadResourceReference(IFileManager fileManager)
    {
        super(FileUploadResourceReference.class, "file-upload");

        this.fileManager = fileManager;
    }

    @Override
    public IResource getResource()
    {
        return new AbstractFileUploadResource(fileManager)
        {
            @Override
            protected String generateJsonResponse(ResourceResponse resourceResponse, ServletWebRequest webRequest, List<FileItem> files,List<String> fileSigns) {
                JSONArray json = new JSONArray();


                String managerUrl=webRequest.getQueryParameters().getParameterValue("managerUrl").toString(null);
                managerUrl=fileManager.getContext()+managerUrl;
                for (int i=0;i<files.size();i++)
                {
                    FileItem fileItem = files.get(i);
                    String name=fileSigns.get(i);
                    JSONObject fileJson = new JSONObject();

                    try {
                        fileJson.put("name", name);
                        fileJson.put("url", getViewUrl(name,managerUrl));
                        fileJson.put("thumbnail_url", getViewUrl(name,managerUrl));
                        fileJson.put("size", fileItem.getSize());
                        fileJson.put("delete_type", "POST");
                        fileJson.put("delete_url", getDeleteUrl(name,managerUrl));
                    } catch (JSONException e) {
                        try {
                            fileJson.put("error", e.getMessage());
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }

                    json.put(fileJson);
                }

                return json.toString();
            }

            @Override
            protected String generateHtmlResponse(ResourceResponse resourceResponse, ServletWebRequest webRequest, List<FileItem> files,List<String> fileSigns)
            {
                String jsonResponse = generateJsonResponse(resourceResponse, webRequest, files,fileSigns);
                String escapedJson = escapeHtml(jsonResponse);
                return escapedJson;
            }
        };
    }

    private CharSequence getViewUrl(String name,String managerUrl) {
        PageParameters params = new PageParameters();
        params.set("filename", name);


        CharSequence url =managerUrl+"?filename="+name;
//       String img_show_url  =ConfigUtil.getImgShowUrl();
//        CharSequence url =img_show_url+name;




        return url;
    }


    private CharSequence getDeleteUrl(String name,String managerUrl) {
        PageParameters params = new PageParameters();
        params.set("filename", name);
        params.set("delete", true);

        CharSequence url =managerUrl+"?filename="+name+"&delete=true";

        return url;
    }

}
