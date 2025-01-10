package com.example.foreal_project.repository;

import com.example.foreal_project.model.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {

    @Query("SELECT a FROM Account a")
    List<AccountInfo> buscarInfos();
}
