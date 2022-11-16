package com.codegym.qltcbe.model.dto;

import java.time.LocalDate;

public interface IPaymentInADayDTO {
    Long getId();
    String getPayment();
    Long getMoney();
    LocalDate getDate();
    String getDescription();
    String getWalletName();
}
