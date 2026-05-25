//Message
package com.chatapp;

public class Message {

    private final int loopCounter;
    private final String recipient;
    private final String content;
    private final String messageId;
    private final String messageHash;

    public Message(int loopCounter, String recipient, String content) {
        this.loopCounter = loopCounter;
        this.recipient = recipient;
        this.content = content;
        this.messageId = generateMessageId();
        this.messageHash = generateMessageHash();
    }

    private String generateMessageId() {
        if (recipient == null || recipient.length() < 5) {
            return "MSG-00" + loopCounter;
        }
        String cellSlice = recipient.substring(recipient.length() - 4);
        return "ID-" + cellSlice + "-" + loopCounter;
    }

    private String generateMessageHash() {
        if (content == null || content.isEmpty()) {
            return "HASH-EMPTY";
        }
        StringBuilder manipulation = new StringBuilder();
        char[] chars = content.replaceAll("\\s+", "").toCharArray();
        int limit = Math.min(chars.length, 5);
        
        for (int i = 0; i < limit; i++) {
            if (Character.isUpperCase(chars[i])) {
                manipulation.append(Character.toLowerCase(chars[i]));
            } else {
                manipulation.append(Character.toUpperCase(chars[i]));
            }
        }
        return "HASH-" + manipulation.toString() + "-X" + content.length();
    }

    public String checkMessageLength(String content) {
        if (content == null || content.length() > 250) {
            return "Message is too long; please ensure that the content is no more than 250 characters.";
        }
        return "Message ready to send.";
    }

    public String SentMessage(int status) {
        if (status == 1) {
            return "Message successfully sent via QuickChat Engine.";
        }
        return "Message failed to send.";
    }

    public String printMessages() {
        return "Message ID: " + this.messageId + 
               "\nMessage Hash: " + this.messageHash +
               "\nRecipient: " + this.recipient + 
               "\nContent: " + this.content;
    }

    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getContent() { return content; }
}