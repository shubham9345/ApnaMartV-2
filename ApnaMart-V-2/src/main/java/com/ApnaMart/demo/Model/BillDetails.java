package com.ApnaMart.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;
    @OneToOne
    @JoinColumn(name = "carts_id")
    @JsonIgnore
    private Cart carts;
    private int itemsTotals;
    private int deliveryCharge;
    private int handlingCharge;
    private int totalCharge;
}
