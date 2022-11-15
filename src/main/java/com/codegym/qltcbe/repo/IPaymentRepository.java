package com.codegym.qltcbe.repo;

import com.codegym.qltcbe.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

    @Query
    Iterable<Payment> findAllByWallet_Id(Long wallet_id);


    Iterable<Payment> findAllByCategory_Id(Long category_id);
}
