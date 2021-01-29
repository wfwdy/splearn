package com.bnsc.community.controller;

import com.bnsc.community.pojo.DiscussPost;
import com.bnsc.community.pojo.Page;
import com.bnsc.community.pojo.User;
import com.bnsc.community.service.DiscussPostService;
import com.bnsc.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/","/index"}, method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> lists = discussPostService.selectDiscussPosts(0,page.getOffset(), page.getLimit());
        List<Map<String,Object>>  discussPosts = new ArrayList<>();
        if(lists!=null){
            for(DiscussPost post:lists){
                Map<String,Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.selectById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
