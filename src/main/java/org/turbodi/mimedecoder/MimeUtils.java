package org.turbodi.mimedecoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.mail.Session;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.ParseException;

/**
 * @author borisov
 * @since 24.10.2012
 */
public class MimeUtils
{
    public static Session defaultSession()
    {
        return Session.getDefaultInstance( System.getProperties() );
    }

    public static MimeMessage newMIME()
    {
        return new MimeMessage( defaultSession() );
    }

    public static MimeMessage toMIME(byte[] bytes)
    {
        MimeMessage message;

        try {
            ByteArrayInputStream is = new ByteArrayInputStream( bytes );
            message = new MimeMessage( defaultSession(), is );
        } catch (Exception e) {
            throw new RuntimeException( e );
        }

        return message;
    }

    public static MimeMessage toMIME(InputStream is)
    {
        MimeMessage message;

        try {
            message = new MimeMessage( defaultSession(), is );
        } catch (Exception e) {
            throw new RuntimeException( e );
        }

        return message;
    }

    public static byte[] toBytes(MimeMessage message)
    {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            message.writeTo( os );
        } catch (Exception e) {
            throw new RuntimeException( e );
        }
        return os.toByteArray();
    }

    public static String toString(MimeMessage message)
    {
        return new String( toBytes( message ) );
    }

    public static boolean isText(String contentType)
    {
        return isMimeType( contentType, MimeTypeRegistry.PLAIN );
    }

    public static boolean isHtml(String contentType)
    {
        return isMimeType( contentType, MimeTypeRegistry.HTML );
    }

    public static boolean isMimeType(String contentType, String mimeType)
    {
        try {
            if (contentType != null) {
                return new ContentType( contentType )
                        .match( mimeType );
            }
        } catch (ParseException e) {
            return contentType.equalsIgnoreCase( mimeType );
        }
        return MimeTypeRegistry.PLAIN.equals( mimeType );
    }

    public static String getFileNameByMimeType(String fileName, String fileMimeType)
    {
        String fileExt = MimeTypeRegistry.getFileExtensionByMimeType(fileMimeType);
        return (fileExt != null) ? fileName + '.' + fileExt : fileName;
    }
}
