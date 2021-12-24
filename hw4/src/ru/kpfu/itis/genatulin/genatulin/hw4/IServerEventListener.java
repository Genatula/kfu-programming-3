package ru.kpfu.itis.genatulin.genatulin.hw4;

import java.io.IOException;
import java.io.InputStream;

public interface IServerEventListener {
    public void init(SocketServer server);
    public void handle(InputStream inputStream, int socketId) throws IOException;
}
