package com.codegym.qltcbe.service.payment;

import com.codegym.qltcbe.model.dto.IPaymentInADayDTO;
import com.codegym.qltcbe.model.dto.SumInDay;
import com.codegym.qltcbe.model.entity.Payment;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    Payment save(Payment payment);

    Iterable<Payment> findAll();

    Optional<Payment> findById(Long id);

    List<Payment> findAllByCategory_Id(Long id);

    Iterable<Payment> findAllByWallet_Id(Long id);

    Optional<Payment> remove(Long id);

    Iterable<Payment> findAllTransactionsDuringTime(String startDate, String endDate);

    Iterable<Payment> findAllTransactionsDuringTimeByWallet(String startDate, String endDate, Long id);

    Iterable<IPaymentInADayDTO> findAllTransactionsToday(@Param("user_id") Long id);
    Iterable<SumInDay> getSumMoney(@Param("user_id") Long id);

}
