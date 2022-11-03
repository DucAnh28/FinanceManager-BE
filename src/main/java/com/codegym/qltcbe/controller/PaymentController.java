package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.entity.Payment;
import com.codegym.qltcbe.service.category.ICategoryService;
import com.codegym.qltcbe.service.payment.IPaymentService;
import com.codegym.qltcbe.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IWalletService walletService;


    @GetMapping("find-by-wallet/{id}")
    public ResponseEntity<Iterable<Payment>> findAll(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.findAllByWallet_Id(id), HttpStatus.OK);
    }

    @GetMapping("find-by-category/{id}")
    public ResponseEntity<Iterable<Payment>> findAllByCategory_Id(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.findAllByCategory_Id(id), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) {
        Optional<Payment> optionalTransaction = paymentService.findById(id);
        if (!optionalTransaction.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paymentService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Payment> save(@RequestBody Payment payment) {
        return new ResponseEntity<>(paymentService.save(payment), HttpStatus.OK);
    }
}

//    @PutMapping("{id}")
//    private ResponseEntity<Transaction> update(@PathVariable Long id, @RequestBody Transaction transaction) {
//        Optional<Transaction> optionalTransaction = transactionService.findById(id);
//        if (!optionalTransaction.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        transaction.setId(id);
//        return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.OK);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<Optional<Transaction>> createTransaction(@RequestBody Transaction transaction) {
//        Optional<Wallet> wallet = walletService.findById(transaction.getWallet().getId());
//        Optional<Category> category = categoryService.findById(transaction.getCategory().getId());
//        transaction.getCategory().setStatus(category.get().getStatus());
//        transactionService.save(transaction);
//        if (transaction.getCategory().getStatus() == 1) {
//            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() + transaction.getTotalSpent());
//            walletService.save(wallet.get());
//        } else {
//            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() - transaction.getTotalSpent());
//            walletService.save(wallet.get());
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Transaction> removeTransaction(@PathVariable Long id) {
//        Optional<Transaction> transaction = transactionService.findById(id);
//        Optional<Wallet> editWallet = walletService.findById(transaction.get().getWallet().getId());
//        editWallet.get().setId(transaction.get().getWallet().getId());
//        if (transaction.get().getCategory().getStatus() == 1) {
//            editWallet.get().setMoneyAmount(editWallet.get().getMoneyAmount() - transaction.get().getTotalSpent());
//        } else {
//            editWallet.get().setMoneyAmount(editWallet.get().getMoneyAmount() + transaction.get().getTotalSpent());
//        }
//        walletService.save(editWallet.get());
//        transactionService.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Optional<Transaction>> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
//        Optional<Transaction> editTransaction = transactionService.findById(id);
//        Optional<Wallet> wallet = walletService.findById(editTransaction.get().getWallet().getId());
//        transaction.setId(id);
//        int oldTransaction = editTransaction.get().getCategory().getStatus();
//        int newTransaction = transaction.getCategory().getStatus();
//        wallet.get().setId(wallet.get().getId());
//        if ((oldTransaction == 1) && (newTransaction == 1)) {
//            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() - editTransaction.get().getTotalSpent() + transaction.getTotalSpent());
//        } else if ((oldTransaction == 1) && (newTransaction == 2)) {
//            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() - editTransaction.get().getTotalSpent() - transaction.getTotalSpent());
//        } else if ((oldTransaction == 2) && (newTransaction == 1)) {
//            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() + editTransaction.get().getTotalSpent() + transaction.getTotalSpent());
//        } else {
//            wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() + editTransaction.get().getTotalSpent() - transaction.getTotalSpent());
//        }
//        transactionService.save(transaction);
//        walletService.save(wallet.get());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//}
