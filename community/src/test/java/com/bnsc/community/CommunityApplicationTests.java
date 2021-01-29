package com.bnsc.community;

import com.bnsc.community.mapper.DiscussPostMapper;
import com.bnsc.community.mapper.UserMapper;
import com.bnsc.community.pojo.DiscussPost;
import com.bnsc.community.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testSelectDiss(){
        List<DiscussPost> lists =  discussPostMapper.selectDiscussPosts(111,0,10);
        for(DiscussPost post:lists){
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(111);
        System.out.println(rows);
    }

}
