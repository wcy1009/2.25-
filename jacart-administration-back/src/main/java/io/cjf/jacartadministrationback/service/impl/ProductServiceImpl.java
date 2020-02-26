package io.cjf.jacartadministrationback.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.cjf.jacartadministrationback.dao.ProductDetailMapper;
import io.cjf.jacartadministrationback.dao.ProductMapper;
import io.cjf.jacartadministrationback.dto.in.ProductCreateInDTO;
import io.cjf.jacartadministrationback.dto.in.ProductUpdateInDTO;
import io.cjf.jacartadministrationback.dto.out.ProductListOutDTO;
import io.cjf.jacartadministrationback.dto.out.ProductShowOutDTO;
import io.cjf.jacartadministrationback.po.Product;
import io.cjf.jacartadministrationback.po.ProductDetail;
import io.cjf.jacartadministrationback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailMapper productDetailMapper;


    @Override
    @Transactional
    public Integer create(ProductCreateInDTO productCreateInDTO) {
        Product product=new Product();
        product.setDiscount(productCreateInDTO.getDiscount());
        product.setPrice(productCreateInDTO.getPrice());
        product.setProductCode(productCreateInDTO.getProductCode());
        product.setProductName(productCreateInDTO.getProductName());
        product.setStockQuantity(productCreateInDTO.getStockQuantity());
        product.setStatus(productCreateInDTO.getStatus());
        product.setMainPicUrl(productCreateInDTO.getMainPicUrl());
        product.setRewordPoints(productCreateInDTO.getRewordPoints());
        product.setSortOrder(productCreateInDTO.getSortOrder());
        String description = productCreateInDTO.getDescription();
        String productAbstract = description.substring(0, Math.min(100, description.length()));
        product.setProductAbstract(productAbstract);
        productMapper.insertSelective(product);



        Integer productId = product.getProductId();
        ProductDetail productDetail=new ProductDetail();
        productDetail.setProductId(productId);
        productDetail.setDescription(productCreateInDTO.getDescription());
        List<String> othPicUrls = productCreateInDTO.getOthPicUrls();
        productDetail.setOtherPicUrls(JSON.toJSONString(othPicUrls));
        productDetailMapper.insertSelective(productDetail);
        return  productId;
    }

    @Override
    @Transactional
    public void update(ProductUpdateInDTO productUpdateInDTO) {
        Product product=new Product();
        product.setDiscount(productUpdateInDTO.getDiscount());
        product.setPrice(productUpdateInDTO.getPrice());
        product.setProductName(productUpdateInDTO.getProductName());
        product.setStockQuantity(productUpdateInDTO.getStockQuantity());
        product.setStatus(productUpdateInDTO.getStatus());
        product.setMainPicUrl(productUpdateInDTO.getMainPicUrl());
        product.setRewordPoints(productUpdateInDTO.getRewordPoints());
        product.setSortOrder(productUpdateInDTO.getSortOrder());
        String description = productUpdateInDTO.getDescription();
        String productAbstract = description.substring(0, Math.min(100, description.length()));
        product.setProductAbstract(productAbstract);
        productMapper.updateByPrimaryKeySelective(product);



        Integer productId = product.getProductId();
        ProductDetail productDetail=new ProductDetail();
        productDetail.setProductId(productId);
        productDetail.setDescription(productUpdateInDTO.getDescription());
        List<String> othPicUrls = productUpdateInDTO.getOthPicUrls();
        productDetail.setOtherPicUrls(JSON.toJSONString(othPicUrls));
        productDetailMapper.updateByPrimaryKeySelective(productDetail);
    }

    @Override
    @Transactional
    public void delete(Integer productId) {
        productMapper.deleteByPrimaryKey(productId);
        productDetailMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public void batchDelete(List<Integer> productIds) {
        productMapper.batchDelete(productIds);
        productDetailMapper.batchDelete(productIds);
    }

    @Override
    public Page<ProductListOutDTO> search(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<ProductListOutDTO> page = productMapper.search();
        return page;
    }

    @Override
    public ProductShowOutDTO getById(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        ProductDetail productDetail = productDetailMapper.selectByPrimaryKey(productId);

        ProductShowOutDTO productShowOutDTO = new ProductShowOutDTO();
        productShowOutDTO.setProductId(productId);
        productShowOutDTO.setProductCode(product.getProductCode());
        productShowOutDTO.setProductName(product.getProductName());
        productShowOutDTO.setPrice(product.getPrice());
        productShowOutDTO.setDiscount(product.getDiscount());
        productShowOutDTO.setStatus(product.getStatus());
        productShowOutDTO.setMainPicUrl(product.getMainPicUrl());
        productShowOutDTO.setRewordPoints(product.getRewordPoints());
        productShowOutDTO.setSortOrder(product.getSortOrder());
        productShowOutDTO.setStockQuantity(product.getStockQuantity());

        productShowOutDTO.setDescription(productDetail.getDescription());
        String otherPicUrlsJson = productDetail.getOtherPicUrls();
        List<String> otherPicUrls = JSON.parseArray(otherPicUrlsJson, String.class);
        productShowOutDTO.setOthPicUrls(otherPicUrls);
        return productShowOutDTO;
    }


}
