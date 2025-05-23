package com.d4c.www.mapper;

import com.d4c.www.entity.Banner;

import java.util.List;

public interface BannerMapper {

    int insert( Banner banner );

    List<Banner> select( Banner banner );

    int update( Banner banner , Banner condition);

    int delete(Banner condition);

    int deleteByBannerIds( Integer ByBannerIds[] );
}
