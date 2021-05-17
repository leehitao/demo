package com.xrt.bzj.dao.mapper;


import com.xrt.bzj.dao.po.MchUser;

public interface MchUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MchUser record);

    int insertSelective(MchUser record);

    MchUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MchUser record);

    int updateByPrimaryKey(MchUser record);

}