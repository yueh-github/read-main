//package com.readmain.api.controller;
//
//import com.google.gson.Gson;
//import com.readmain.common.entity.DemoEntity;
//import com.readmain.common.entity.TestUser;
//import com.readmain.common.entity.UserEntity;
//import com.readmain.service.service.TestService;
//import org.apache.commons.collections.map.HashedMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * Created by yuehao on 2017/8/27.
// */
//@RestController
//public class UserController {
//
//    @Resource
//    private TestService testService;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//
//    @RequestMapping("/index")
//    public String getString() throws Exception {
//        UserEntity userEntity = new UserEntity("岳浩", 1000);
////
//        DemoEntity demoEntity = new DemoEntity();
////        demoEntity.setMsg("mes:给我一杯水");
////        demoEntity.setContent("content:水来了~");
//        return new Gson().toJson(demoEntity);
////        return "岳浩";
//    }
//
//    @RequestMapping("/list")
//    public List<TestUser> getUserList() throws Exception {
//        return this.testService.getUserList();
//    }
//
//    @RequestMapping("/save_redis_string")
//    public String saveRedisString(@RequestParam("name") String name) throws Exception {
//        stringRedisTemplate.opsForValue().set("name", "USER_NAME-" + name);
//        return stringRedisTemplate.opsForValue().get("name");
//    }
//
//    @RequestMapping("/save_redis_hash")
//    public String saveRedisObject() throws Exception {
//        Map map = new HashedMap();
//        map.put("name", "岳浩");
//        map.put("age", 30);
//        redisTemplate.opsForHash().putAll("map", map);
//        return new Gson().toJson(redisTemplate.opsForHash().entries("map"));
//    }
//
//    @RequestMapping("/save_redis_list")
//    public String saveRedisList() throws Exception {
//        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        redisTemplate.opsForList().set("y-list", lists.size(), lists);
//        return new Gson().toJson(redisTemplate.opsForList().range("y-list", 0, lists.size()));
//    }
//
////    @RequestMapping("/save_redis_user")
////    public String saveRedisUser() throws Exception {
////        ValueOperations<String, UserEntity> operations = redisTemplate.opsForValue();
////        UserEntity userEntity = new UserEntity("岳飞", 1000);
////        operations.set("yuehao", userEntity);
////        return new Gson().toJson(operations.get("yuehao").getName());
////    }
//}
