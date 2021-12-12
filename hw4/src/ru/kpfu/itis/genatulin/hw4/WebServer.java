package ru.kpfu.itis.genatulin.hw4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebServer implements IWebServer {

    private final int port;
    private ServerSocket serverSocket;
    private List<Socket> socketList;
    private List<IServerEventListener> listeners;
    private boolean started;
    private final Method[] methods = new Method[]{Method.GET};
    private final String[] params = new String[]{"a", "b"};
    private final String[] paths = new String[]{"/add", "/subtract", "/divide", "/multiply"};

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
    public void respond(Map<String, String> data, Socket socket) {
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketList.add(socket);
            int connectionId = socketList.lastIndexOf(socket);
            StringBuilder message = new StringBuilder("");
            boolean endOfMessage = false;
            int character;
            while ((character = reader.read()) != -1 && !endOfMessage) {
                if (character == '\r') {
                    endOfMessage = true;
                }
                message.append((char) character);
            }
            Map<String, String> data;
            try {
                 data = parseRequest(message.toString());
            } catch (IllegalMethodException exception) {
                 data = null;
            } catch (IncorrectFormatException exception) {
                 data = null;
            } catch (MissingParameterException exception) {
                data = null;
            } catch (IllegalPathException exception) {
                data = null;
            }
            respond(data, socket);
            this.started = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> parseRequest(String data) throws IncorrectFormatException, IllegalMethodException, MissingParameterException, IllegalPathException {
        Pattern pathRegex = Pattern.compile("(?<path>/[a-z]*)+\\?(?:(?<key>[a-z]+)=(?<value>[a-z0-9]+)&?){2,}");
        if (data.length() == 0) {
            throw new IncorrectFormatException();
        }
        String[] lines = data.split("\r");
//        if (lines.length < 2) {
//            throw new IncorrectFormatException();
//        }
        String[] firstLine = lines[0].split(" ");
        if (firstLine.length != 3) {
            throw new IncorrectFormatException();
        }
        Method method = Method.valueOf(firstLine[0]);
        if (!Arrays.asList(methods).contains(method)) {
            throw new IllegalMethodException();
        }
        Matcher pathMatcher = pathRegex.matcher(firstLine[1]);
        if (!pathMatcher.matches()) {
            throw new IncorrectFormatException();
        }
        Map<String, String> output = new HashMap<>();
        output.put("method", firstLine[0]);
        try {
            output.putAll(getParams(firstLine[1]));
        } catch (MissingParameterException e) {
            throw new MissingParameterException();
        }
        String path = pathMatcher.group("path");
        if (!Arrays.asList(paths).contains(path)) {
            throw new IllegalPathException();
        }
        output.put("path", path);
        return output;
    }

    private Map<String, String> getParams(String path) throws MissingParameterException {
        Map<String, String> output  = new HashMap<>();
        for (String param: params) {
            Pattern paramRegex = Pattern.compile(".*[?&]" + param + "=(?<value>[a-z0-9]*).*");
            Matcher matcher = paramRegex.matcher(path);
            if (!matcher.matches()) {
                throw new MissingParameterException();
            }
            String value = matcher.group("value");
            output.put(param, value);
        }
        return output;
    }
}
