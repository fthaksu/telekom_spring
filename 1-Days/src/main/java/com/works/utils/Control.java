package com.works.utils;

import com.works.entities.User;
import com.works.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class Control {

    final UserRepository uRepo;
    final HttpServletRequest req;
    final HttpServletResponse res;
    public Control(UserRepository uRepo, HttpServletRequest req, HttpServletResponse res) {
        this.uRepo = uRepo;
        this.req = req;
        this.res = res;
    }


    // user login control
    public boolean login(User user, String remember) {
        String md5Pass = fncMd5(user.getPassword(), 3);
        Optional<User> oUser = uRepo.findByEmailEqualsIgnoreCaseAndPasswordEquals( user.getEmail(), md5Pass );
        if ( oUser.isPresent() ) {
            User us = oUser.get();
            us.setPassword(null);
            // session create
            req.getSession().setAttribute("user", us);
            return true;
        }
        return false;
    }

    public String control( String page ) {

        boolean status = req.getSession().getAttribute("user") == null;
        if ( status ) {
            return "redirect:";
        }
        return page;

    }



    public String fncMd5( String password, int count ) {
        for (int i = 0; i < count; i++) {
            password = MD5(password);
        }
        return password;
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }



}
