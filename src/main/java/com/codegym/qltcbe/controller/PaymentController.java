package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.entity.Payment;
import com.codegym.qltcbe.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;

//    @Autowired
//    private ICategoryService categoryService;
//
//    @Autowired
//    private IWalletService walletService;

    @GetMapping("find-by-wallet/{id}")
    public ResponseEntity<Iterable<Payment>> findAll(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.findAllByWallet_id(id), HttpStatus.OK);
    }


}
