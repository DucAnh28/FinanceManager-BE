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

    Iterable<Payment> findAllByMonthTimeAndYearTime(int status, String month, int id);

    Iterable<Payment> findAllTransactionsDuringTime(String startTime, String endTime);
}
