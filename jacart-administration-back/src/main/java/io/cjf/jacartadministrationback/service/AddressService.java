package io.cjf.jacartadministrationback.service;

import io.cjf.jacartadministrationback.po.Address;


import java.util.List;

public interface AddressService {

    Address getById(Integer addressId);

    List<Address> getByCustomerId(Integer customerId);

}
