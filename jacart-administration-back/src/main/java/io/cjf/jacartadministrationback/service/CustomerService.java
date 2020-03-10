package io.cjf.jacartadministrationback.service;

import com.github.pagehelper.Page;
import io.cjf.jacartadministrationback.dto.in.CustomerSearchInDTO;
import io.cjf.jacartadministrationback.dto.in.CustomerSetStatusInDTO;
import io.cjf.jacartadministrationback.po.Customer;

public interface CustomerService {

    Page<Customer> search(CustomerSearchInDTO customerSearchInDTO, Integer pageNum);

    Customer getById(Integer customerId);

    void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO);

}
