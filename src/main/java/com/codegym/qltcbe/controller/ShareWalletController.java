package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.model.entity.Notification;
import com.codegym.qltcbe.model.entity.ShareWallet;
import com.codegym.qltcbe.model.entity.Wallet;
import com.codegym.qltcbe.service.notification.NotificationService;
import com.codegym.qltcbe.service.shareWallet.IShareWalletService;
import com.codegym.qltcbe.service.user.UserService;
import com.codegym.qltcbe.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/share")
@CrossOrigin("*")
public class ShareWalletController {
    @Autowired
    private IShareWalletService shareWalletService;
    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Wallet>> findALlSharedWallet(@PathVariable Long userId) {
        List<Wallet> wallets = new ArrayList<>();
        List<Long> list = shareWalletService.findListWalletShare(userId);
        for (int i = 0; i < list.size(); i++) {
            wallets.add(walletService.findById(list.get(i)).get());
        }
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{shareId}")
    public ResponseEntity<ShareWallet> delete(@PathVariable Long shareId) {
        this.shareWalletService.remove(shareId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("create/{walletId}")
    public ResponseEntity<ShareWallet> createShareWallet(@PathVariable Long walletId, @RequestParam(name = "username") String username) {
        List<AppUser> appUsers = (List<AppUser>) userService.findAll();
        for (AppUser appUser : appUsers) {

            if (appUser.getUsername().equals(username)) {
                AppUser user1= userService.getUserByUsername(username);
                Wallet wallet = walletService.findById(walletId).get();
                if (wallet.getAppUser().getId() == user1.getId()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                ShareWallet shareWallet = new ShareWallet(user1, wallet);
                List<ShareWallet> shareWallets = (List<ShareWallet>) shareWalletService.findAll();
                for (ShareWallet shareWallet1 : shareWallets) {
                    if (shareWallet1.getWallet().getId() == shareWallet.getWallet().getId() && shareWallet1.getAppUser().getId() == shareWallet.getAppUser().getId()) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }
                Notification n = new Notification(
                        wallet.getAppUser().getUsername() + " đã chia sẻ ví "+ wallet.getName()+ " với bạn",
                        wallet.getAppUser(),
                        user1
                );
                notificationService.save(n);
                shareWalletService.save(shareWallet);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return null;
    }

//    @PostMapping("/create/{walletId}")
//    public ResponseEntity<ShareWallet> createShareWallet(@PathVariable Long walletId, @RequestBody AppUser appUser) {
//        Wallet wallet = walletService.findById(walletId).get();
//        if (wallet.getAppUser().getId() == appUser.getId()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        ShareWallet shareWallet = new ShareWallet(appUser, wallet);
//        List<ShareWallet> shareWallets = (List<ShareWallet>) shareWalletService.findAll();
//        for (ShareWallet shareWallet1 : shareWallets) {
//            if (shareWallet1.getWallet().getId() == shareWallet.getWallet().getId() && shareWallet1.getAppUser().getId() == shareWallet.getAppUser().getId()) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        }
//        return null;
//    }
}

