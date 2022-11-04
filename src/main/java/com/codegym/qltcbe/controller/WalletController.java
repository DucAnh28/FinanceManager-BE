package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.model.entity.Wallet;
import com.codegym.qltcbe.service.user.IUserService;
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
    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<Wallet> addWallet(@RequestBody Wallet wallet){
        wallet.setStatus(1);
        return new ResponseEntity<>(walletService.save(wallet), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> findWalletById(@PathVariable Long id) {
        Optional<Wallet> walletOptional = walletService.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(walletOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wallet> deleteWallet(@PathVariable Long id) {
        Optional<Wallet> walletDelete = walletService.findById(id);
        if (!walletDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletService.remove(id);
        return new ResponseEntity<>(walletDelete.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wallet> editWallet(@PathVariable Long id, @RequestBody Wallet wallet) {
        Optional<Wallet> walletOptional = walletService.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wallet.setId(walletOptional.get().getId());
        return new ResponseEntity<>(walletService.save(wallet), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Wallet>> getAllWallet(@RequestParam Long user_id) {
        Iterable<Wallet> walletIterable = walletService.findWalletsByAppUserId(user_id);
        return new ResponseEntity<>(walletIterable, HttpStatus.OK);
    }
}
