package com.tt.plat.comp.fileupload2;

import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.servlet.MultipartServletWebRequest;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.upload.FileItem;
import org.apache.wicket.util.upload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The resource that handles the file uploads.
 * Reads the file items from the request parameters and uses FileManager
 * to store them.
 * Additionally cares about the response's content type and body.
 */
public abstract class AbstractFileUploadResource extends AbstractResource
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractFileUploadResource.class);

    private final IFileManager fileManager;


    public AbstractFileUploadResource(IFileManager fileManager)
    {
        this.fileManager = fileManager;
    }

    /**
     * Reads and stores the uploaded files
     * @param attributes
     * @return
     */
    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes)
    {
        final ResourceResponse resourceResponse = new ResourceResponse();

        final ServletWebRequest webRequest = (ServletWebRequest) attributes.getRequest();

        try
        {

            MultipartServletWebRequest multiPartRequest = webRequest.newMultipartWebRequest(getMaxSize(), "ignored");

            multiPartRequest.parseFileParts();

            Map<String, List<FileItem>> files = multiPartRequest.getFiles();
            List<FileItem> fileItems = files.get(FileUploadBehavior.PARAM_NAME);

            List<String> fileSigns = saveFiles(fileItems);

            prepareResponse(resourceResponse, webRequest, fileItems,fileSigns);
        }
        catch (Exception fux)
        {
            LOG.error("An error occurred while uploading a file", fux);
            throw new AbortWithHttpErrorCodeException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, fux.getMessage());
        }

        return resourceResponse;
    }

    /**
     * Sets the response's content type and body
     * @param resourceResponse
     * @param webRequest
     * @param fileItems
     * @throws FileUploadException
     * @throws java.io.IOException
     */
    protected void prepareResponse(ResourceResponse resourceResponse, ServletWebRequest webRequest, List<FileItem> fileItems,List<String> fileSigns)
            throws FileUploadException, IOException {

        final String responseContent;
        String accept = webRequest.getHeader("Accept");

        if (wantsHtml(accept))
        {
            // Internet Explorer
            resourceResponse.setContentType("text/html");

            responseContent = generateHtmlResponse(resourceResponse, webRequest, fileItems,fileSigns);
        }
        else
        {
            // a real browser
            resourceResponse.setContentType("application/json");

            responseContent = generateJsonResponse(resourceResponse, webRequest, fileItems,fileSigns);
        }

        resourceResponse.setWriteCallback(new WriteCallback() {
            @Override
            public void writeData(Attributes attributes) throws IOException {
                attributes.getResponse().write(responseContent);
            }
        });
    }

    /**
     * Delegates to FileManager to store the uploaded files
     * @param fileItems
     * @throws java.io.IOException
     */
    protected  List<String> saveFiles(List<FileItem> fileItems) throws IOException {
        List<String> fileSignList = new ArrayList<>();
        for (FileItem fileItem : fileItems)
        {
            fileSignList.add(fileManager.save(fileItem));
        }

        return fileSignList;
    }

    /**
     * Decides what should be the response's content type depending on the 'Accept' request header.
     * HTML5 browsers work with "application/json", older ones use IFrame to make the upload and the
     * response should be HTML.
     * Read http://blueimp.github.com/jQuery-File-Upload/ docs for more info.
     * @param acceptHeader
     * @return
     */
    protected boolean wantsHtml(String acceptHeader)
    {
        return !Strings.isEmpty(acceptHeader) && acceptHeader.contains("text/html");
    }

    /**
     * Defines what is the maximum size of the uploaded files.
     * TODO: integrate this in FileUploadBehavior to set the max size at the client side too
     * @return
     */
    protected Bytes getMaxSize()
    {
        return Bytes.megabytes(100);
    }
    /**
     * Should generate the response's body in JSON format
     *
     * @param resourceResponse
     * @param webRequest
     * @param files
     * @return
     */
    protected abstract String generateJsonResponse(ResourceResponse resourceResponse,
                                                 ServletWebRequest webRequest, List<FileItem> files,List<String> fileSigns);

    /**
     * Should generate the response's body in HTML format
     * @param resourceResponse
     * @param webRequest
     * @param files
     * @return
     */
    protected abstract String generateHtmlResponse(ResourceResponse resourceResponse,
                                                 ServletWebRequest webRequest, List<FileItem> files,List<String> fileSigns);

}