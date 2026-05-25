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
/**
 *
 * @author Student
 */
public class Message {

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

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

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
   

    //Sends message / Stores message / Disregards message 
    public String sentMessage(String option) {

        if (option.equalsIgnoreCase("Send")) {
            return "Message successfully sent.";
        }

        if (option.equalsIgnoreCase("Disregard")) {
            return "Press 0 to delete the message.";
        }

        if (option.equalsIgnoreCase("Store")) {
            storeMessage();
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
}