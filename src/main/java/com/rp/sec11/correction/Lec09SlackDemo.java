package com.rp.sec11.correction;

import com.rp.courseUtill.Util;

public class Lec09SlackDemo {
    public static void main(String[] args) {

        SlackRoom slackRoom = new SlackRoom("Reactor");

        SlackMember sam = new SlackMember("sam");
        SlackMember jake = new SlackMember("jake");
        SlackMember mike = new SlackMember("mike");

        slackRoom.joinRoom(sam);
        slackRoom.joinRoom(jake);

        sam.says("Hi all...");

        Util.sleepSeconds(4);

        jake.says("Hey!");
        sam.says("I simply want to say hi...");

        Util.sleepSeconds(4);

        slackRoom.joinRoom(mike);
        mike.says("Hey guys! Glad to be here!");

    }
}
