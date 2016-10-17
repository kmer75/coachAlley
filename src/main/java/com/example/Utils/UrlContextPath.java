package com.example.Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 16/10/2016.
 */
public class UrlContextPath {

    public static String getURLWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
