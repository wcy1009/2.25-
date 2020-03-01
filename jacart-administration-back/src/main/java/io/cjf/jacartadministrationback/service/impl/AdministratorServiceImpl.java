package io.cjf.jacartadministrationback.service.impl;

import io.cjf.jacartadministrationback.dao.AdministratorMapper;
import io.cjf.jacartadministrationback.po.Administrator;
import io.cjf.jacartadministrationback.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator getByUsername(String name) {

        return administratorMapper.getByUsername(name);
    }
}
