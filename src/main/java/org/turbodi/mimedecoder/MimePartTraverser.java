package org.turbodi.mimedecoder;

import org.turbodi.mimedecoder.handler.IMultipartHandler;
import org.turbodi.mimedecoder.handler.MultipartHandlerRegistry;
import org.turbodi.mimedecoder.processor.IMimePartProcessor;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.ContentType;

/**
* @author borisov
* @since 20.12.2012
*/
public class MimePartTraverser
{
    public void traverseParts(Part part, String contentType, MimeDecoderContext context, IMimePartProcessor processor) throws MessagingException, IOException
    {
        Object content = part.getContent();
        if (content instanceof Multipart)
        {
            ContentType type = new ContentType(contentType);
            IMultipartHandler mpHandler = MultipartHandlerRegistry.getMultipartHandlerByType(type.getBaseType());
            mpHandler.handle((Multipart)content, context, processor);
        }
        else
        {
            processor.process(part, contentType, context);
        }
    }
}
