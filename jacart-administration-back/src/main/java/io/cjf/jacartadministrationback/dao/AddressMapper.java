package io.cjf.jacartadministrationback.dao;

import io.cjf.jacartadministrationback.po.Address;
import io.cjf.jacartadministrationback.po.Administrator;
import org.apache.ibatis.annotations.Param;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

}