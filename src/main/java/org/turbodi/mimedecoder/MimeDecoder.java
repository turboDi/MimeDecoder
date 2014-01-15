package org.turbodi.mimedecoder;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

import org.turbodi.mimedecoder.processor.IMimePartProcessor;
import org.turbodi.mimedecoder.processor.MimePartAttachmentProcessor;
import org.turbodi.mimedecoder.processor.MimePartContentProcessor;
import org.turbodi.mimedecoder.processor.MimePartContentTypeProcessor;

/**
* @author borisov
* @since 19.12.2012
*/
public class MimeDecoder
{
    private MimeMessage _message;
    
    private String _content;
    private String _contentType;
    private List<Attachment> _attachments;

    public MimeDecoder(MimeMessage message)
    {
        _message = message;
    }

    public String getTo() throws MessagingException
    {
        return StringUtils.join(_message.getAllRecipients(), "; ");
    }

    public Date getDate() throws MessagingException
    {
        return _message.getSentDate();
    }

    public String getFrom() throws MessagingException, IOException
    {
        Address[] addresses;
        try
        {
            addresses = _message.getFrom();
        }
        catch (MessagingException e)
        {
            // если не удалось разобрать адрес и имя отправителя - толкаем поле From как есть
            String from = _message.getHeader("From")[0];
            addresses = new Address[] { new InternetAddress("", from) };
        }
        return MimeUtility.decodeText(StringUtils.join(addresses, "; "));
    }

    public String getContent() throws MessagingException, IOException
    {
        if (_content == null)
        {
            MimeDecoderContext context = new MimeDecoderContext();
            decode(new MimePartContentProcessor(), context);
            _content = context.getText().toString();
        }
        return _content;
    }

    public String getContentType() throws MessagingException, IOException
    {
        if (_contentType == null)
        {
            MimeDecoderContext context = new MimeDecoderContext();
            decode(new MimePartContentTypeProcessor(), context);
            _contentType = context.getContentType();
        }
        return _contentType;
    }

    public List<Attachment> getAttachments() throws MessagingException, IOException
    {
        if (_attachments == null)
        {
            MimeDecoderContext context = new MimeDecoderContext(); 
            decode(new MimePartAttachmentProcessor(), context);
            _attachments = context.getAttachments();
        }
        return Collections.unmodifiableList(_attachments);
    }
    
    private void decode(IMimePartProcessor processor, MimeDecoderContext context) throws MessagingException, IOException
    {
        context.getTraverser().traverseParts(_message, _message.getContentType(), context, processor);
    }
}
