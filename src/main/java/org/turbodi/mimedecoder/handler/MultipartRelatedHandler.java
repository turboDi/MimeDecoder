package org.turbodi.mimedecoder.handler;

import org.turbodi.mimedecoder.MimeTypeRegistry;

/**
* The <code>Multipart/Related</code> content-type provides a common mechanism for representing objects that are aggregates of related MIME body parts.
* @see <a href="http://tools.ietf.org/html/rfc2387">RFC 2387</a>
*
* @author borisov
* @since 20.12.2012
*/
public class MultipartRelatedHandler extends MultipartHandlerBase
{
    public static final String TYPE = "multipart/related";
    
    @Override
    public String getDefaultPartContentType()
    {
        return MimeTypeRegistry.PLAIN;
    }

}
