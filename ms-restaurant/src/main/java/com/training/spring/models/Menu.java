package com.training.spring.models;

import java.util.List;

public class Menu {

    private List<String> meals;
    //@Size(min = 10, max = 11)
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
