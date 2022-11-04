package com.codegym.qltcbe.service.wallet;

import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.model.entity.Wallet;
import com.codegym.qltcbe.service.IGeneralService;


public interface IWalletService extends IGeneralService<Wallet> {


    Iterable<Wallet> findWalletsByAppUserId (Long user_id);

}
