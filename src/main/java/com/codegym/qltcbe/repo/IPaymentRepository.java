package com.codegym.qltcbe.repo;

import com.codegym.qltcbe.model.dto.IPaymentInADayDTO;
import com.codegym.qltcbe.model.dto.SumInDay;
import com.codegym.qltcbe.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

    @Query
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
            "    where date BETWEEN CAST(:startDate AS DATE) AND CAST(:endDate AS DATE)\n" +
            "      and wallet_id = :wallet_id and p.status = 1", nativeQuery = true)
    Iterable<Payment> findAllTransactionsDuringTimeByWallet(@Param("startDate") String startDate,
                                                            @Param("endDate") String endDate,
                                                            @Param("wallet_id") Long id);

    @Query(value = "select p.id, p.name as payment, p.money, p.date, p.description, w.name as walletName from payment p\n" +
            "    join wallet w on p.wallet_id = w.id\n" +
            "    where date = curdate() and p.user_id = :user_id",  nativeQuery = true)
    Iterable<IPaymentInADayDTO> findAllTransactionsToday(@Param("user_id") Long id);

    @Query(value = "select p.id, p.name as payment, p.money, p.date, p.description, w.name from payment p\n" +
            "    join wallet w on p.wallet_id = w.id\n" +
            "    where date = curdate() and p.user_id = :user_id and wallet_id = :wallet_id", nativeQuery = true)
    Iterable<IPaymentInADayDTO> findAllTransactionsTodayByWallet(@Param("user_id") Long user_id, @Param("wallet_id") Long wallet_id);

    @Query(nativeQuery = true, value = "select sum(p.money) as total\n" +
            "from payment p\n" +
            "    join wallet w on w.id = p.wallet_id = w.id\n" +
            "    join users u on p.user_id = u.id\n" +
            "where p.date = curdate() and u.id = ?")
    Iterable<SumInDay> getSumInDay(Long id);
}
