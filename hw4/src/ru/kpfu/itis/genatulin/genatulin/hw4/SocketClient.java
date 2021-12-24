package ru.kpfu.itis.genatulin.genatulin.hw4;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketClient implements ISocketClient {

    private Socket socket;
    private String response;
    private String method;
    private Map<String, String> parameters;
    private String host;
    private String path = "/";
    private final int port = 11903;

    public SocketClient(String host) {
        this.host = host;
    }

    @Override
    public void addParameters(Map<String, String> parameters) {
        this.parameters = new HashMap<>();
        this.parameters.putAll(parameters);
    }

    @Override
    public void sendRequest() {
        if (method == null) {
            throw new IllegalStateException("Method has not been specified");
        }
        try {
            openConnection();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            writer.print(method + " " + path + " HTTP/1.1");
            writer.print("\n");
            writer.print("Host: " + host);
            writer.print("\n\n");
            writer.print(getParametersAsString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getResponse() {
        StringBuilder builder = new StringBuilder();
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
                String line;
                while (null != (line = reader.readLine())) {
                    builder.append(line);
                }
            }
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.response = builder.toString();
        return this.response;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public void setMethod(Method method) {
        this.method = method.name();
    }

    private String getParametersAsString() {
        StringBuilder builder = new StringBuilder();
        if (parameters == null) {
            builder.append("");
        }
        else {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                if (entry.getKey().contains(" ")) {
                    throw new IllegalArgumentException("The key cannot contain a space character");
                }
                if (builder.length() == 0) {
                    builder
                            .append(entry.getKey())
                            .append("=")
                            .append(transformSpacesIntoPluses(entry.getValue()));
                } else {
                    builder
                            .append("&")
                            .append(entry.getKey())
                            .append("=")
                            .append(transformSpacesIntoPluses(entry.getValue()));
                }
            }
        }
        return builder.toString();
    }

    private String transformSpacesIntoPluses(String input) {
        return input.replaceAll("\\s", "+");
    }

    private void openConnection() throws IOException {
        InetAddress address = InetAddress.getByName(this.host);
        this.socket = new Socket(address, port);
    }

    private void closeConnection() throws IOException {
        this.socket.close();
    }
}
