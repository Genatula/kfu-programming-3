package ru.kpfu.itis.genatulin.genatulin.hw4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocketServer implements ISocketServer {

    private final int port;
    private ServerSocket serverSocket;
    private List<Socket> socketList;
    private IServerEventListener listener;
    private boolean started;

    private final Method[] methods = new Method[]{Method.GET};
    private final String[] params = new String[]{"a", "b"};
    private final String[] paths = new String[]{"/add", "/subtract", "/divide", "/multiply"};

    public SocketServer(int port) {
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
        if (this.started) {
            throw new IllegalStateException();
        }
        listener.init(this);
        this.listener = listener;
    }

    @Override
    public void respond(Map<String, String> data, int socketId) {
        Socket socket = socketList.get(socketId);
        if (data != null) {
            String method = data.get("method");
            String path = data.get("path");
            StringBuilder response = new StringBuilder("");
            int answer = 0;
            switch (path) {
                case "/add":
                    for (String param: params) {
                        answer += Integer.parseInt(data.get(param));
                    }
                    break;
                case "/subtract":
                    int i = 1;
                    for (String param: params) {
                        if (i == 1) {
                            answer += Integer.parseInt(data.get(param));
                            i++;
                        }
                        else {
                            answer -= Integer.parseInt(data.get(param));
                        }
                    }
                    break;
                case "/multiply":
                    answer = 1;
                    for (String param: params) {
                        answer *= Integer.parseInt(data.get(param));
                    }
                    break;
                case "/divide":
                    answer = 1;
                    int k = 1;
                    for (String param: params) {
                        if (k == 1) {
                            answer *= Integer.parseInt(data.get(param));
                            k++;
                        }
                        else {
                            answer /= Integer.parseInt(data.get(param));
                        }
                    }
                    break;
            }
            response.append("HTTP/1.1 200 OK");
            response.append("\n");
            response.append("Content-Type: text/html");
            response.append("\n\n");
            response.append("<html><body>").append(answer).append("</body></html>");
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write(response.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void processSocketRequest(Socket socket) {
        try  {
            socketList.add(socket);
            listener.handle(socket.getInputStream(), socketList.lastIndexOf(socket));
            this.started = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Method[] getMethods() {
        return methods;
    }

    public String[] getParams() {
        return params;
    }

    public String[] getPaths() {
        return paths;
    }
}
