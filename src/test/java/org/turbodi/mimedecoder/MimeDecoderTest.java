package org.turbodi.mimedecoder;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

/**
* @author borisov
* @since 19.12.2012
*/
public class MimeDecoderTest
{
    private MimeDecoder _plainMsgDecoder;
    private MimeDecoder _maMsgDecoder;
    private MimeDecoder _mrMsgDecoder;
    private MimeDecoder _mnMsgDecoder;
    private MimeDecoder _mmMsgDecoder;

    private MimeMessage getMessage(String path)
    {
        InputStream is = MimeDecoderTest.class.getResourceAsStream(path);
        return MimeUtils.toMIME(is);
    }

    @Before
    public void init()
    {
        _plainMsgDecoder = new MimeDecoder(getMessage("plainTextMsg.eml"));//plain/text message
        _mrMsgDecoder = new MimeDecoder(getMessage("multipartRelatedMsg.eml"));//multipart/relative message
        _maMsgDecoder = new MimeDecoder(getMessage("multipartAlternativeMsg.eml"));//multipart/alternative message
        _mnMsgDecoder = new MimeDecoder(getMessage("multipartAlternativeWithNestedMsg.eml"));//multipart/alternative message with nested multipart/related
        _mmMsgDecoder = new MimeDecoder(getMessage("multipartMixedMsg.eml"));//multipart/mixed message with nested multipart/alternative
    }

    @Test
    public void fromDecodedProperly() throws Exception
    {
        assertThat(_plainMsgDecoder.getFrom(), is("Елена Александрова <undisclosed@domain.org>"));
        assertThat(_mrMsgDecoder.getFrom(), is("spoiled address <>"));
    }

    @Test
    public void toDecodedProperly() throws Exception
    {
        assertThat(_plainMsgDecoder.getTo(), is("undisclosed2@domain.org"));
        assertThat(_mrMsgDecoder.getTo(), is("undisclosed2@domain.org; nobody <undisclosed3@domain.org>"));
    }

    @Test
    public void dateDecodedProperly() throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z");
        assertThat(_plainMsgDecoder.getDate(), is(dateFormat.parse("2012/10/31 19:15:59 +0600")));
    }

    @Test
    public void contentDecodedProperly() throws Exception
    {
        assertThat(_plainMsgDecoder.getContent(), is(" Добрый день.\n\n\n"));
        assertThat(_mrMsgDecoder.getContent(), containsString("<p class=\"text\">"));
        assertThat(_maMsgDecoder.getContent(), containsString("<font color=\"#ff6600\">orange</font>"));
        assertThat(_maMsgDecoder.getContent(), not(containsString("plain orange")));

        assertThat(_mnMsgDecoder.getContent(), containsString("<h1>Multipart alternative with attachments</h1>"));
        assertThat(_mnMsgDecoder.getContent(), not(containsString("Here comes the plain text.")));

        assertThat(_mmMsgDecoder.getContent(), containsString("<h3>Связанные новости:</h3>"));
        assertThat(_mmMsgDecoder.getContent(), not(containsString("* Бухгалтерия.")));
    }

    @Test
    public void contentTypeDecodedProperly() throws Exception
    {
        assertThat(_plainMsgDecoder.getContentType(), is(MimeTypeRegistry.PLAIN));
        assertThat(_mrMsgDecoder.getContentType(), is(MimeTypeRegistry.HTML));
        assertThat(_maMsgDecoder.getContentType(), is(MimeTypeRegistry.HTML));
        assertThat(_mnMsgDecoder.getContentType(), is(MimeTypeRegistry.HTML));
        assertThat(_mmMsgDecoder.getContentType(), is(MimeTypeRegistry.HTML));
    }

    @Test
    public void attachmentsDecodedProperly() throws Exception
    {
        assertThat(_plainMsgDecoder.getAttachments().size(), is(0));

        assertThat(_mrMsgDecoder.getAttachments().size(), is(2));
        assertThat(_mrMsgDecoder.getAttachments().get(0).getFilename(), is("meubvzqgorvvawua.jpg"));
        assertThat(_mrMsgDecoder.getAttachments().get(0).getContentId(), is("<b0a42f67a1ae19acda1c56bcc3d092e650815a70245f3>"));

        assertThat(_maMsgDecoder.getAttachments().size(), is(0));
        assertThat(_mnMsgDecoder.getAttachments().size(), is(1));
        assertThat(_mmMsgDecoder.getAttachments().size(), is(2));
    }
}
