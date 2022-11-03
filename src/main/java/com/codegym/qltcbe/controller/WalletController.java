package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.entity.Wallet;
import com.codegym.qltcbe.service.wallet.IWalletService;
import com.codegym.qltcbe.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private IWalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<Wallet> addWallet(@RequestBody Wallet wallet){
        return new ResponseEntity<>(walletService.save(wallet), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<Iterable<Wallet>> getAllWallet(){
        Iterable<Wallet> wallets =  walletService.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Wallet> findWalletById(@PathVariable Long id) {
        Optional<Wallet> walletOptional = walletService.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(walletOptional.get(), HttpStatus.OK);
    }
}
