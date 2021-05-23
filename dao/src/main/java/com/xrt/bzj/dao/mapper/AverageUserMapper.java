package com.xrt.bzj.dao.mapper;

import com.xrt.bzj.dao.po.AverageUser;

import java.util.List;

public interface AverageUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AverageUser record);

    int insertSelective(AverageUser record);

    AverageUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AverageUser record);

    int updateByPrimaryKey(AverageUser record);

    AverageUser selectByAccount(String accont);

    List<AverageUser> selectAllAverageUser();
}