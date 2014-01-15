package org.turbodi.mimedecoder.handler;

import org.turbodi.mimedecoder.MimeDecoderContext;
import org.turbodi.mimedecoder.processor.IMimePartProcessor;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

/**
* @author borisov
* @since 20.12.2012
*/
public abstract class MultipartHandlerBase implements IMultipartHandler
{
    @Override
    public void handle(Multipart mp, MimeDecoderContext context, IMimePartProcessor processor) throws MessagingException, IOException
    {
        for (int i = 0; i < mp.getCount(); ++i)
        {
            handlePart(mp.getBodyPart(i), context, processor);
        }
    }

    protected void handlePart(Part part, MimeDecoderContext context, IMimePartProcessor processor) throws MessagingException, IOException
    {
        context.getTraverser().traverseParts(part, getPartContentType(part), context, processor);
    }

    public String getPartContentType(Part part) throws MessagingException
    {
        return part.getContentType() != null ? part.getContentType() : getDefaultPartContentType();
    }
}
