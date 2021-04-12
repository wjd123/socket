package com.wjd.mynote.common;

import io.socket.emitter.Emitter;
import io.socket.engineio.server.EngineIoServer;
import io.socket.engineio.server.EngineIoSocket;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/socket.io/*")
public class SocketIoServlet extends HttpServlet {

    private final EngineIoServer mEngineIoServer = new EngineIoServer();
    private final SocketIoServer mSocketIoServer = new SocketIoServer(mEngineIoServer);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        mEngineIoServer.handleRequest(request, response);

        SocketIoNamespace namespace = mSocketIoServer.namespace("/123");

        namespace.on("connection", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                SocketIoSocket socket = (SocketIoSocket) args[0];
                // Do something with socket
            }
        });

        // Attaching to 'foo' event
        mEngineIoServer.on("foo", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("  ");
                // Arugments from client available in 'args'
            }
        });

//        namespace.send("foo", "bar arg", 1);

        namespace.broadcast("room", "foo", "bar arg");


    }





}