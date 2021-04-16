package com.wjd.mynote.service;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.wjd.mynote.common.SocketIoServlet;
import com.wjd.mynote.dto.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.OperatingSystemMXBean;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wjd
 */
@Service
public class GoodService {

    /**
     * 发布商品
     *
     * @param request
     * @param good
     * @throws InterruptedException
     */
    @Transactional(rollbackFor = {})
    public void releaseGoods(HttpServletRequest request, Good good) throws InterruptedException {

        //设置过期时间为180s后
        good.setExpiredTime(getAfterTime(180000));
        Object o = request.getServletContext().getAttribute("goods");

        if (null == o) {
            ArrayList<Good> goods = new ArrayList<>();
            goods.add(good);
            request.getServletContext().setAttribute("goods", goods);
        } else if (o instanceof ArrayList) {
            ArrayList<Good> goods = (ArrayList) o;
            goods.add(good);
            request.getServletContext().setAttribute("goods", goods);
        }
    }

    /**
     * 竞拍
     *
     * @param request
     * @param goodId
     * @param buyerId
     * @return
     */
    @Transactional(rollbackFor = {})
    public boolean bidGood(HttpServletRequest request, int goodId, int buyerId) {
        ServletContext application = request.getServletContext();
        Object o = application.getAttribute("goods");

        if (!(o instanceof ArrayList)) {
            return false;
        }

        ArrayList<Good> goods = (ArrayList) o;
        Optional<Good> optional = goods.stream()
                .filter(i -> {
                    //存在抢购商品，并且过期时间大于当前时间
                    return (i.getId() == goodId) && (i.getExpiredTime().getTime() > System.currentTimeMillis());
                })
                .findAny();

        if (!optional.isPresent()) {
            return false;
        }

        synchronized (this) {
            Good good = optional.get();
            //过期时间重置为180s后
            good.setExpiredTime(getAfterTime(180000));
            good.setBuyerId(buyerId);
        }

        pushGoods(request);
        return true;

    }

    /**
     * 推送商品信息
     *
     * @param request
     */
    public void pushGoods(HttpServletRequest request) {
        Gson gson = new Gson();
        String json = gson.toJson(getGoodList(request));
//        SocketIoServlet.goodsNamespace.broadcast("pushGoods", "pushGoods", json);
//        SocketIoServlet.goodsNamespace.broadcast(null,"pushGoods", json);
        String[] s = new String[]{"111","pushGoods","222","333"};
        SocketIoServlet.goodsNamespace.broadcast(s ,"pushGoods", new String[]{json});
    }

    /**
     * 获取列表
     *
     * @param request
     * @return
     */
    public List<Good> getGoodList(HttpServletRequest request) {
        //
        ArrayList<Good> goods = null;
        Object o = request.getServletContext().getAttribute("goods");
        if (o instanceof ArrayList) {
            goods = (ArrayList) o;
            for (Good good : goods) {
                long time = good.getExpiredTime().getTime() - System.currentTimeMillis();
                //移除过期商品
                if (time <= 0) {
                    goods.remove(good);
                }
                //计算倒计时
                int goodTime = (int) (time / 1000);
                good.setTime(goodTime);
            }
        }

        return goods;
    }

    /**
     * 获取指定毫秒后的时间
     *
     * @param later 指定毫秒数
     * @return
     */
    private Date getAfterTime(long later) {
        return new Date(System.currentTimeMillis() + later);
    }


    //TODO
    public List<Good> getGoodListConnect() {
        return null;
    }

    //TODO 定时任务清除 过期商品(没有买家，且超过过期时间)

}
