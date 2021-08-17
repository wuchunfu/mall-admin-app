package com.mall.weapp.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartVo {

    private List<CartItemVo> cartItem;
    private Double totalPrice;
}
