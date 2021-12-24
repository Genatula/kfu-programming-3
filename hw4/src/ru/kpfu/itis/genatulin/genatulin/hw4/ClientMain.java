package ru.kpfu.itis.genatulin.genatulin.hw4;

public class ClientMain {
    public static void main(String[] args) {
        ISocketClient webClient = new SocketClient("127.0.0.1");
        webClient.setMethod(Method.GET);
        webClient.sendRequest();
//        System.out.println(webClient.getResponse());
    }
}
