MimeDecoder
===========

In-memory MIME parser, used for parsing E-mails. 
Based on javax.mail, [RFC](http://tools.ietf.org/html/rfc2046) aware

Decoded parts
===========

* From
* To
* Date
* Content-Type
* Attachments

Usage
===========
Package to jar:

```
mvn clean install
```
  
Import as maven dependency
```
<dependency>
  <groupId>org.turbo-di.mimedecoder</groupId>
  <artifactId>mimedecoder</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
Use in code (assume that mailInputStream is inbox mail)
```
MimeDecoder mimeDecoder = new MimeDecoder(MimeUtils.toMIME(mailInputStream));

String from = mimeDecoder.getFrom();
String to = mimeDecoder.getTo();
Date date = mimeDecoder.getDate();
String contentType = mimeDecoder.getContentType();
List<Attachment> attachmentList = mimeDecoder.getAttachments();
```
