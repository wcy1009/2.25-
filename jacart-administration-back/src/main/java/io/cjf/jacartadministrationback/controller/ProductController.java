package io.cjf.jacartadministrationback.controller;

import com.github.pagehelper.Page;
import io.cjf.jacartadministrationback.dto.in.ProductCreateInDTO;
import io.cjf.jacartadministrationback.dto.in.ProductSearchInDTO;
import io.cjf.jacartadministrationback.dto.out.ProductShowOutDTO;
import io.cjf.jacartadministrationback.dto.in.ProductUpdateInDTO;
import io.cjf.jacartadministrationback.dto.out.PageOutDTO;
import io.cjf.jacartadministrationback.dto.out.ProductListOutDTO;
import io.cjf.jacartadministrationback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService  productService;

    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> search(ProductSearchInDTO productSearchInDTO,
                                               @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<ProductListOutDTO> page = productService.search(pageNum);
        PageOutDTO<ProductListOutDTO>  pageOutDTO=new PageOutDTO<>();
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setList(page);
        return pageOutDTO;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody ProductCreateInDTO productCreateInDTO){
        Integer integer = productService.create(productCreateInDTO);
        return integer;
    }

    @PostMapping("/update")
    public void update(@RequestBody ProductUpdateInDTO productUpdateInDTO){
        productService.update(productUpdateInDTO);
    }

    @GetMapping("/getById")
    private ProductShowOutDTO getById(@RequestParam Integer productId){
        return productService.getById(productId);
    }

    @PostMapping("/delete")
    public void  delete(@RequestBody Integer productId){
        productService.delete(productId);
    }

    @PostMapping("/batchDelete")
    public void  batchDelete(@RequestBody List<Integer> productIds){
        productService.batchDelete(productIds);
    }

}
