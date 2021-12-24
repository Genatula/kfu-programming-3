package ru.kpfu.itis.genatulin.genatulin.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocketServerEventListener implements IServerEventListener {
    private SocketServer server;

    @Override
    public void init(SocketServer server) {
        this.server = server;
    }

    @Override
    public void handle(InputStream inputStream, int socketId) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
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
        server.respond(data, socketId);
    }

    private Map<String, String> parseRequest(String data) throws IncorrectFormatException, IllegalMethodException, MissingParameterException, IllegalPathException {
        Pattern pathRegex = Pattern.compile("(?<path>/[a-z]*)+\\?(?:(?<key>[a-z]+)=(?<value>[a-z0-9]+)&?){2,}");
        if (data.length() == 0) {
            throw new IncorrectFormatException();
        }
        String[] lines = data.split("\r");
        String[] firstLine = lines[0].split(" ");
        if (firstLine.length != 3) {
            throw new IncorrectFormatException();
        }
        Method method = Method.valueOf(firstLine[0]);
        if (!Arrays.asList(server.getMethods()).contains(method)) {
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
        if (!Arrays.asList(server.getPaths()).contains(path)) {
            throw new IllegalPathException();
        }
        output.put("path", path);
        return output;
    }

    private Map<String, String> getParams(String path) throws MissingParameterException {
        Map<String, String> output  = new HashMap<>();
        for (String param: server.getParams()) {
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
