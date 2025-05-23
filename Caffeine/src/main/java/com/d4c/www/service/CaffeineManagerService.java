package com.d4c.www.service;

import com.d4c.www.pojo.dto.UserInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Service
@ResponseBody
@RequestMapping("/api")
public class CaffeineManagerService {
  	//模拟数据库
    private static HashMap<String, UserInfo> userInfoMap = new HashMap<>();
  
    //这种写法是错误的，当返回值是 void 时，缓存一个 空对象 到缓存中对应的`key`上
    @CachePut(cacheNames ="cache1" ,key = "T(String).valueOf(#userInfo.id)")
    public void addUserInfo1(UserInfo userInfo) {
        userInfoMap.put(userInfo.getId(), userInfo);
    }

    @PostMapping("/addUserInfo2")
    @CachePut(value = "user",key = "T(String).valueOf(#userInfo.id)")
    public UserInfo addUserInfo2(@RequestBody UserInfo userInfo) {
        userInfoMap.put(userInfo.getId(), userInfo);
        return  userInfo;
    }
  	//！！！应该改为: 返回值为UserInfo
    @PostMapping("/addUserInfo")
  	@CachePut(cacheNames ="cache1" ,key = "T(String).valueOf(#userInfo.id)")
    public UserInfo addUserInfo(@RequestBody UserInfo userInfo) {
        userInfoMap.put(userInfo.getId(), userInfo);
        return userInfo;
    }

    @GetMapping("/getByName/{id}")
    @Cacheable(cacheNames = "cache1",key = "#id")
    public UserInfo getByName(@PathVariable String id) {
        // 如果缓存中不存在，则从库中查找
        return userInfoMap.get(id);
    }

    @GetMapping("/getByName2/{id}")
    @Cacheable(value = "user",key = "#id")
    public UserInfo getByName2(@PathVariable String id) {
        // 如果缓存中不存在，则从库中查找
        System.out.println("从数据库中查找");
        return userInfoMap.get(id);
    }
    @GetMapping("/deleteById/{id}")
    @CacheEvict(cacheNames = "cache1",key = "#id")
    public void deleteById(@PathVariable String id) {
        userInfoMap.remove(id);
    }

    @GetMapping("/deleteById2/{id}")
    @CacheEvict(value = "user",key = "#id")
    public void deleteById2(@PathVariable String id) {
        userInfoMap.remove(id);
    }
}