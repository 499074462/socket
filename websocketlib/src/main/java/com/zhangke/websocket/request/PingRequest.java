package com.zhangke.websocket.request;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;

/**
 * 发送 Ping
 * <p>
 * Created by ZhangKe on 2019/3/28.
 */
public class PingRequest implements Request<Object> {

    PingRequest() {
    }

    @Override
    public void setRequestData(Object data) {

    }

    @Override
    public Object getRequestData() {
        return null;
    }

    @Override
    public void send(WebSocketClient client) {
        Log.e("data", "ping");
        client.sendPing();
    }

    @Override
    public void release() {
        RequestFactory.releasePingRequest(this);
    }
}
