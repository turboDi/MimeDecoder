package org.turbodi.mimedecoder.processor;

import org.turbodi.mimedecoder.MimeDecoderContext;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Part;

/**
* @author borisov
* @since 20.12.2012
*/
public interface IMimePartProcessor
{
    void process(Part part, String contentType, MimeDecoderContext context) throws IOException, MessagingException;
}
