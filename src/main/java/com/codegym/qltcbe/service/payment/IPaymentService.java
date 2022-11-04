package com.codegym.qltcbe.service.payment;

import com.codegym.qltcbe.model.entity.Payment;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface IPaymentService {
    Payment save(Payment payment);

    Iterable<Payment> findAll();

    Optional<Payment> findById(Long id);



    Iterable<Payment> findAllByCategory_Id(Long id);

    Iterable<Payment> findAllByWallet_Id(Long id);
//

    void remove(Long id);



    Iterable<Payment> findAllByMonthTimeAndYearTime(int status, String month, int id);
//
//    Iterable<Payment> findAllPaymentIncomeFor6Months(Long id, String presentTime, String sixMonthsAgo);
//
//    Iterable<Payment> findAllPaymentExpenseFor6Months(Long id, String presentTime, String sixMonthsAgo);
//
//    Iterable<Payment> findAllByPayment(String startTime, String endTime, Long status, Long from, Long to, Long id);

}
