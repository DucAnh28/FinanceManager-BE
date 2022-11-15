package com.codegym.qltcbe.model.entity;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class ShareWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private Wallet wallet;

    private Boolean canEdit;

    public ShareWallet(AppUser user, Wallet wallet) {
        this.user = user;
        this.wallet = wallet;
    }
}
