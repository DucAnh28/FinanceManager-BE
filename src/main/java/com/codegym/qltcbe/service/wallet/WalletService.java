package com.codegym.qltcbe.service.wallet;

import com.codegym.qltcbe.model.entity.Wallet;
import com.codegym.qltcbe.repository.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WalletService  implements IWalletService{
    @Autowired
    private WalletRepo walletRepository;

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Iterable<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return walletRepository.findById(id);
    }

    @Override
    public Iterable<Wallet> findAllByStatus(Long id) {
        return walletRepository.findAllByStatus(id);
    }

    @Override
    public Iterable<Wallet> findAllByStatusPublicAndUser_Id(Long id) {
        return null;
    }

    @Override
    public Iterable<Wallet> findAllByStatusPrivateAndUser_Id(Long id) {
        return null;
    }


}
