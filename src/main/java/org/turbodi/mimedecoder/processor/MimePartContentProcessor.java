package org.turbodi.mimedecoder.processor;

import org.turbodi.mimedecoder.MimeDecoderContext;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Part;

/**
* @author borisov
* @since 21.12.2012
*/
public class MimePartContentProcessor implements IMimePartProcessor
{
    @Override
    public void process(Part part, String contentType, MimeDecoderContext context) throws IOException, MessagingException
    {
        Object content = part.getContent();
        if (content instanceof String)
        {
            context.getText().append((String)content).append("\n");
        }
    }

}
