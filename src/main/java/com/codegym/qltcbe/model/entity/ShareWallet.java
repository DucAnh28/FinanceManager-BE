package com.codegym.qltcbe.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Wallet wallet;

    public ShareWallet(AppUser appUser, Wallet wallet) {
        this.appUser = appUser;
        this.wallet = wallet;
    }

}
