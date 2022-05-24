package com.controller;

import com.entities.ProductEntity;

import java.util.ArrayList;

public class CartBean {
    private ArrayList list = new ArrayList();
    private double total;
    private double tong;
    private int quantity;
    private ArrayList<CartItemBean> list1 = new ArrayList();

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getLineItemCount() {
        return list.size();
    }

    public double getTong() {
        return tong;
    }

    public void setTong(double tong) {
        this.tong = tong;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<CartItemBean> getList1() {
        return list1;
    }

    public void setList1(ArrayList<CartItemBean> list1) {
        this.list1 = list1;
    }

    public void deleteCart(String stt) {
        int iSTT = 0;
        try {
            iSTT = Integer.parseInt(stt);
            list.remove(iSTT - 1);
            calculateOrderTotal();
        } catch (NumberFormatException nfe) {
            System.out.println("Error while deleting cart item: " + nfe.getMessage());
            nfe.printStackTrace();
        }
    }

    public void updateCart(String stt, String quantity) {
        int iSTT = Integer.parseInt(stt);
        CartItemBean cartItem = (CartItemBean) list.get(iSTT - 1);
        double iPrice = cartItem.getPrice();
        int iQuantity = Integer.parseInt(quantity);
        try {
            if (iQuantity > 0) {
                cartItem.setQuantity(iQuantity);
                cartItem.setTotalCost(iPrice * iQuantity);
                calculateOrderTotal();
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error while updating cart: " + nfe.getMessage());
            nfe.printStackTrace();
        }

    }

    public void addCart(String description, String price, String quantity, ProductEntity product, String image) {
        double iPrice = Double.parseDouble(price);
        int iQuantity = Integer.parseInt(quantity);
        String iImage = image;
        CartItemBean cartItem = new CartItemBean();

        if (!list.isEmpty()) {
            int ExistedIndex = -1;
            int size = list.size();
            //Tim vi tri san pham neu da ton tai
            for (int i = 0; i < size; i++) {
                CartItemBean cartItemBean = (CartItemBean) list.get(i);
                System.out.println(cartItemBean.getProductEntity());

                if (cartItemBean.getProductEntity().getId() == product.getId()) {
                    System.out.println("= voi cai truoc");
                    ExistedIndex = i;
                    cartItem = cartItemBean;
                }
            }

            if (ExistedIndex != -1) {
                //Neu ton tai thi cap nhat lineItem cu
                cartItem.setQuantity(cartItem.getQuantity() + iQuantity);
                list.set(ExistedIndex, cartItem);
            } else {
                //Neu chua ton tai thi them lineItem moi
                cartItem.setProductEntity(product);
                cartItem.setDescription(product.getName());
                cartItem.setPrice(product.getPrice());
                cartItem.setImage(iImage);
                cartItem.setQuantity(iQuantity);
                cartItem.setTotalCost(product.getPrice() * iQuantity);
                list.add(cartItem);
                calculateOrderTotal();
            }
        } else {
            cartItem.setProductEntity(product);
            cartItem.setDescription(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setImage(iImage);
            cartItem.setQuantity(iQuantity);
            cartItem.setTotalCost(product.getPrice() * iQuantity);
            list.add(cartItem);
            calculateOrderTotal();
        }
    }
    protected void calculateOrderTotal() {
        double plus = 0;
        int quan = 0;
        for (int i = 0; i < list.size(); i++) {
            CartItemBean cartItem = (CartItemBean) list.get(i);
            plus += cartItem.getTotalCost();
            quan += cartItem.getQuantity();
        }
        setTotal(plus);
        setTong(plus + 50);
        setQuantity(quan);
    }
}
