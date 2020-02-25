package io.cjf.jacartadministrationback.controller;


import io.cjf.jacartadministrationback.dto.in.AdministratorCreateInDTO;
import io.cjf.jacartadministrationback.dto.in.AdministratorResetPwdInDTO;
import io.cjf.jacartadministrationback.dto.in.AdministratorUpdateProfileInDTO;
import io.cjf.jacartadministrationback.dto.out.AdministratorGetProfileOutDTO;
import io.cjf.jacartadministrationback.dto.out.AdministratorListOutDTO;
import io.cjf.jacartadministrationback.dto.out.AdministratorShowOutDTO;
import io.cjf.jacartadministrationback.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @GetMapping("/login")
    public String  login(){
        return null;
    }

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO  getProfile(@RequestParam Integer administratorId){
        return  null;
    }

    @PostMapping("/updateProfile")
    public void   updateProfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO){

    }

    @GetMapping("/getPwdResetCode")
    public  String getPwdResetCodr(@RequestParam String email){
        return null;
    }

    @PostMapping("/resetPwd")
    public void  resePwd(@RequestBody AdministratorResetPwdInDTO administratorResetPwdInDTO){

    }

    @GetMapping("/getList")
    public PageOutDTO<AdministratorListOutDTO>  getList(@RequestParam Integer pageNum){
        return  null;
    }

    @GetMapping("/getById")
    public AdministratorShowOutDTO getById(@RequestParam Integer administratorId){
        return null;
    }

    @PostMapping("/create")
    public Integer  create(@RequestBody AdministratorCreateInDTO administratorCreateInDTO){
        return null;
    }

    @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO){

    }
}
