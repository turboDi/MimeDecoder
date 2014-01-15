package org.turbodi.mimedecoder.processor;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Part;

import org.turbodi.mimedecoder.MimeDecoderContext;
import org.turbodi.mimedecoder.MimeTypeRegistry;
import org.turbodi.mimedecoder.MimeUtils;

/**
* @author borisov
* @since 21.12.2012
*/
public class MimePartContentTypeProcessor implements IMimePartProcessor
{
    @Override
    public void process(Part part, String contentType, MimeDecoderContext context) throws IOException, MessagingException
    {
        // XXX: if at least 1 part with text/html content type found, whole message will have this content type
        if (MimeUtils.isHtml(contentType))
        {
            context.setContentType(MimeTypeRegistry.HTML);
        }
    }

}
