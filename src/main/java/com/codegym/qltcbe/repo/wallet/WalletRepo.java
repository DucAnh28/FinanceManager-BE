package com.codegym.qltcbe.repo.wallet;

import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {
    Iterable<Wallet> findWalletsByAppUserId(Long user_id);

}
