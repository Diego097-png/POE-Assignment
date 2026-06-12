//MessageDataStoreTest
package com.chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageDataStoreTest {

    private MessageDataStore store;

    @BeforeEach
    public void setUp() {
        store = new MessageDataStore();
        store.addMessageWithFlag(new Message(1, "+27834557896", "Did you get the cake?"), "Sent");
        store.addMessageWithFlag(new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time."), "Stored");
        store.addMessageWithFlag(new Message(3, "+27834484567", "Yohoooo, I am at your gate."), "Disregard");
        store.addMessageWithFlag(new Message(4, "0838884567", "It is dinner time !"), "Sent");
        store.addMessageWithFlag(new Message(5, "+27838884567", "Ok, I am leaving without you."), "Stored");
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals(2, store.getSentMessages().size());
        assertEquals("Did you get the cake?", store.getSentMessages().get(0).getContent());
        assertEquals("It is dinner time !", store.getSentMessages().get(1).getContent());
    }

    @Test
    public void testDisplayTheLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        String actual = store.getLongestStoredMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchForMessageId() {
        String expected = "It is dinner time !";
        String actual = store.searchByMessageId("0838884567");
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchAllMessagesRegardingParticularRecipient() {
        String actual = store.searchAllMessagesForRecipient("+27838884567");
        assertTrue(actual.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(actual.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageUsingMessageHash() {
        String targetHash = store.getStoredMessages().get(0).getMessageHash();
        String result = store.deleteMessageByHash(targetHash);
        
        assertTrue(result.contains("successfully deleted."));
        assertEquals(1, store.getStoredMessages().size());
    }

    @Test
    public void testDisplayReport() {
        String report = store.generateFullReport();
        assertNotNull(report);
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
    }
}