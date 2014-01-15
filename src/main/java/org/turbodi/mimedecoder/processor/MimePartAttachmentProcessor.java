package org.turbodi.mimedecoder.processor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang3.StringUtils;

import org.turbodi.mimedecoder.Attachment;
import org.turbodi.mimedecoder.MimeDecoderContext;
import org.turbodi.mimedecoder.MimeUtils;

/**
* @author borisov
* @since 20.12.2012
*/
public class MimePartAttachmentProcessor implements IMimePartProcessor
{
    public static String DEFAULT_FILENAME = "Part";
    
    @Override
    public void process(Part part, String contentType, MimeDecoderContext context) throws IOException, MessagingException
    {
        Object content = part.getContent();
        String disposition = part.getDisposition();
        boolean isInline = Part.INLINE.equalsIgnoreCase(disposition);
        boolean isAttachment = Part.ATTACHMENT.equalsIgnoreCase(disposition);

        if (isAttachment || isInline//
                || (StringUtils.isEmpty(disposition) && part.isMimeType("message/*"))//attach message with empty Content-Disposition
                || !(content instanceof String))
        {
            addAttachment(part, contentType, context);
        }
    }

    private void addAttachment(Part part, String contentType, MimeDecoderContext context) throws MessagingException, IOException
    {
        ContentType ct = new ContentType(contentType);
        String baseType = ct.getBaseType();
        String filename = decodeFileName(part.getFileName(), baseType, context);

        if (part.getSize() >= 0)
        {
            String cid = null;
            if (part instanceof MimeBodyPart)
            {
                cid = ((MimeBodyPart)part).getContentID();
            }
            Attachment attachment = new Attachment(cid, filename, new ByteArrayDataSource(part.getInputStream(), baseType));
            context.addAttachment(attachment);
        }
    }

    private String decodeFileName(String filename, String type, MimeDecoderContext context) throws UnsupportedEncodingException
    {
        String decoded;
        if (StringUtils.isEmpty(filename))
        {
            decoded = MimeUtils.getFileNameByMimeType(DEFAULT_FILENAME + context.getAttachments().size(), type);
        }
        else
        {
            decoded = MimeUtility.decodeText(filename);
        }
        return decoded;
    }
}
