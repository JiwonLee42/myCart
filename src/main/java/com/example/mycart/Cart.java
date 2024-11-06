package com.example.mycart;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    public Map<Product,Integer> Items = new HashMap<>();

    public void showItems() {
       Items.forEach(((product, integer) -> {
           System.out.println(product.getName() + " : " + integer + " 개");
       }));
    }

    public void addProduct(Product product, int num) {
        Items.put(product, Items.getOrDefault(product, 0) + num);
    }

    public void removeProduct(Product product, int num) {
        if(!(Items.containsKey(product))){
            System.out.println("상품이 없습니다. ");
        }
        else if (Items.get(product) >= num) {
            Items.put(product, Items.get(product) - num);
            System.out.println("상품: " + product.getName() + "이 " + num + "개 삭제되었습니다. ");
        }
        else{
            System.out.println("상품의 수량이 입력한 값보다 적습니다. ");
        }
    }

}
