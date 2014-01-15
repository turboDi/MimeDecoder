/* 
 * $Id$
 */
package org.turbodi.mimedecoder;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * FIXME: Use mime types from the servlet container?
 * @author borisov
 */
public class MimeTypeRegistry
{
    public static final String PLAIN = "text/plain";
    public static final String HTML = "text/html";
    public static final String RFC822 = "message/rfc822";
    
    private static final Map<String, String> MIME_TYPE_2_FILE_EXTENSION = new HashMap<String, String>();
    private static final Map<String, String> FILE_EXTENSION_2_MIME_TYPE = new HashMap<String, String>();

    static
    {
        MIME_TYPE_2_FILE_EXTENSION.put("text/plain", "txt");
        MIME_TYPE_2_FILE_EXTENSION.put(HTML, "html");
        MIME_TYPE_2_FILE_EXTENSION.put("message/rfc822", "eml");
        MIME_TYPE_2_FILE_EXTENSION.put("application/zip", "zip");
        MIME_TYPE_2_FILE_EXTENSION.put("application/msword", "doc");
        MIME_TYPE_2_FILE_EXTENSION.put("application/vnd.ms-excel", "xls");

        FILE_EXTENSION_2_MIME_TYPE.put("txt", PLAIN);
        FILE_EXTENSION_2_MIME_TYPE.put("html", HTML);
        FILE_EXTENSION_2_MIME_TYPE.put("htm", HTML);
        FILE_EXTENSION_2_MIME_TYPE.put("css", "text/css");        
        FILE_EXTENSION_2_MIME_TYPE.put("eml", "message/rfc822");
        FILE_EXTENSION_2_MIME_TYPE.put("zip", "application/zip");
        FILE_EXTENSION_2_MIME_TYPE.put("gz", "application/x-gzip");
        FILE_EXTENSION_2_MIME_TYPE.put("pdf", "application/pdf");
        FILE_EXTENSION_2_MIME_TYPE.put("rtf", "application/rtf");
        FILE_EXTENSION_2_MIME_TYPE.put("doc", "application/msword");
        FILE_EXTENSION_2_MIME_TYPE.put("dot", "application/msword");
        FILE_EXTENSION_2_MIME_TYPE.put("xls", "application/vnd.ms-excel");
        FILE_EXTENSION_2_MIME_TYPE.put("ppt", "application/vnd.ms-powerpoint");
        FILE_EXTENSION_2_MIME_TYPE.put("jpg", "image/jpeg");
        FILE_EXTENSION_2_MIME_TYPE.put("jpeg", "image/jpeg");
        FILE_EXTENSION_2_MIME_TYPE.put("jpe", "image/jpeg");
        FILE_EXTENSION_2_MIME_TYPE.put("png", "image/png");
        FILE_EXTENSION_2_MIME_TYPE.put("gif", "image/gif");
        FILE_EXTENSION_2_MIME_TYPE.put("tif", "image/tiff");
        FILE_EXTENSION_2_MIME_TYPE.put("tiff", "image/tiff");
        FILE_EXTENSION_2_MIME_TYPE.put("bmp", "image/bmp");
    }

    public static String getMimeTypeByFileExtension(String fileExtension)
    {
        String res = null;
        if (!StringUtils.isBlank(fileExtension))
        {
            res = FILE_EXTENSION_2_MIME_TYPE.get(fileExtension.toLowerCase().trim());
        }
        return (res == null ? HTML : res);
    }
    
    public static String getFileExtensionByMimeType(String type)
    {
        String res = null;
        if (!StringUtils.isBlank(type))
        {
            res = MIME_TYPE_2_FILE_EXTENSION.get(type.toLowerCase().trim());
        }
        return res;
    }
}
