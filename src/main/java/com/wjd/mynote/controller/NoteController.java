package com.wjd.mynote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class NoteController {

    @GetMapping("/note/{id}")
    public String getNote(@PathVariable("id")String id,HttpServletResponse response) throws IOException {
        File f = new File("C:\\study\\java\\mynote\\src\\main\\resources\\123.txt");
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return "error";
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要

        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        br.close();
        out.close();
        return "note" + id;
    }

    @GetMapping("/note/download/out")
    public void download(HttpServletResponse response) throws IOException {
        File f = new File("C:\\study\\java\\mynote\\src\\main\\resources\\123.txt");
        if (!f.exists()) {
            response.sendError(333, "!!!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); //

        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        br.close();
        out.close();
    }

    @GetMapping("/sss")
    public String print (String s) {
        return "sss";
    }
}
