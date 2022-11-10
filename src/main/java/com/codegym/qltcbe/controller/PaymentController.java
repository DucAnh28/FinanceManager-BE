package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.entity.Category;
import com.codegym.qltcbe.model.entity.Payment;
import com.codegym.qltcbe.model.entity.Wallet;
import com.codegym.qltcbe.service.category.ICategoryService;
import com.codegym.qltcbe.service.payment.IPaymentService;
import com.codegym.qltcbe.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
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
        Optional<Payment> optionalPayment = paymentService.findById(id);
        if (!optionalPayment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paymentService.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Payment>> findAll() {
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Payment> save(@RequestBody Payment payment) {
        return new ResponseEntity<>(paymentService.save(payment), HttpStatus.OK);
    }


    @PutMapping("{id}")
    private ResponseEntity<Payment> update(@PathVariable Long id, @RequestBody Payment payment) {
        Optional<Payment> optionalPayment = paymentService.findById(id);
        if (!optionalPayment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        payment.setId(id);
        return new ResponseEntity<>(paymentService.save(payment), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<Payment>> createPayment(@RequestBody Payment payment) {
        Optional<Wallet> wallet = walletService.findById(payment.getWallet().getId());
        Optional<Category> category = categoryService.findById(payment.getCategory().getId());
        payment.getCategory().setStatus(category.get().getStatus());
        paymentService.save(payment);
        if (payment.getCategory().getStatus() == 1) {
            wallet.get().setMoney(wallet.get().getMoney() + payment.getMoney());
            walletService.save(wallet.get());
        } else {
            wallet.get().setMoney(wallet.get().getMoney() - payment.getMoney());
            walletService.save(wallet.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Payment> removePayment(@PathVariable Long id) {
//        Optional<Payment> payment = paymentService.findById(id);
//        Optional<Wallet> editWallet = walletService.findById(payment.get().getWallet().getId());
//        editWallet.get().setId(payment.get().getWallet().getId());
//        if (payment.get().getCategory().getStatus() == 1) {
//            editWallet.get().setMoney(editWallet.get().getMoney() - payment.get().getMoney());
//        } else {
//            editWallet.get().setMoney(editWallet.get().getMoney() + payment.get().getMoney());
//        }
//        walletService.save(editWallet.get());
//        paymentService.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Payment> removePayment(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Payment>> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        Optional<Payment> editPayment = paymentService.findById(id);
        Optional<Wallet> wallet = walletService.findById(editPayment.get().getWallet().getId());
        payment.setId(id);
        int oldPayment = editPayment.get().getCategory().getStatus();
        int newPayment = payment.getCategory().getStatus();
        wallet.get().setId(wallet.get().getId());
        if ((oldPayment == 1) && (newPayment == 1)) {
            wallet.get().setMoney(wallet.get().getMoney() - editPayment.get().getMoney() + payment.getMoney());
        } else if ((oldPayment == 1) && (newPayment == 2)) {
            wallet.get().setMoney(wallet.get().getMoney() - editPayment.get().getMoney() - payment.getMoney());
        } else if ((oldPayment == 2) && (newPayment == 1)) {
            wallet.get().setMoney(wallet.get().getMoney() + editPayment.get().getMoney() + payment.getMoney());
        } else {
            wallet.get().setMoney(wallet.get().getMoney() + editPayment.get().getMoney() - payment.getMoney());
        }
        paymentService.save(payment);
        walletService.save(wallet.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("find-all-by-time2")
//    public ResponseEntity<Iterable<Payment>> findAllByMonthTimeAndYearTime(@RequestParam("id") int id) {
//        String month = String.valueOf(YearMonth.now());
//        return new ResponseEntity<>(paymentService.findAllByMonthTimeAndYearTime(2, month, id), HttpStatus.OK);
//    }


}
