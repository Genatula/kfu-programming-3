package ru.kpfu.itis.genatulin.genatulin.hw4;

public class ServerMain {
    public static void main(String[] args) {
        SocketServer server = new SocketServer(11903);
        server.registerListener(new SocketServerEventListener());
        server.start();
    }
}
