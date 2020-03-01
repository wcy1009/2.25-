package io.cjf.jacartadministrationback.controller;


import at.favre.lib.crypto.bcrypt.BCrypt;
import io.cjf.jacartadministrationback.constant.ClientExceptionConstant;
import io.cjf.jacartadministrationback.dto.in.AdministratorCreateInDTO;
import io.cjf.jacartadministrationback.dto.in.AdministratorLoginInDTO;
import io.cjf.jacartadministrationback.dto.in.AdministratorResetPwdInDTO;
import io.cjf.jacartadministrationback.dto.in.AdministratorUpdateProfileInDTO;
import io.cjf.jacartadministrationback.dto.out.*;
import io.cjf.jacartadministrationback.exception.ClientException;
import io.cjf.jacartadministrationback.po.Administrator;
import io.cjf.jacartadministrationback.service.AdministratorService;
import io.cjf.jacartadministrationback.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/login")
    public AdministratorLoginOutDTO  login(AdministratorLoginInDTO administratorLoginInDTO) throws ClientException {
        Administrator administrator = administratorService.getByUsername(administratorLoginInDTO.getUsername());
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }
        String encPwdDB = administrator.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(administratorLoginInDTO.getPassword().toCharArray(), encPwdDB);

        if (result.verified) {
            AdministratorLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(administrator);
            return administratorLoginOutDTO;
        }else {
            throw new ClientException(ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }
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
