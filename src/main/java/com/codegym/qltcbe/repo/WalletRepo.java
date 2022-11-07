package com.codegym.qltcbe.repo;

import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {
    Iterable<Wallet> findWalletsByAppUserIdAndStatus (Long user_id,int status);

    @Query(nativeQuery = true,value = "select SUM(money) from wallet where app_user_id=?1 and status=1; ")
    long sumMoneyWalletByUser(@PathVariable int id);

}
