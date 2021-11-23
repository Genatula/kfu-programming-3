package ru.kpfu.itis.genatulin.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WebServer implements IWebServer {

    private final int port;
    private ServerSocket serverSocket;
    private List<Socket> socketList;
    private List<IServerEventListener> listeners;
    private boolean started;

    public WebServer(int port) {
        this.listeners = new ArrayList<>();
        this.socketList = new ArrayList<>();
        this.port = port;
    }

    @Override
    public void start() {
        try {
            serverSocket = new ServerSocket(this.port);
            this.started = true;
            while (started) {
                Socket connection = serverSocket.accept();
                processSocketRequest(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerListener(IServerEventListener listener) {

    }

    @Override
    public void sendMessage(int connectionId, String message) {

    }

    public void processSocketRequest(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            socketList.add(socket);
            int connectionId = socketList.lastIndexOf(socket);
            StringBuilder message = new StringBuilder("");
            boolean[] endOfMessage = new boolean[]{false, false};
            int character;
            while ((character = reader.read()) != -1) {
                message.append((char) character);
            }
            System.out.println(message);
            this.started = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
