package ru.kpfu.itis.genatulin.genatulin.hw4;

import java.util.Map;

public interface ISocketClient {
    public void addParameters(Map<String, String> parameters);
    public void sendRequest();
    public void setPath(String path);
    public void setHost(String host);
    public void setMethod(Method method);
    public String getResponse();
}
