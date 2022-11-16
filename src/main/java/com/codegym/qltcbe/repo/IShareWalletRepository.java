package com.codegym.qltcbe.repo;

import com.codegym.qltcbe.model.entity.ShareWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IShareWalletRepository extends JpaRepository<ShareWallet, Long> {
    @Modifying
    @Query(value = "select s.wallet_id from share_wallet s join wallet w on s.wallet_id = w.id where s.app_user_id = ?1 and w.status=1", nativeQuery = true)
    List<Long> findListWalletShare(Long id);

    @Modifying
    @Query(value = "select app_user_id from share_wallet where wallet_id = ?1", nativeQuery = true)
    List<Long> findWhoWasShared(Long id);
}
