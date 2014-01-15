package org.turbodi.mimedecoder.handler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

import org.turbodi.mimedecoder.MimeDecoderContext;
import org.turbodi.mimedecoder.MimeTypeRegistry;
import org.turbodi.mimedecoder.MimeUtils;
import org.turbodi.mimedecoder.processor.IMimePartProcessor;

/**
* The <code>multipart/alternative</code> type is syntactically identical to <code>multipart/mixed</code>, but the semantics are different.
* <p>In particular, each of the parts is an "alternative" version of the same information.</p>
* @see <a href="http://tools.ietf.org/html/rfc2046#section-5.1.4">RFC 2046, Section 5.1.4</a>
* 
* @author borisov
* @since 20.12.2012
*/
public class MultipartAlternativeHandler extends MultipartHandlerBase
{
    public static final String TYPE = "multipart/alternative";
    private static final List<String> SUPPORTED_TYPES = Arrays.asList(MimeTypeRegistry.HTML, "multipart/*", MimeTypeRegistry.PLAIN);

    @Override
    public String getDefaultPartContentType()
    {
        return MimeTypeRegistry.PLAIN;
    }

    @Override
    public void handle(Multipart mp, MimeDecoderContext context, IMimePartProcessor processor) throws MessagingException, IOException
    {
        //The alternatives appear in an order of increasing faithfulness to the original content.
        //The best choice is the LAST part of a type supported by the recipient system's local environment.
        for (int i = mp.getCount() - 1; i >= 0; --i)
        {
            Part p = mp.getBodyPart(i);
            for (String type : SUPPORTED_TYPES)
            {
                if (MimeUtils.isMimeType(getPartContentType(p), type))
                {
                    handlePart(p, context, processor);
                    return;
                }
            }
        }
    }
}
