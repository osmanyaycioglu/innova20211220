package com.training.spring.order.models;

import java.util.List;

public class Order {

    private Long         orderId;
    private String       name;
    private String       surname;
    private String       number;
    private List<String> meals;
    private Integer      deliveryPeriod;
    private Integer      amount;

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final Long orderIdParam) {
        this.orderId = orderIdParam;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String nameParam) {
        this.name = nameParam;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surnameParam) {
        this.surname = surnameParam;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(final String numberParam) {
        this.number = numberParam;
    }

    public List<String> getMeals() {
        return this.meals;
    }

    public void setMeals(final List<String> mealsParam) {
        this.meals = mealsParam;
    }

    public Integer getDeliveryPeriod() {
        return this.deliveryPeriod;
    }

    public void setDeliveryPeriod(final Integer deliveryPeriodParam) {
        this.deliveryPeriod = deliveryPeriodParam;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(final Integer amountParam) {
        this.amount = amountParam;
    }


}
