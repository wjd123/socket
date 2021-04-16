package com.wjd.mynote.service;

import com.google.gson.Gson;
import com.wjd.mynote.common.Emitter;
import com.wjd.mynote.common.SocketIoServlet;
import io.socket.socketio.server.SocketIoNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

@Service
public class SocketService {

    public static final SocketIoNamespace goodsNamespace = SocketIoServlet.goodsNamespace;

    @Autowired
    GoodService goodService;

    public void socketTest(HttpServletRequest request) throws IOException {
        JedisPool jedis = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);

        System.out.println(jedis.getResource().get("111"));
        Emitter emitter = Emitter.getInstance(jedis, new HashMap<String,String>());
        Gson gson = new Gson();
        String json = gson.toJson(goodService.getGoodList(request));
//        Emitter emitter = Emitter.getInstance(null, opts);
        emitter.of("goods").to("pushGoods").emit("pushGoods",json );

    }


}
