package com.example.foreal_project.service;

import com.example.foreal_project.dto.AccountDTO;
import com.example.foreal_project.model.AccountInfo;
import com.example.foreal_project.repository.AccountInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class AccountService {

    @Autowired
    private AccountInfoRepository repository;

    public AccountInfo addAccountConfig(AccountDTO accountDTO) {
        MultipartFile imageData = accountDTO.picture();
        String baseDir = "C:/Users/ryanp/VsCode/foreal/frontend-vue/src/assets/imageProfilePicture/";

        String filePath = baseDir + imageData.getOriginalFilename();
        File destine = new File(filePath);

        try {
            File file = new File(baseDir);
            if (Objects.requireNonNull(file.listFiles()).length > 0) {
                file.listFiles()[0].delete();
                repository.deleteAll();
            }

            imageData.transferTo(destine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AccountInfo accountInfo = new AccountInfo(accountDTO);

        String fileSplitted = filePath.replaceAll(baseDir, "");
        accountInfo.setPathProfilePicture(fileSplitted);

        return repository.save(accountInfo);
    }

    public List<AccountInfo> getAccountInfo() {
        return repository.buscarInfos();
    }
}
