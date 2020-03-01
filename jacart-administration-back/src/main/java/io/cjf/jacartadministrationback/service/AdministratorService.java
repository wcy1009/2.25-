package io.cjf.jacartadministrationback.service;

import io.cjf.jacartadministrationback.dao.AdministratorMapper;
import io.cjf.jacartadministrationback.po.Administrator;
import org.springframework.beans.factory.annotation.Autowired;

public interface AdministratorService {



      Administrator getByUsername(String name);
}
