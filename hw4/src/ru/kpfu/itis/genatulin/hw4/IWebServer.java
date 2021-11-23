package ru.kpfu.itis.genatulin.hw4;

public interface IWebServer {
    public void start();
    public void registerListener(IServerEventListener listener);
    public void sendMessage(int connectionId, String message);
}
