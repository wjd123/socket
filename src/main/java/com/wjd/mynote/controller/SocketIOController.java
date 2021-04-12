package com.wjd.mynote.controller;

import io.socket.client.IO;import io.socket.client.Socket;import io.socket.emitter.Emitter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class SocketIOController {

    @GetMapping("client")
    public void socketClient() throws URISyntaxException {

        final Socket socket = IO.socket("http://localhost:8081");
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("I have connected to server.");
            }
        });

        socket.connect();
        socket.send("foo", "bar arg", 1);

    }


}
