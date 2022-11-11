package com.codegym.qltcbe.repo;

import com.codegym.qltcbe.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

//    @Query(value = "select * from `financial_management`.payment where wallet_id=? order by id DESC;", nativeQuery = true)
//    Iterable<Payment> findAllByWallet_Id(Long id);

//    Iterable<Payment> findAllByCategory_Id(Long id);

    Iterable<Payment> findAllByWallet_Id(Long wallet_id);

    Iterable<Payment> findAllByCategory_Id(Long category_id);

    @Query(value = "select *" +
            "from payment p\n" +
            "    join category c on c.id = p.category_id\n" +
            "    join wallet w on p.wallet_id = w.id\n" +
            " where date BETWEEN CAST(:startDate AS DATE) AND CAST(:endDate AS DATE)", nativeQuery = true)
    Iterable<Payment> findAllTransactionsDuringTime(@Param("startDate") String startDate, @Param("endDate") String endDate);


    @Query(value = "select * from payment p\n" +
            "    join category c on c.id = p.category_id\n" +
            "    join wallet w on p.wallet_id = w.id\n" +
            "where date BETWEEN CAST(:startDate AS DATE) AND CAST(:endDate AS DATE)\n"
            + " and wallet_id = :wallet_id", nativeQuery = true)
    Iterable<Payment> findAllTransactionDuringTimeByWallet(@Param("startDate") String startDate,
                                                           @Param("endDate") String endDate,
                                                           @Param("wallet_id") Long id);
}
