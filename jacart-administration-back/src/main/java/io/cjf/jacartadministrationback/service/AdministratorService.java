package io.cjf.jacartadministrationback.service;

import com.github.pagehelper.Page;
import io.cjf.jacartadministrationback.po.Administrator;

import java.util.List;

public interface AdministratorService {

      Administrator  getById(Integer administratorId);

      Administrator getByUsername(String name);

      void update(Administrator administrator);

      Integer  create(Administrator administrator);

      void    delete(Integer administratorId);

      void    deletes(List<Integer> administratorIds);

      Page<Administrator>   getList(Integer pageNum);

    Administrator getByEmail(String email);
}
