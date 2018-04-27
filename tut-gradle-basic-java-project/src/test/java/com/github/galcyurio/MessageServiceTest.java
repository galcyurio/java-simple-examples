package com.github.galcyurio;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author galcyurio
 */
public class MessageServiceTest {

    private MessageService messageService;

    @Before
    public void setUp() throws Exception {
        messageService = new MessageService();
    }

    @Test
    public void getMessage_test() {
        String actual = messageService.getMessage();
        assertEquals("Hello World!", actual);
    }
}