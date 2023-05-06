package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.PaymentRequest;
import com.groupe4.pidev.entities.PaymentResponse;
import com.groupe4.pidev.repositories.PaymentRequestRepo;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;




@Service
public class PaymentRequestService implements IPaymentRequestService{
    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    public PaymentResponse processPayment(PaymentRequest paymentRequest) throws StripeException {
        // Configure la clé d'API de Stripe
        Stripe.apiKey = stripeApiKey;

        // Crée les paramètres de la charge
        ChargeCreateParams chargeParams = ChargeCreateParams.builder()
                .setAmount(paymentRequest.getAmount())
                .setCurrency(paymentRequest.getCurrency())
                .setDescription(paymentRequest.getDescription())
                .setSource(paymentRequest.getCardToken())
                .build();

        // Effectue la charge via l'API de Stripe
        Charge charge = Charge.create(chargeParams);

        // Récupère les détails de la charge réussie
        String transactionId = charge.getId();
        String status = charge.getStatus();
        String Id_paymentResponse= charge.getId();
        Long amount=charge.getAmount();
        String currency=charge.getCurrency();

        // Crée la réponse de paiement
        PaymentResponse paymentResponse = new PaymentResponse(Id_paymentResponse,transactionId, status,amount,currency);

        // Effectue des opérations supplémentaires basées sur la réponse de paiement

        // Retourne la réponse de paiement
        return paymentResponse;
    }

    @Override
    public void processPayment(String cardNumber, Long amount) throws StripeException {

    }
}

