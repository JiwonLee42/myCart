package com.example.mycart;

import java.io.*;
import java.util.*;

public class CartApp {

    /*
     방법2 : csv파일에서 불러와서 넣는 방법 사용
     */
    public Set<Product> readCsv() {
        Set<Product> productSet = new HashSet<>();
        try{
            File file = new File("src/main/java/com/example/mycart/products.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            br.readLine();
            while((line = br.readLine())!=null) {
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);
                String productName = lineArr[1];
                int price = Integer.parseInt(lineArr[2]);
                Product product = new Product(productName,price);
                productSet.add(product);
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productSet;
    }

    // 상품명을 기준으로 상품을 찾기
    public Product findProductByName(Set<Product> productSet, String name) {
        for (Product product : productSet) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
    public static void main(String[] args) {

        // csv 파일로부터 상품목록 불러올 수 있도록 수정
        CartApp app = new CartApp();

        // 상품 목록 생성
        Set<Product> productSet = app.readCsv();

        // 방법1: 상품 클래스를 생성하여 상품목록에 넣기
        /*
        Product prod1 = new Product("우유", 2000);
        Product prod2 = new Product("화장지", 1000);
        Product prod3 = new Product("책", 10000);

        productSet.add(prod1);
        productSet.add(prod2);
        productSet.add(prod3);

        // 상품 목록 확인
        System.out.println("고유한 상품 목록: ");
        for (Product product : productSet) {
            System.out.println(product.getName());
        }

        // 장바구니 생성
        Cart myCart = new Cart();


        // 상품 장바구니에 추가
        myCart.addProduct(prod1,3);
        myCart.addProduct(prod2,2);
        myCart.addProduct(prod3,1);

        myCart.showItems();

        // 상품 장바구니에서 제거
        myCart.removeProduct(prod1,3);
        myCart.removeProduct(prod2, 1);
        myCart.removeProduct(prod3,1);

        // 상품 장바구니에 현재 담긴 상품들 출력( 상품이름, 각 상품의 갯수 )
        myCart.showItems();

         */
        // 상품 목록 확인
        System.out.println("고유한 상품 목록: ");
        for (Product product : productSet) {
            System.out.println(product.getName());
        }

        Scanner scanner = new Scanner(System.in);

        // 장바구니 생성
        Cart myCart = new Cart();

        // 메뉴 반복문
        boolean running = true;
        while (running) {
            System.out.println("--------------------------------------------");
            System.out.println("|              CART SYSTEM                  |");
            System.out.println("--------------------------------------------");
            System.out.println("|            1. 장바구니에 상품 추가              |");
            System.out.println("|            2. 장바구니에서 상품 제거            |");
            System.out.println("|            3. 장바구니 상품 조회              |");
            System.out.println("|            x. 프로그램 종료                  |");
            System.out.println("--------------------------------------------");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();


            switch (choice) {
                case "1":
                    System.out.print("추가할 상품명을 입력하세요: ");
                    String addProductName = scanner.nextLine();
                    Product addProduct = app.findProductByName(productSet, addProductName);
                    if (addProduct != null) {
                        System.out.print("추가할 수량을 입력하세요: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        myCart.addProduct(addProduct, quantity);
                        System.out.println(quantity + "개의 " + addProduct.getName() + "이(가) 장바구니에 추가되었습니다.");
                    } else {
                        System.out.println("해당 상품을 찾을 수 없습니다.");
                    }
                    break;

                case "2":
                    System.out.print("제거할 상품명을 입력하세요: ");
                    String removeProductName = scanner.nextLine();
                    Product removeProduct = app.findProductByName(productSet, removeProductName);
                    if (removeProduct != null) {
                        System.out.print("제거할 수량을 입력하세요: ");
                        int removeQuantity = Integer.parseInt(scanner.nextLine());
                        myCart.removeProduct(removeProduct, removeQuantity);
                        System.out.println(removeQuantity + "개의 " + removeProduct.getName() + "이(가) 장바구니에서 제거되었습니다.");
                    } else {
                        System.out.println("해당 상품을 찾을 수 없습니다.");
                    }
                    break;

                case "3":
                    System.out.println("현재 장바구니 내용:");
                    myCart.showItems();
                    break;

                case "x":
                case "X":
                    running = false;
                    System.out.println("프로그램을 종료합니다.");
                    break;

                default:
                    System.out.println("잘못된 선택입니다. 다시 입력하세요.");
                    break;
            }
        }
        scanner.close();
    }
}
