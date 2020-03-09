package io.cjf.jacartadministrationback.service;

import com.github.pagehelper.Page;
import io.cjf.jacartadministrationback.dto.in.ProductCreateInDTO;
import io.cjf.jacartadministrationback.dto.in.ProductSearchInDTO;
import io.cjf.jacartadministrationback.dto.in.ProductUpdateInDTO;
import io.cjf.jacartadministrationback.dto.out.PageOutDTO;
import io.cjf.jacartadministrationback.dto.out.ProductListOutDTO;
import io.cjf.jacartadministrationback.dto.out.ProductShowOutDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductService {
    Integer   create(ProductCreateInDTO productCreateInDTO);

    void  update(ProductUpdateInDTO productUpdateInDTO);

    void  delete(Integer productId);

    void batchDelete(List<Integer> productIds);

    Page<ProductListOutDTO> search(ProductSearchInDTO productSearchInDTO, Integer pageNum);

    ProductShowOutDTO  getById(Integer productId);

}
