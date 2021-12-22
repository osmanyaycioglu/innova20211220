package com.training.spring.order.rest.models;

import java.util.List;

public class OrderRestObj {

    private String       name;
    private String       surname;
    private String       number;
    private List<String> meals;

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

    @Override
    public String toString() {
        return "OrderRestObj [name="
               + this.name
               + ", surname="
               + this.surname
               + ", number="
               + this.number
               + ", meals="
               + this.meals
               + "]";
    }


}
