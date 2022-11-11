package com.codegym.qltcbe.service.payment;

import com.codegym.qltcbe.model.entity.Payment;

import javax.crypto.spec.OAEPParameterSpec;
import java.time.LocalDate;
import java.util.Optional;

public interface IPaymentService {
    Payment save(Payment payment);

    Iterable<Payment> findAll();

    Optional<Payment> findById(Long id);



    Iterable<Payment> findAllByCategory_Id(Long id);

    Iterable<Payment> findAllByWallet_Id(Long id);
//
//    Iterable<Payment> findAllByWallet(Long id);

    void remove(Long id);

    Iterable<Payment> findAllTransactionsDuringTime(String startDate, String endDate);

    Iterable<Payment> findAllTransactionsDuringTimeByWallet(String startDate, String endDate, Long id);
}
