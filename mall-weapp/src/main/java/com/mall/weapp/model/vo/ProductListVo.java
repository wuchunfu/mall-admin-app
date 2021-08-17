package com.mall.weapp.model.vo;

import com.mall.weapp.model.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductListVo {

    private Long id;
    private String imageUrl;
    private String title;
    private Double price;

    public ProductListVo() {}
    public ProductListVo(Product product) {
        this.id = product.getId();
        this.imageUrl = product.getImageUrl();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
