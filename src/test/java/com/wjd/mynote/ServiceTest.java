package com.wjd.mynote;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ServiceTest {


    @Test
    public void test01() throws InterruptedException {
        int limitSec = 30;
        final int[] curSec = {limitSec};
        System.out.println("count down from "+limitSec+" s ");
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                System.out.println("Time remians "+ --curSec[0] +" s");

            }
        },0,1000);
        TimeUnit.SECONDS.sleep(limitSec);
        timer.cancel();
//        System.out.println("Time is out!");
    }

    @Test
    public void test02(){
        ArrayList<String> list = new ArrayList<>();

        for (String s :
                list) {
            System.out.println(s);
        }
    }

    public int sendNum(Object... objs){
        return objs.length;
    }

}
