/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    @Test
    public void testMessageLengthValid(){
    Message msg = new Message(1, "+27123456789", "Hello Kyle");
    assertEquals("Message ready to send.", msg.checkMessageLength());    
    }
    
    @Test
    public void testMessageLengthInvalid(){
   String longMessage = "A".repeat(260);
    Message msg = new Message(1, "+27123456789", longMessage);
    assertEquals("Message exceeds 250 characters by 10 characters.", 
                 msg.checkMessageLength());
    }
    
    @Test
    public void testRecipientValid() {
    Message msg = new Message(1, "+27123456789", "Hello");
    assertEquals("Cell phone number successfully captured",
                 msg.checkRecipientCell());
    }
    
    @Test
    public void testRecipientInvalid() {
    Message msg = new Message(1, "0812345678", "Hello");
    assertEquals("Cell phone number is incorrectly formatted or does not contain an international code.",
                 msg.checkRecipientCell());
    }
    
    @Test
    public void testMessageHash() {
    Message msg = new Message(0, "+27123456789", "Hi Tonight", "0");
    assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }
    
    @Test
    public void testMessageIDLength() {
    Message msg = new Message(1, "+27123456789", "Hello");
    assertEquals(10, msg.getMessageID().length());
    }
    
    @Test
    public void testSendMessage() {
    Message msg = new Message(1, "+27123456789", "Hello");
    assertEquals("Message successfully sent.", msg.sentMessage("Send"));
    }
    
    @Test
    public void testDisregardMessage() {
    Message msg = new Message(1, "+27123456789", "Hello");
    assertEquals("Press 0 to delete the message.", msg.sentMessage("Disregard"));
    }
    
    @Test
    public void testStoreMessage() {
    Message msg = new Message(1, "+27123456789", "Hello");
    assertEquals("Message successfully stored.", msg.sentMessage("Store"));
    }
}
