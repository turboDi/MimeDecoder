package org.turbodi.mimedecoder.handler;

import org.turbodi.mimedecoder.MimeDecoderContext;
import org.turbodi.mimedecoder.processor.IMimePartProcessor;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Multipart;

/**
* @author borisov
* @since 20.12.2012
*/
public interface IMultipartHandler
{
    void handle(Multipart mp, MimeDecoderContext context, IMimePartProcessor processor) throws MessagingException, IOException;
    
    String getDefaultPartContentType();
}
