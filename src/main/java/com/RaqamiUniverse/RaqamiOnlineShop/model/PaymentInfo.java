package com.RaqamiUniverse.RaqamiOnlineShop.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PaymentInfo {
    private Long id;
    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
