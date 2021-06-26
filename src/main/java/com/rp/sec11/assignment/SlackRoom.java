package com.rp.sec11.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {
    private String roomName;
    //Sink to receive data
    private Sinks.Many<SlackMessage> sink;
    //Flux to publish data
    private Flux<SlackMessage> flux;

    public SlackRoom(String roomName){
        this.roomName = roomName;
        this.sink = Sinks.many().multicast().directBestEffort();
        this.flux = this.sink.asFlux();
    }

    //what room needs to do when user subscribe to the room
    public void joinRoom(SlackMember slackMember){
        System.out.println("Member " + slackMember.getName() + " joined ------------> room: " + this.roomName);
        this.subscribe(slackMember);
        slackMember.setMessageConsumer(s -> {
            this.postMessage(s, slackMember);
        });
    }

    //how user subscribe to the room
    private void subscribe(SlackMember slackMember){
        this.flux.filter(sm -> !sm.getSender().equals(slackMember.getName()))
                .doOnNext(sm -> sm.setRecipient(slackMember.getName()))
                .map(SlackMessage::toString)
                .subscribe(s -> slackMember.receiveMessage(s));
    }

    //define method to post message
    // we need to know what is the message and who is the sender
    private void postMessage(String message, SlackMember slackMember){
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setMessage(message);
        slackMessage.setSender(slackMember.getName());
        this.sink.tryEmitNext(slackMessage);
    }
}
