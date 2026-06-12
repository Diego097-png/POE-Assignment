//MessageDataStore
package com.chatapp;

import java.util.ArrayList;
import java.util.List;

public class MessageDataStore {

    private final List<Message> sentMessages = new ArrayList<>();
    private final List<Message> disregardedMessages = new ArrayList<>();
    private final List<Message> storedMessages = new ArrayList<>();

    public void addMessageWithFlag(Message msg, String flag) {
        if (msg == null || flag == null) return;
        
        switch (flag.trim().toLowerCase()) {
            case "sent" -> sentMessages.add(msg);
            case "disregard" -> disregardedMessages.add(msg);
            case "stored" -> storedMessages.add(msg);
        }
    }

    public String displayStoredSendersAndRecipients() {
        if (storedMessages.isEmpty()) {
            return "No stored messages available.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message msg : storedMessages) {
            sb.append("Sender: System, Recipient: ").append(msg.getRecipient()).append("\n");
        }
        return sb.toString().trim();
    }

    public String getLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
        Message longest = storedMessages.get(0);
        for (Message msg : storedMessages) {
            if (msg.getContent().length() > longest.getContent().length()) {
                longest = msg;
            }
        }
        return longest.getContent();
    }
    public String searchByMessageId(String messageId) {
        if (messageId == null) return "";
        // Search through sent first, then stored per test specifications
        for (Message msg : sentMessages) {
            if (msg.getMessageId().equals(messageId)) return msg.getContent();
        }
        for (Message msg : storedMessages) {
            if (msg.getMessageId().equals(messageId)) return msg.getContent();
        }
        return "Message not found.";
    }

    public String searchAllMessagesForRecipient(String recipient) {
        if (recipient == null) return "";
        StringBuilder sb = new StringBuilder();
        
        for (Message msg : storedMessages) {
            if (msg.getRecipient().equals(recipient)) {
                if (sb.length() > 0) sb.append(" ");
                sb.append("\"").append(msg.getContent()).append("\"");
            }
        }
        for (Message msg : sentMessages) {
            if (msg.getRecipient().equals(recipient)) {
                if (sb.length() > 0) sb.append(" ");
                sb.append("\"").append(msg.getContent()).append("\"");
            }
        }
        return sb.toString();
    }

    public String deleteMessageByHash(String hash) {
        if (hash == null) return "Hash invalid.";
        
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                Message removed = storedMessages.remove(i);
                return "Message: \"" + removed.getContent() + "\" successfully deleted.";
            }
        }
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).getMessageHash().equals(hash)) {
                Message removed = sentMessages.remove(i);
                return "Message: \"" + removed.getContent() + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    public String generateFullReport() {
        StringBuilder sb = new StringBuilder();
        for (Message msg : sentMessages) {
            sb.append("Hash: ").append(msg.getMessageHash())
              .append(" | Recipient: ").append(msg.getRecipient())
              .append(" | Message: ").append(msg.getContent()).append("\n");
        }
        for (Message msg : storedMessages) {
            sb.append("Hash: ").append(msg.getMessageHash())
              .append(" | Recipient: ").append(msg.getRecipient())
              .append(" | Message: ").append(msg.getContent()).append("\n");
        }
        return sb.toString().trim();
    }

    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
}