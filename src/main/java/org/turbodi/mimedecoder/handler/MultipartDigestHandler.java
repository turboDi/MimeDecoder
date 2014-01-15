package org.turbodi.mimedecoder.handler;

/**
* This type is syntactically identical to <code>"multipart/mixed"</code>, but the semantics are different.
* <p>In particular, in a digest, the default <code>Content-Type</code> value for a body part is changed from "text/plain" to "message/rfc822".</p>
* @see <a href="http://tools.ietf.org/html/rfc2046#section-5.1.5">RFC 2046, Section 5.1.5</a>
*
* @author borisov
* @since 20.12.2012
*/
public class MultipartDigestHandler extends MultipartHandlerBase
{
    public static final String TYPE = "multipart/digest";
    
    @Override
    public String getDefaultPartContentType()
    {
        return "message/rfc822";
    }

}
