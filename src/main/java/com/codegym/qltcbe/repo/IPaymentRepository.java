package com.codegym.qltcbe.repo;

import com.codegym.qltcbe.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
