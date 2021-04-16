package com.wjd.mynote.controller;

import com.wjd.mynote.service.GoodService;
import com.wjd.mynote.service.SocketService;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class SocketIOController {

    @Autowired
    GoodService goodService;
    @Autowired
    SocketService socketService;


    @GetMapping("qqq")
    public void socketEmit(HttpServletRequest request) throws URISyntaxException, IOException {
        socketService.socketTest(request);
    }


}
