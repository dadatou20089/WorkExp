package netty;

import netty.service.TimerServer;

public class Main {

    private TimerServer timerServer;

    public static void main(String[] args) {

        int port  = 8080;
        new TimerServer().bind(port);


    }
}
