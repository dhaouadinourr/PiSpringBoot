package com.groupe4.pidev.controllers;
import com.groupe4.pidev.entities.PaymentRequest;
import com.groupe4.pidev.entities.PaymentResponse;
import com.groupe4.pidev.services.PaymentRequestService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class PaymentRequestController {
    @Autowired
    private PaymentRequestService paymentRequestService;

    @PostMapping("/paymentRequest")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            PaymentResponse paymentResponse = paymentRequestService.processPayment(paymentRequest);
            // Gérer la réponse de paiement et renvoyer une réponse appropriée
            System.out.println(paymentResponse.getTransactionId());
            System.out.println(paymentResponse.getStatus());
            return ResponseEntity.ok().body(paymentResponse);
        } catch (StripeException e) {
            // Gérer les erreurs Stripe et renvoyer une réponse d'erreur appropriée
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
