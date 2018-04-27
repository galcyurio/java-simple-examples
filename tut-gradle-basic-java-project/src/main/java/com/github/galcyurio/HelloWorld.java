package com.github.galcyurio;


import org.apache.log4j.Logger;

/**
 * @author galcyurio
 */
public class HelloWorld {

    private final static Logger logger = Logger.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        MessageService messageService = new MessageService();

        logger.info(messageService.getMessage());
    }
}
