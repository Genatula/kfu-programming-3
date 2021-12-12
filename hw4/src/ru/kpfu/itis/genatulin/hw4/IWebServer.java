package ru.kpfu.itis.genatulin.hw4;

import java.net.Socket;
import java.util.Map;

public interface IWebServer {
    public void start();
    public void registerListener(IServerEventListener listener);
    public void respond(Map<String, String> data, Socket socket);
}
