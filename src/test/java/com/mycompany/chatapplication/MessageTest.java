/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;
import java.util.List;
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    private Message msg1;
    private Message msg2;
    private Message msg3;
    private Message msg4;
    private Message msg5;
    
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
    Message msg = new Message(0, "+27123456789", "Hi Tonight", "00");
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
    
    @BeforeEach
    public void setUp() {
    Message.clearData();
    
    msg1 = new Message(1, "+27834557896",
            "Did you get the cake?");

    msg2 = new Message(2, "+27838884567",
            "Where are you? You are late! I have asked you to be on time.");

    msg3 = new Message(3, "+27834484567",
            "Yohoooo, I am at your gate.");

    msg4 = new Message(4, "0838884567",
            "It is dinner time!");

    msg5 = new Message(5, "+27838884567",
            "Ok, I am leaving without you.");

    // Process messages based on POE requirements
    msg1.sentMessage("Send");
    msg2.sentMessage("Store");
    msg3.sentMessage("Store");
    msg4.sentMessage("Send");
    msg5.sentMessage("Store");
    }
    
    @Test
    public void testSentMessagesArray_correctlyPopulated() {
    String[] sentMessages = Message.getSentMessages();
    assertTrue(
        java.util.Arrays.asList(sentMessages)
            .contains("Did you get the cake?")
    );
    assertTrue(
        java.util.Arrays.asList(sentMessages)
            .contains("It is dinner time!")
    );
    }
    
    @Test
    public void testDisplayLongestMessage_returnsCorrectMessage() {
    String expected = "Where are you? You are late! I have asked you to be on time.";
    assertEquals(expected, Message.displayLongestMessage());
    }
    
    @Test
    public void testSearchByMessageID_returnsCorrectMessage() {
    String result = Message.searchByMessageID("0838884567");
    assertEquals("It is dinner time!", result);
    }
    
    @Test
    public void testSearchByRecipient_returnsAllMatchingMessages() {
    String result = Message.searchByRecipient("+27838884567");
    assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
    assertTrue(result.contains("Ok, I am leaving without you."));
    }
    
    @Test
    public void testDeleteByHash_removesCorrectMessage() {
    String hash = msg2.createMessageHash();
    String expected = "Message: Where are you? You are late! I have asked you to be on time successfully deleted.";
    assertEquals(expected,Message.deleteByMessageHash(hash));
    }
    
    @Test
    public void testDisplayReport_containsRequiredFields() {
    String report = Message.displayFullReport();

    assertTrue(report.contains(msg1.createMessageHash()));
    assertTrue(report.contains("+27834557896"));
    assertTrue(report.contains("Did you get the cake?"));

    assertTrue(report.contains(msg4.createMessageHash()));
    assertTrue(report.contains("0838884567"));
    assertTrue(report.contains("It is dinner time!"));
    }
    
    
}
