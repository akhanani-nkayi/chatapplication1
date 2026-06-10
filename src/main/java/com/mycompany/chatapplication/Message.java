/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 *
 * @author Student
 */
public class Message {
    
    private static List<String> sentMessages = new ArrayList<>();
    private static List<String> disregardedMessages = new ArrayList<>();
    private static List<String> storedMessages = new ArrayList<>();        
    private static List<String> messageHashes = new ArrayList<>();
    private static List<String> messageIDs = new ArrayList<>();
    private static List<String> recipients = new ArrayList<>();
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    private static int totalMessage = 0;

    //Constructor
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();

        totalMessage++;
    }

    public Message(int messageNumber, String recipient, String messageText, String testIDPrefix) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        // deterministic ID for testing
        this.messageID = testIDPrefix + "12345678";
        this.messageHash = createMessageHash();
    }

    //Message ID generator
    private String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    //Hash Creation
    public String createMessageHash() {
        String idPart = messageID.substring(0, 2);

        String msgNum = String.valueOf(messageNumber);

        String[] words = messageText.split(" ");

        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]","");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]","");

        String hash = idPart + ":" + msgNum + ":" + firstWord + lastWord;

        return hash.toUpperCase();
    }

    //Gets message ID
    public String getMessageID() {
        return messageID;
    }
    //Gets message hash
    public String getMessageHash() {
        return messageHash;
    }

    //Checks message length
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + " characters.";
        }
    }

    // ✔ RECIPIENT VALIDATION
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("\\+\\d+")) {
            return "Cell phone number successfully captured";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }
    }

    //Sends message / Stores message / Disregards message 
    public String sentMessage(String option) {
        if (option.equalsIgnoreCase("Send")) {
            sentMessages.add(messageText);
            messageHashes.add(messageHash);
            messageIDs.add(messageID);
            return "Message successfully sent.";
        }

        if (option.equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(messageText);
            return "Press 0 to delete the message.";
        }

        if (option.equalsIgnoreCase("Store")) {
            storeMessage();
            storedMessages.add(messageText);
            sentMessages.add(messageText);
            messageHashes.add(messageHash);
            messageIDs.add(messageID);
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }

    //Prints message
    public void printMessage() {
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
    }

    //JSON file storage
    public void storeMessage() {
        JSONObject obj = new JSONObject();

        obj.put("messageID", messageID);
        obj.put("messageNumber", messageNumber);
        obj.put("messageHash", messageHash);
        obj.put("recipient", recipient);
        obj.put("message", messageText);

        try (FileWriter fw = new FileWriter("messages.json", true)) {

            fw.write(obj.toString());
            fw.write(System.lineSeparator());

        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    //Total amount of messages
    public static int returnTotalMessages() {
        return totalMessage;
    }
    
    public static void displayStoredMessages() {//Goes to the stored messages and displys them
    if (storedMessages.isEmpty()) {
        System.out.println("There are no stored messages.");
        return;
    }
    System.out.println("=== Stored Messages ===");
    for (String msg : storedMessages) {
        System.out.println(msg);
        }
    }
    
    public static String displayLongestMessage() {
    if (storedMessages.isEmpty()) {
    return "No stored messages.";
    }
    String longest = storedMessages.get(0);
    for (String msg : storedMessages) {//Counts the length of the messages stored and checks which is longer
        if (msg.length() > longest.length()) {
            longest = msg;
        }
    }
    System.out.println("Longest message: " + longest);
        return longest;
    }
    
    public static String searchByMessageID(String searchID) {
    int index = messageIDs.indexOf(searchID);
    if (index != -1) {
        System.out.println("Message found:");
        return storedMessages.get(index);
        } else {
            return null;
        }
    }
    
    
    
    public static String displayFullReport() {//Gives report on every message sent to the recipient
    System.out.println("=== Report of Sent Messages ===");
    for (int i = 0; i < sentMessages.size(); i++) {
        System.out.println("Message #" + (i + 1));
        System.out.println("Recipient: " + "TODO: recipient here");
        System.out.println("Hash: " + messageHashes.get(i));
        System.out.println("Text: " + sentMessages.get(i));
        System.out.println("-----------------------------");
        }
        return null;
    }   
    
    // Attribution: org.json library - https://mvnrepository.com/artifact/org.json/json
    public static void loadStoredMessages() {
    storedMessages.clear(); // Prevents duplicates when loading again
        try (BufferedReader br = new BufferedReader(new FileReader("messages.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject obj = new JSONObject(line);
                // Retrieve the message text from JSON
                String messageText = obj.getString("message");
                String recipient = obj.getString("recipient");
                storedMessages.add(messageText);
                recipients.add(recipient);
            }
        } catch (IOException e) {
        // File may not exist yet on first run
        System.out.println("No stored messages file found yet.");
        }
    }
    
    public static String[] getSentMessages() { //Gets input sent messages from array
    return sentMessages.toArray(new String[0]);
    }
    
    public static void clearData() {
    sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        recipients.clear();
        totalMessage = 0;
    }
    
    
}