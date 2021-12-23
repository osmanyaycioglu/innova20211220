package com.training.spring.order.integration.models;

import java.util.List;

public class Menu {

    private List<String> meals;
    private String       phoneNumber;

    public List<String> getMeals() {
        return this.meals;
    }

    public void setMeals(final List<String> mealsParam) {
        this.meals = mealsParam;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumberParam) {
        this.phoneNumber = phoneNumberParam;
    }


}
