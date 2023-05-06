package com.groupe4.pidev.repositories;



import com.groupe4.pidev.entities.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRequestRepo extends JpaRepository<PaymentRequest,Long> {

}
