package com.rp.sec11.assignment;

import java.util.function.Consumer;

public class SlackMember {
    private String name;
    private Consumer<String> messageConsumer; //slack member will consume the string for other action: delete message, update message, post message, etc...

    public SlackMember(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }

    // what he do when receive message
    // it is a Consumer since it is used by SlackMember when subscribe to the Slack room
    void receiveMessage(String message){
        System.out.println(message);
    }

    void sendMessage(String message){
        this.messageConsumer.accept(message);
    }

    void setMessageConsumer(Consumer<String> messageConsumer){
        this.messageConsumer = messageConsumer;
    }

}
