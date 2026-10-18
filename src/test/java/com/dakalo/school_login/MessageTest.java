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
}
