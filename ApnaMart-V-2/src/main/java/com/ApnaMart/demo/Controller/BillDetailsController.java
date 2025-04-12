package com.ApnaMart.demo.Controller;

import com.ApnaMart.demo.Model.BillDetails;
import com.ApnaMart.demo.Service.BillDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BillDetailsController {
    @Autowired
    private BillDetailsService billDetailsService;
    @GetMapping("/bill/{cartId}")
    public ResponseEntity<?> getBillDetails(@PathVariable Long cartId, @RequestParam int handlingCharge,@RequestParam int deliveryCharge){
        BillDetails billDetails = billDetailsService.getBillsByCartId(cartId,handlingCharge,deliveryCharge);
        return new ResponseEntity<>(billDetails, HttpStatus.OK);
    }
}
