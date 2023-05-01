package com.groupe4.pidev.services;

import com.stripe.exception.StripeException;

public interface IPaymentRequestService {
    void processPayment(String cardNumber, Long amount) throws StripeException;
}
