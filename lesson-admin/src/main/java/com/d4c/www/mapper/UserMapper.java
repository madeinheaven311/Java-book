package com.d4c.www.mapper;

import com.d4c.www.entity.Banner;
import com.d4c.www.entity.User;

import java.util.List;

public interface UserMapper {


    int insert(User user);


    List<User> select(User condition);


    int update(User user, User condition);


    int delete(User condition);


    int deleteByUserIds(Integer[] userIds);
}
