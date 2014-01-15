package org.turbodi.mimedecoder;

import java.util.ArrayList;
import java.util.List;

/**
* @author borisov
* @since 20.12.2012
*/
public class MimeDecoderContext
{
    private StringBuilder _text = new StringBuilder();
    private String _contentType = MimeTypeRegistry.PLAIN;
    private List<Attachment> _attachments = new ArrayList<Attachment>();
    private MimePartTraverser _traverser = new MimePartTraverser();
    
    public StringBuilder getText()
    {
        return _text;
    }

    public void addText(String text)
    {
        _text.append(text);
    }

    public String getContentType()
    {
        return _contentType;
    }

    public void setContentType(String contentType)
    {
        _contentType = contentType;
    }

    public List<Attachment> getAttachments()
    {
        return _attachments;
    }

    public void addAttachment(Attachment attachment)
    {
        _attachments.add(attachment);
    }

    public MimePartTraverser getTraverser()
    {
        return _traverser;
    }

    public void setTraverser(MimePartTraverser traverser)
    {
        _traverser = traverser;
    }
}
