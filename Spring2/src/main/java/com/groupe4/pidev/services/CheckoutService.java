package com.groupe4.pidev.services;

import com.groupe4.pidev.dto.PaymentInfo;
import com.groupe4.pidev.dto.Purchase;
import com.groupe4.pidev.dto.PurchaseResponse;
/*import com.luv2code.ecommerce.dto.PaymentInfo;
import com.luv2code.ecommerce.dto.Purchase;
import com.luv2code.ecommerce.dto.PurchaseResponse;*/
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}
