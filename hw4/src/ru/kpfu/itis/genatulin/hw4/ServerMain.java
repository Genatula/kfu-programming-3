package ru.kpfu.itis.genatulin.hw4;

public class ServerMain {
    public static void main(String[] args) {
        WebServer server = new WebServer(11903);
        server.start();
    }
}
