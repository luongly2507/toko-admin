package com.example.toko_admin.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketClient extends WebSocketListener {
    private WebSocket webSocket;

    public void connectWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("ws://192.168.1.105:3000/app/application")
                .build();
        System.out.println(request);
        webSocket = client.newWebSocket(request, this);
    }

    public void disconnectWebSocket() {
        if (webSocket != null) {
            webSocket.cancel();
            webSocket = null;
        }
    }
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println(text);
    }
}
