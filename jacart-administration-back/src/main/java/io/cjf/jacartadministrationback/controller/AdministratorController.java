package io.cjf.jacartadministrationback.controller;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.pagehelper.Page;
import io.cjf.jacartadministrationback.constant.ClientExceptionConstant;
import io.cjf.jacartadministrationback.dto.in.*;
import io.cjf.jacartadministrationback.dto.out.*;
import io.cjf.jacartadministrationback.enumeration.AdministratorStatus;
import io.cjf.jacartadministrationback.exception.ClientException;
import io.cjf.jacartadministrationback.po.Administrator;
import io.cjf.jacartadministrationback.service.AdministratorService;
import io.cjf.jacartadministrationback.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private Map<String, String> emailPwdResetCodeMap = new HashMap<>();

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
    public AdministratorGetProfileOutDTO  getProfile(@RequestAttribute Integer administratorId){
        Administrator administrator = administratorService.getById(administratorId);
        AdministratorGetProfileOutDTO administratorGetProfileOutDTO=new AdministratorGetProfileOutDTO();
        administratorGetProfileOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorGetProfileOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorGetProfileOutDTO.setUsername(administrator.getUsername());
        administratorGetProfileOutDTO.setRealName(administrator.getRealName());
        administratorGetProfileOutDTO.setEmail(administrator.getEmail());
        administratorGetProfileOutDTO.setCreateTimestamp(administrator.getCreateTime().getTime());


        return  null;
    }

    @PostMapping("/updateProfile")
    public void   updateProfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO,
                                @RequestAttribute Integer administratorId){
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorId);
        administrator.setRealName(administratorUpdateProfileInDTO.getRealName());
        administrator.setEmail(administratorUpdateProfileInDTO.getEmail());
        administrator.setAvatarUrl(administratorUpdateProfileInDTO.getAvatarUrl());
        administratorService.update(administrator);
    }

    @PostMapping("/changePwd")
    public void  changePwd(@RequestBody AdministratorChangePwdInDTO administratorChangePwdInDTO,
                           @RequestAttribute Integer administratorId){

    }

    @GetMapping("/getPwdResetCode")
    public  String getPwdResetCodr(@RequestParam String email){
        Administrator administrator = administratorService.getByEmail(email);
        byte[] bytes = secureRandom.generateSeed(3);
        String hex = DatatypeConverter.printHexBinary(bytes);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("jcart管理端管理员密码重置");
        message.setText(hex);
        mailSender.send(message);
        //todo send messasge to MQ
        emailPwdResetCodeMap.put(email, hex);
        return null;
    }

    @PostMapping("/resetPwd")
    public void  resePwd(@RequestBody AdministratorResetPwdInDTO administratorResetPwdInDTO) throws ClientException {
        String email = administratorResetPwdInDTO.getEmail();
        if (email == null) {
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_EMAIL_NONE_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_PWDRESET_EMAIL_NONE_ERRMSG);
        }
        String innerResetCode = emailPwdResetCodeMap.get(email);
        if (innerResetCode == null) {
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_INNER_RESETCODE_NONE_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_PWDRESET_INNER_RESETCODE_NONE_ERRMSG);
        }
        String outerResetCode = administratorResetPwdInDTO.getResetCode();
        if (outerResetCode == null) {
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_OUTER_RESETCODE_NONE_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_PWDRESET_OUTER_RESETCODE_NONE_ERRMSG);
        }
        if (!outerResetCode.equalsIgnoreCase(innerResetCode)){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_RESETCODE_INVALID_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_PWDRESET_RESETCODE_INVALID_ERRMSG);
        }
        Administrator administrator = administratorService.getByEmail(email);
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRMSG);
        }

        String newPwd = administratorResetPwdInDTO.getNewPwd();
        if (newPwd == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_NEWPWD_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_NEWPWD_NOT_EXIST_ERRMSG);
        }
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, newPwd.toCharArray());
        administrator.setEncryptedPassword(bcryptHashString);
        administratorService.update(administrator);
    }

    @GetMapping("/getList")
    public PageOutDTO<AdministratorListOutDTO>  getList(@RequestParam Integer pageNum){
        Page<Administrator> page = administratorService.getList(pageNum);
        page.stream().map(administrator -> {
            AdministratorListOutDTO administratorListOutDTO=new AdministratorListOutDTO();
            administratorListOutDTO.setAdministratorId(administrator.getAdministratorId());
            administratorListOutDTO.setUsername(administrator.getUsername());
            administratorListOutDTO.setStatus(administrator.getStatus());
            administratorListOutDTO.setCreateTimestamp(administrator.getCreateTime().getTime());
            return administratorListOutDTO;

        }).collect(Collectors.toList());


        PageOutDTO<AdministratorListOutDTO>  pageOutDTO=new PageOutDTO<>();
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setTotal(page.getTotal());
        return  pageOutDTO;
    }

    @GetMapping("/getById")
    public AdministratorShowOutDTO getById(@RequestParam Integer administratorId){
        Administrator administrator = administratorService.getById(administratorId);
        AdministratorShowOutDTO administratorShowOutDTO=new AdministratorShowOutDTO();
        administratorShowOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorShowOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorShowOutDTO.setEmail(administrator.getEmail());
        administratorShowOutDTO.setUsername(administrator.getUsername());
        administratorShowOutDTO.setRealName(administrator.getRealName());
        administratorShowOutDTO.setStatus(administrator.getStatus());
        return administratorShowOutDTO;
    }

    @PostMapping("/create")
    public Integer  create(@RequestBody AdministratorCreateInDTO administratorCreateInDTO){
        Administrator administrator=new Administrator();
        administrator.setUsername(administratorCreateInDTO.getUsername());
        administrator.setRealName(administratorCreateInDTO.getRealName());
        administrator.setEmail(administratorCreateInDTO.getEmail());
        administrator.setAvatarUrl(administratorCreateInDTO.getAvatarUrl());
        administrator.setStatus((byte) AdministratorStatus.Enable.ordinal());
        administrator.setCreateTime(new Date());

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, administratorCreateInDTO.getPassword().toCharArray());
        administrator.setEncryptedPassword(bcryptHashString);
        administrator.setEncryptedPassword(bcryptHashString);
        Integer integer = administratorService.create(administrator);

        return integer;
    }

    @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorUpdateInDTO.getAdministratorId());
        administrator.setRealName(administratorUpdateInDTO.getRealName());
        administrator.setEmail(administratorUpdateInDTO.getEmail());
        administrator.setAvatarUrl(administratorUpdateInDTO.getAvatarUrl());
        administrator.setStatus(administratorUpdateInDTO.getStatus());
        String password = administratorUpdateInDTO.getPassword();
        if (password != null && !password.isEmpty()){
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            administrator.setEncryptedPassword(bcryptHashString);
        }
        administratorService.update(administrator);

    }

    @PostMapping("/delete")
    public void delete(@RequestBody  Integer administratorId){
        administratorService.delete(administratorId);
    }

    @PostMapping("/delets")
    public void  deletes(List<Integer> administratorIds){
            administratorService.deletes(administratorIds);
    }

}
