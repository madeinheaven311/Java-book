package com.d4c.www.service;

import com.d4c.www.component.annotation.DoubleCache;
import com.d4c.www.component.myEnum.CacheType;
import com.d4c.www.pojo.dto.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Service
@ResponseBody
@RequestMapping("/api/user")
public class DoubleCacheService {
    private HashMap<String, UserInfo> hashMap = new HashMap<>();
    DoubleCacheService(){
        hashMap.put("123",new UserInfo("123","张三"));
    }

    @GetMapping("/add")
    @DoubleCache(value = "user",key = "#userInfo.id",type = CacheType.PUT)
    public UserInfo addUserInfo(@RequestBody UserInfo userInfo) {
        hashMap.put(userInfo.getId(), userInfo);
        return userInfo;
    }

    @GetMapping("/get/{id}")
    @DoubleCache(value = "user", key = "#id", type = CacheType.FULL)
    public UserInfo getByName(@PathVariable String id) {
        // 如果缓存中不存在，则从库中查找
        UserInfo userInfo = hashMap.get(id);
        if (userInfo == null){
            return new UserInfo();
        }
        return userInfo;
    }
}