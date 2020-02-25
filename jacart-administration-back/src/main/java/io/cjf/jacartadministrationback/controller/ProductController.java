package io.cjf.jacartadministrationback.controller;

import io.cjf.jacartadministrationback.dto.in.ProductCreateInDTO;
import io.cjf.jacartadministrationback.dto.in.ProductSearchInDTO;
import io.cjf.jacartadministrationback.dto.out.ProductShowOutDTO;
import io.cjf.jacartadministrationback.dto.in.ProductUpdateInDTO;
import io.cjf.jacartadministrationback.dto.out.PageOutDTO;
import io.cjf.jacartadministrationback.dto.out.ProductListOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> search(ProductSearchInDTO productSearchInDTO,
                                               @RequestParam Integer pageNum){
        return null;
    }

    @PostMapping("/creates")
    public Integer create(@RequestBody ProductCreateInDTO productCreateInDTO){
        return null;
    }

    @PostMapping
    public void update(@RequestBody ProductUpdateInDTO productUpdateInDTO){

    }

    @GetMapping("/getById")
    private ProductShowOutDTO getById(@RequestParam Integer productId){
        return null;
    }


}
