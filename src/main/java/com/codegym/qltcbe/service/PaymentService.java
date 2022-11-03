package com.codegym.qltcbe.service;

import com.codegym.qltcbe.model.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PaymentService implements IPaymentService{

    @Autowired
    private IPaymentRepository paymentRepository;
    @Override
    public Payment save(Payment payment) {
        return null;
    }

    @Override
    public Iterable<Payment> findAll() {
        return null;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Payment> findAllByCategory_id(Long id) {
        return null;
    }

    @Override
    public Iterable<Payment> findAllByWallet_id(Long id) {
        return null;
    }

    @Override
    public Iterable<Payment> findAllByWallet(Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Iterable<Payment> findAllByMonthTimeAndYearTime(int status, String month, int id) {
        return null;
    }

    @Override
    public Iterable<Payment> findAllPaymentIncomeFor6Months(Long id, String presentTime, String sixMonthsAgo) {
        return null;
    }

    @Override
    public Iterable<Payment> findAllPaymentExpenseFor6Months(Long id, String presentTime, String sixMonthsAgo) {
        return null;
    }

    @Override
    public Iterable<Payment> findAllByPayment(String startTime, String endTime, Long status, Long from, Long to, Long id) {
        return null;
    }
}
