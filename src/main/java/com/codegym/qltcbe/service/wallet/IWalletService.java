package com.codegym.qltcbe.service.wallet;

import com.codegym.qltcbe.model.entity.Wallet;
import com.codegym.qltcbe.repository.WalletRepo;
import com.codegym.qltcbe.service.IGeneralService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IWalletService  {
    Wallet save(Wallet wallet);

    Iterable<Wallet> findAll();

    Optional<Wallet> findById(Long id);

    Iterable<Wallet> findAllByStatus( Long id);

    Iterable<Wallet> findAllByStatusPublicAndUser_Id(@Param("id") Long id);
    Iterable<Wallet> findAllByStatusPrivateAndUser_Id(@Param("id") Long id);
}
