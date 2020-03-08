package io.cjf.jacartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.cjf.jacartadministrationback.dao.AdministratorMapper;
import io.cjf.jacartadministrationback.po.Administrator;
import io.cjf.jacartadministrationback.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator getById(Integer administratorId) {
        return administratorMapper.selectByPrimaryKey(administratorId);
    }

    @Override
    public Administrator getByUsername(String name) {

        return administratorMapper.getByUsername(name);
    }

    @Override
    public void update(Administrator administrator) {
     administratorMapper.updateByPrimaryKeySelective(administrator);
    }

    @Override
    public Integer create(Administrator administrator) {
        administratorMapper.insertSelective(administrator);
        Integer administratorId = administrator.getAdministratorId();
        return administratorId;
    }

    @Override
    public void delete(Integer administratorId) {
        administratorMapper.deleteByPrimaryKey(administratorId);
    }

    @Override
    public void deletes(List<Integer> administratorIds) {
        administratorMapper.deletes(administratorIds);
    }

    @Override
    public Page<Administrator> getList(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<Administrator> page = administratorMapper.getList();
        return page;
    }

    @Override
    public Administrator getByEmail(String email) {
        Administrator administrator = administratorMapper.selectByEmail(email);
        return administrator;
    }


}
