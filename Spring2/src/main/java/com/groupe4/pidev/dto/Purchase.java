package com.groupe4.pidev.dto;

import com.groupe4.pidev.entities.Address;
import com.groupe4.pidev.entities.Order;
import com.groupe4.pidev.entities.OrderItem;
import com.groupe4.pidev.entities.User;
/*import com.luv2code.ecommerce.entity.Address;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderItem;*/
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private User customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
