package com.rp.sec11.assignment;

import com.rp.courseUtill.Util;

public class Lec08Assignment {
    public static void main(String[] args) {
        SlackRoom slackRoom = new SlackRoom("Reactor");

        SlackMember sam = new SlackMember("sam");
        SlackMember jake = new SlackMember("jake");
        SlackMember mike = new SlackMember("mike");

        slackRoom.joinRoom(sam);
        slackRoom.joinRoom(jake);

        sam.sendMessage("Hi all...");

        Util.sleepSeconds(4);

        jake.sendMessage("Hey!");
        sam.sendMessage("I simply want to say hi...");

        Util.sleepSeconds(4);

        slackRoom.joinRoom(mike);
        mike.sendMessage("Hey guys! Glad to be here!");
    }
}
