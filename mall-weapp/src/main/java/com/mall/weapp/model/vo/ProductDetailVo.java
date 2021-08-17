package com.mall.weapp.model.vo;

import com.mall.weapp.model.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDetailVo {

    private String imageUrl;
    private String title;
    private Double price;
    private Integer amount;
    private Integer sales;
    private String sendAddress;
    private String parcelType;
    private String salesService;

    public ProductDetailVo() {}
    public ProductDetailVo(Product product) {
        this.imageUrl = product.getImageUrl();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.sales = product.getSales();
        this.sendAddress = product.getSendAddress();
        this.parcelType = product.getParcelType();
        this.salesService = product.getSalesService();
    }
}
