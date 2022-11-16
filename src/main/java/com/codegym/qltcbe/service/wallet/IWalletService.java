package com.codegym.qltcbe.service.wallet;
import com.codegym.qltcbe.model.entity.Wallet;
import com.codegym.qltcbe.service.IGeneralService;
import org.springframework.web.bind.annotation.PathVariable;

public interface IWalletService extends IGeneralService<Wallet> {

    Iterable<Wallet> findWalletsByAppUserIdAndStatus (Long user_id,int status);
    long sumMoneyWalletByUser(@PathVariable int id,int status);
    long addMoney(Long id, long money);

}
