package com.wjd.mynote.common;

import com.google.gson.Gson;
import com.wjd.mynote.dto.Good;
import com.wjd.mynote.service.GoodService;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.server.EngineIoServer;
import io.socket.socketio.server.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@WebServlet("/socket.io/*")
public class SocketIoServlet extends HttpServlet {

    @Autowired
    GoodService goodService;

    public static final EngineIoServer mEngineIoServer = new EngineIoServer();
    public static final SocketIoServer mSocketIoServer = new SocketIoServer(mEngineIoServer);
    public static final SocketIoNamespace goodsNamespace = mSocketIoServer.namespace("/goods");
    public static final SocketIoAdapter adapter = new SocketIoMemoryAdapter.Factory().createAdapter(goodsNamespace);
//    public static final SocketIoNamespace goods2Namespace = mSocketIoServer.namespace("/goods2");

    static {

    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        mEngineIoServer.handleRequest(request, response);

        goodsNamespace.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @SneakyThrows
            @Override
            public void call(Object... args) {
                SocketIoSocket socket = (SocketIoSocket) args[0];
                adapter.add("pushGoods",socket);
//                System.out.println("=====" + goodsNamespace.getAdapter());
//                socket.joinRoom("pushGoods");

//                System.out.println("连接正常。。。" + goodsNamespace.getAdapter());

//                Gson gson = new Gson();
//                System.out.println("22222222222");
//                String json = gson.toJson(goodService.getGoodList(request));
//                socket.send("pushGoods", json);
//                Thread.sleep(Integer.MAX_VALUE);
            }
        });

        goodsNamespace.on("foo", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("wwwww");
            }
        });

    }


}
