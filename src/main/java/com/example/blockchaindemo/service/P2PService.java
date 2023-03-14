package com.example.blockchaindemo.service;

import org.java_websocket.WebSocket;

import java.util.List;

public interface P2PService {

    void broatcast(String toJSONString);

    String queryLatestBlockMsg();

    List<WebSocket> getSockets();

    void handleMessage(WebSocket webSocketClient, String msg, List<WebSocket> sockets);

    void write(WebSocket ws, String massage);
}
