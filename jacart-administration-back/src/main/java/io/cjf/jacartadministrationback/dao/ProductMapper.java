package io.cjf.jacartadministrationback.dao;

import com.github.pagehelper.Page;
import io.cjf.jacartadministrationback.dto.out.ProductListOutDTO;
import io.cjf.jacartadministrationback.po.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    void batchDelete(List<Integer> productIds);

    Page<ProductListOutDTO>  search(@Param("productCode") String productCode,
                                    @Param("status") Byte status,
                                    @Param("stockQuantity") Integer stockQuantity,
                                    @Param("price") Double price,
                                    @Param("productName") String productName);
}