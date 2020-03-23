package com.negen.util;

import org.springframework.http.server.ServletServerHttpRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 13:43 2020/3/6
 * @ Description：输出至前端工具类
 * @ Modified By：
 * @Version: 1.0
 */
public class PrintUtil {

    public static void response(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(msg);
        out.flush();
    }
}
