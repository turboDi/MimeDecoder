package org.turbodi.mimedecoder.handler;

import org.turbodi.mimedecoder.MimeTypeRegistry;

/**
* The <code>"mixed"</code> subtype of <code>"multipart"</code> is intended for use when the body parts are independent and need to be bundled in a particular order.
* @see <a href="http://tools.ietf.org/html/rfc2046#section-5.1.3">RFC 2046, Section 5.1.3</a>
* 
* @author borisov
* @since 20.12.2012
*/
public class MultipartMixedHandler extends MultipartHandlerBase
{
    public static final String TYPE = "multipart/mixed";

    @Override
    public String getDefaultPartContentType()
    {
        return MimeTypeRegistry.PLAIN;
    }

}
