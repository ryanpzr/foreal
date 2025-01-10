package com.example.foreal_project.controller;

import com.example.foreal_project.dto.AccountDTO;
import com.example.foreal_project.model.AccountInfo;
import com.example.foreal_project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/postAccountConfig")
    @CrossOrigin(origins = "*")
    public ResponseEntity<AccountInfo> addAccountConfig(@ModelAttribute AccountDTO accountDTO) {
        AccountInfo accountInfo = accountService.addAccountConfig(accountDTO);
        return ResponseEntity.ok().body(accountInfo);
    }

    @GetMapping("/getAccountInfo")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<AccountInfo>> getAccountInfo() {
        List<AccountInfo> accountInfo = accountService.getAccountInfo();
        return ResponseEntity.ok().body(accountInfo);
    }

}
