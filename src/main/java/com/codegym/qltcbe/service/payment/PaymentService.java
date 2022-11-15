package com.codegym.qltcbe.service.payment;

import com.codegym.qltcbe.model.entity.Payment;
import com.codegym.qltcbe.repo.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Iterable<Payment> findAllByCategory_Id(Long category_id) {
        return paymentRepository.findAllByCategory_Id(category_id);
    }

    @Override
    public Iterable<Payment> findAllByWallet_Id(Long wallet_id) {
        return paymentRepository.findAllByWallet_Id(wallet_id);
    }


    @Override
    public Optional<Payment> remove(Long id) {
        paymentRepository.deleteById(id);
        return Optional.empty();
    }

    @Override
    public Iterable<Payment> findAllTransactionsDuringTime(String startDate, String endDate) {
        return paymentRepository.findAllTransactionsDuringTime(startDate, endDate);
    }

    @Override
    public Iterable<Payment> findAllTransactionsDuringTimeByWallet(String startDate, String endDate, Long id) {
        return paymentRepository.findAllTransactionsDuringTimeByWallet(startDate, endDate, id);
    }

    @Override
    public Iterable<Payment> findAllTransactionsToday() {
        return paymentRepository.findAllTransactionsToday();
    }

}
