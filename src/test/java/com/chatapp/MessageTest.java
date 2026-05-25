//MessageTest
package com.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testCheckMessageLength_Success() {
        Message message = new Message(1, "+27838968976", "Hello! This is a standard test message.");
        String expected = "Message ready to send.";
        String actual = message.checkMessageLength(message.getContent());
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckMessageLength_Failure() {
        StringBuilder longContent = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            longContent.append("this_is_too_long_");
        }
        
        Message message = new Message(2, "+27838968976", longContent.toString());
        String expected = "Message is too long; please ensure that the content is no more than 250 characters.";
        String actual = message.checkMessageLength(message.getContent());
        assertEquals(expected, actual);
    }

    @Test
    public void testSentMessage_Success() {
        Message message = new Message(1, "+27838968976", "Test message content.");
        String expected = "Message successfully sent via QuickChat Engine.";
        String actual = message.SentMessage(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageStringManipulations() {
        Message message = new Message(1, "+27838968976", "Hello");
        assertEquals("ID-8976-1", message.getMessageId());
        assertEquals("HASH-hELLO-X5", message.getMessageHash());
    }
}