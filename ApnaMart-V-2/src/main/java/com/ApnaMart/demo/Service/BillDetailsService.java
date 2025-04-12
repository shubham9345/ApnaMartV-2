package com.ApnaMart.demo.Service;

import com.ApnaMart.demo.Model.BillDetails;
import com.ApnaMart.demo.Model.Cart;
import com.ApnaMart.demo.Model.Product;
import com.ApnaMart.demo.Repository.BillDetailsRepository;
import com.ApnaMart.demo.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillDetailsService {
    @Autowired
    private BillDetailsRepository billDetailsRepository;
    @Autowired
    private CartRepository cartRepository;

    public BillDetails getBillsByCartId(Long cartId, int handlingCharge, int delieveryCharge) {
        if (!cartRepository.existsById(cartId)) {
            throw new RuntimeException("CartId is not exist");
        }
        Optional<Cart> cart = cartRepository.findById(cartId);
        List<Product> products = cart.get().getProduct();
        int price = 0;
        for (int i = 0; i < products.size(); i++) {
            price += products.get(i).getPrice();
        }
        int total = price + handlingCharge + delieveryCharge;
        BillDetails billDetails = cart.get().getBillDetails();
        if (billDetails == null) {
            BillDetails billDetails1 = new BillDetails();
            billDetails1.setItemsTotals(price);
            billDetails1.setCarts(cart.get());
            billDetails1.setDeliveryCharge(delieveryCharge);
            billDetails1.setHandlingCharge(handlingCharge);
            billDetailsRepository.save(billDetails);
            billDetails.setTotalCharge(total);
            cartRepository.save(cart.get());
            return billDetails1;
        }
        billDetails.setItemsTotals(price);
        billDetails.setCarts(cart.get());
        billDetails.setDeliveryCharge(delieveryCharge);
        billDetails.setHandlingCharge(handlingCharge);
        billDetailsRepository.save(billDetails);
        billDetails.setTotalCharge(total);
        cartRepository.save(cart.get());
        return billDetails;

    }

}
