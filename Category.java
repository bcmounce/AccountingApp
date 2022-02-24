/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group13.budgets;


/**
 *
 * @author carterking
 */
public class Category {
    private int id;
    private String name;
    private int amtAllocated;
    private int amtSpentTD;
    private int amtRemaining;

    public Category(String name, int amtAllocated, int amtSpentTD) {
        this.id = -1;
        this.name = name;
        this.amtAllocated = amtAllocated;
        this.amtSpentTD = amtSpentTD;
        this.amtRemaining = amtAllocated - amtSpentTD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getAmtAllocated() {
        return amtAllocated;
    }

    public void setAmtAllocated(int amtAllocated) {
        this.amtAllocated = amtAllocated;
    }

    public int getAmtSpentTD() {
        return amtSpentTD;
    }

    public void setAmtSpentTD(int amtSpentTD) {
        this.amtSpentTD = amtSpentTD;
        this.amtRemaining = this.amtAllocated - amtSpentTD;
    }

    public int getAmtRemaining() {
        return amtRemaining;
    }

    public void setAmtRemaining(int amtRemaining) {
        this.amtRemaining = amtRemaining;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", amtAllocated=" + amtAllocated + ", amtSpentTD=" + amtSpentTD + ", amtRemaining=" + amtRemaining + '}';
    }
}
