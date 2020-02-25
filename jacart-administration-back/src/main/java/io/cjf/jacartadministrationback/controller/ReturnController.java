package io.cjf.jacartadministrationback.controller;

import io.cjf.jacartadministrationback.dto.in.ReturnSearchOutDTO;
import io.cjf.jacartadministrationback.dto.in.ReturnUpdateActionInDTO;
import io.cjf.jacartadministrationback.dto.out.PageOutDTO;
import io.cjf.jacartadministrationback.dto.out.ReturnListOutDTO;
import io.cjf.jacartadministrationback.dto.out.ReturnShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
public class ReturnController {

    @GetMapping("/search")
    public PageOutDTO<ReturnListOutDTO> search(ReturnSearchOutDTO returnSearchOutDTO,
                                               @RequestParam Integer pageNum){
        return null;
    }

    @GetMapping("/getById")
    public ReturnShowOutDTO  getById(@RequestParam Integer returnId){
        return null;
    }

    @PostMapping("/updateAction")
    public void  updateAction(@RequestBody ReturnUpdateActionInDTO returnUpdateActionInDTO){

    }

}
