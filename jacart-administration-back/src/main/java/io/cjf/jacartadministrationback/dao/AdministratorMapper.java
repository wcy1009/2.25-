package io.cjf.jacartadministrationback.dao;

import com.github.pagehelper.Page;
import io.cjf.jacartadministrationback.po.Administrator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer administratorId);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Integer administratorId);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);

    Administrator getByUsername(@Param("username") String name);

    void deletes(@Param("administratorIds") List<Integer> administratorIds);

    Page<Administrator>   getList();
}