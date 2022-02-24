/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group13.budgets;

import java.util.*;
/**
 *
 * @author carterking
 */
public class Budget {
    private int id;
    private String month;
    private ArrayList<Category> spendingCategories;

    public Budget(String month, ArrayList<Category> categories) {
        this.id = -1; //set to -1 to indicate that this id has not been set yet 
        this.month = month;
        this.spendingCategories = categories;
    }
    
    public Category getCategory(String name){
        //Category category = null;
        for(Category category : spendingCategories) {
            if(category.getName().equals(name)) {
                System.out.println(name + " == " + category.getName());
                return category;
            }
            else {
                System.out.println("No category where name = " + name);
            }
        }
        return null;
    }
    
    public Category getCategoryByID(int id) {
        for(Category category : spendingCategories) {
            if(category.getId() == id) {
                
                return category;
            }
            else {
                System.out.println("No category where id = " + id);
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void addCategory(Category newCategory) {
        this.spendingCategories.add(newCategory);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public ArrayList<Category> getSpendingCategories() {
        return spendingCategories;
    }

    public void setSpendingCategories(ArrayList<Category> spendingCategories) {
        this.spendingCategories = spendingCategories;
    }

    @Override
    public String toString() {
        return "Budget{" + "month=" + month + ", spendingCategories=" + spendingCategories + '}';
    }
}
