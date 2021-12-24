package ru.kpfu.itis.genatulin.genatulin.hw4;

import java.net.Socket;
import java.util.Map;

public interface ISocketServer {
    public void start();
    public void registerListener(IServerEventListener listener);
    public void respond(Map<String, String> data, int socketId);
}
