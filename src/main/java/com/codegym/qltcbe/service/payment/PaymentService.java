package com.codegym.qltcbe.service.payment;

import com.codegym.qltcbe.model.entity.Payment;
import com.codegym.qltcbe.repo.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;
    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Iterable<Payment> findAllByCategory_Id(Long id) {
        return paymentRepository.findAllByCategory_Id(id);
    }

    @Override
    public Iterable<Payment> findAllByWallet_Id(Long wallet_id) {
        return paymentRepository.findAllByWallet_Id(wallet_id);
    }


    @Override
    public void remove(Long id) {
         paymentRepository.deleteById(id);
    }

    @Override
    public Iterable<Payment> findAllTransactionsDuringTime(String startTime, String endTime) {
        return paymentRepository.findAllTransactionsDuringTime(startTime,endTime);
    }

}
