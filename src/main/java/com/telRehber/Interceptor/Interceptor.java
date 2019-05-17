package com.telRehber.Interceptor;

import com.telRehber.model.tblLogin;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class Interceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object obj)throws Exception{
        HttpSession session = request.getSession();
        if (   session==null) return false;
        else{
            String kullaniciAdi =(String) session.getAttribute("kullaniciAdi");
            if (kullaniciAdi==null) return false;
            else return true;
        }
    }
}
