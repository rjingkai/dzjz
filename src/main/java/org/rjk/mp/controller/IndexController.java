package org.rjk.mp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String getUser(){
        return "index";
    }

    @RequestMapping("/adddzjz")
    public String adddzjz(){
        return "adddzjz";
    }

    @RequestMapping("/")
    public String denglu(HttpSession session){
        session.removeAttribute("orgid");
        session.removeAttribute("username");
        session.removeAttribute("name");
        return "denglu";
    }


    @RequestMapping("/indexpcs")
    public String indexpcs(String orgid){
        return "index-pcs";
    }

    @RequestMapping("/updzjz")
    public String updata(){
        return "updzjz";
    }
}
