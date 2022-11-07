package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.entity.Wallet;
import com.codegym.qltcbe.service.user.IUserService;
import com.codegym.qltcbe.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Wallet> addWallet(@RequestBody Wallet wallet) {
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
        walletDelete.get().setStatus(0);
        walletService.save(walletDelete.get());
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
        Iterable<Wallet> walletIterable = walletService.findWalletsByAppUserIdAndStatus(user_id, 1);
        System.out.println(walletIterable);
        return new ResponseEntity<>(walletIterable, HttpStatus.OK);
    }
//@RequestMapping("/money")
    @GetMapping("money/{id}")
    public ResponseEntity<Long> sumWalletByUser(@PathVariable int id) {
       long walletOptional = walletService.sumMoneyWalletByUser(id);
        System.out.println(walletOptional);
        return new ResponseEntity<>(walletOptional, HttpStatus.OK);
    }
    @PostMapping("addmoney")
    public ResponseEntity<Wallet> addMoney(@RequestBody long money){

    }
}
