/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dakalo.school_login;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    Message message = new Message();

    @Test
    public void testCheckMessageIDTrue() {
        assertTrue(message.checkMessageID("ID1234"));
    }

    @Test
    public void testCheckMessageIDFalse() {
        assertFalse(message.checkMessageID("ThisIsAnIDThatIsWayTooLong"));
    }

    @Test
    public void testCheckRecipientCellTrue() {
        assertTrue(message.checkRecipientCell("+278389689"));
    }

    @Test
    public void testCheckRecipientCellFalse() {
        assertFalse(message.checkRecipientCell("0838968976"));
    }

    @Test
    public void testCreateMessageHash() {
        String hash = message.createMessageHash("ID123", 1, "Hello world test");
        assertEquals("ID:1:HELLOTEST", hash);
    }

    @Test
    public void testSentMessage() {
        assertEquals("Message successfully sent.", message.sentMessage("1"));
        assertEquals("Press 0 to delete the message.", message.sentMessage("0"));
        assertEquals("Message successfully stored.", message.sentMessage("2"));
    }
   @Test
    public void testDisplayLongestMessage() {
        Message msg = new Message();
        msg.loadTestData();
        String longest = msg.displayLongestMessage();
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchByMessageID() {
        Message msg = new Message();
        msg.loadTestData();
        String result = msg.searchByMessageID("ID002");
        org.junit.Assert.assertTrue(result.contains("+27838884567"));
    }

    @Test
    public void testSearchByRecipient() {
        Message msg = new Message();
        msg.loadTestData();
        String result = msg.searchByRecipient("+27838884567");
        org.junit.Assert.assertTrue(result.contains("Where are you?"));
    }

    @Test
    public void testDeleteMessageByHash() {
        Message msg = new Message();
        msg.loadTestData();
        boolean deleted = msg.deleteMessageByHash("ID:2:WHERETIME");
        org.junit.Assert.assertTrue(deleted);
    } 
}
