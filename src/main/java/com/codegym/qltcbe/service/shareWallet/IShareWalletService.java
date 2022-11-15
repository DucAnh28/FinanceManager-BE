package com.codegym.qltcbe.service.shareWallet;

import com.codegym.qltcbe.model.entity.ShareWallet;
import com.codegym.qltcbe.service.IGeneralService;

import java.util.List;

public interface IShareWalletService extends IGeneralService<ShareWallet> {
    List<Long> findListWalletShare(Long id);

    List<Long> findWhoWasShared(Long id);





}
