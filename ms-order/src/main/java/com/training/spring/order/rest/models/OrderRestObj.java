package com.training.spring.order.rest.models;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class OrderRestObj {

    @NotEmpty
    @Size(min = 2, max = 20, message = "name 2 ile 20 arasında olmalı")
    //@StartWith("n:")
    private String       name;
    @NotEmpty
    @Size(min = 3, max = 30, message = "surname {min} ile {max} arasında olmalı")
    //@StartWith("s:")
    private String       surname;
    @NotEmpty
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$")
    private String       number;
    @Size(min = 1, message = "en az {min} tane yemek olmalı")
    private List<String> meals;
    @Max(360)
    @Min(20)
    private Integer      deliveryPeriod;

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
