package com.example.mycart;

import java.util.Objects;

public class Product {
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    private String name;

    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "상품{이름='" + name + "', 가격=" + price + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product tmp = (Product) o;
        // 이름만 기준으로 동일 여부 판단
        return name.equals(tmp.name);
    }




}
