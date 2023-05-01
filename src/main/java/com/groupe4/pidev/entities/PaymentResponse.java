package com.groupe4.pidev.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String Id_paymentResponse ;
    String transactionId;
    String status;
    Long amount;
    String currency;
}
