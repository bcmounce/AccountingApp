/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group13.budgets;

import java.sql.*;
import java.util.*;

/**
 *
 * @author carterking
 */
public class BudgetDAO {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/budgetsdb";
    //static final String DB_URL = "jdbc:mysql://cbking3@uisacad.uis.edu:3306/usr/bin/mysql/budgetsdb";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "app";          //ADD YOUR DB USERNAME HERE
    static final String DB_PASSWD = "0";
    
    /**
     * Get an ArrayList of all the budget ID's currently in the DB
     * @return ArrayList of budget Id's
     */
    public ArrayList<Integer> getIDList() {
        String query = "SELECT * FROM Budget";
        
        ArrayList<Integer> IDList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                IDList.add(resultSet.getInt("Pk_Budget_Id"));
            }
            
            //System.out.println(IDList);
            return IDList;
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }finally{
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }
    /**
     * Get the number of records in the Budget table. (Number of budget objects currently saved)
     * @return an integer (count) representing the number of budgets. Returns -1 if there is an error.
     */
    public int getNumBudgets(){
        String query = "SELECT * FROM Budget";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            int count = 0;
            while(resultSet.next()) {
                count ++;
            }

            return count;
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }finally{
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
        return -1;
    }
    
    /**
     * This inserts category data in the DB and returns the generated key.
     * @param budgetID the id number of the budget.
     * @param category the category object to be inserted.
     * @return the Pk_Category_Id generated from the INSERT statement.
     */
    public int insertSingleCategory(int budgetID, Category category){
        String name = category.getName();
        int amtAlloc = category.getAmtAllocated();
        int amtSpentTD = category.getAmtSpentTD();
        String query = "INSERT INTO Category (Name, Amt_Allocated, Amt_Spent_Td, Fk_Budget_Id) VALUES ('" + name + "', '" + amtAlloc + "', '" + amtSpentTD + "', '" + budgetID + "')";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        //test data to build categ
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt(1);
            
            return generatedKey;
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }finally{
            try {
                statement.close();
                connection.close();
                resultSet.close();
            } catch (SQLException ex) {
            }
        }
        return -1;
    }
    
    /**
     * Deletes a category row from the DB.
     * @param categoryID the Pk_Category_Id of the row to be deleted
     */
    public void deleteSingleCategory(int categoryID){

        String query = "DELETE FROM Category WHERE Pk_Category_Id='" + categoryID + "'";
        Connection connection = null;
        Statement statement = null;
        
        //test data to build categ
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
    }
    /**
     * This function calls UPDATA on a single category object.
     * @param budgetID
     * @param category 
     */
    public void updateSingleCategory(int budgetID, Category category){
        
        String query ="UPDATE Category SET Name='" + category.getName() + "', Amt_Allocated='" + category.getAmtAllocated() + "', Amt_Spent_Td='" + category.getAmtSpentTD() + "', Fk_Budget_Id='" + budgetID + "' WHERE Pk_Category_Id='" + category.getId() + "'";
        Connection connection = null;
        Statement statement = null;
        
        //test data to build categ
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
    }
    /**
     * Gets a budget object including associated category objects from the DB.
     * @param budgetID ID of budget object to fetch from DB
     * @return Budget object instantiated with data from DB
     */
    public Budget getBudget(int budgetID) {
        String query = "SELECT month FROM Budget WHERE Pk_Budget_Id=" + budgetID;
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        //test data to build category object
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            String budgetMonth = resultSet.getString(1);
            
            
            CategoryDAO dao = new CategoryDAO();
            
            Budget budget = new Budget(budgetMonth, dao.getCategories(budgetID, connection, statement, resultSet));
            budget.setId(budgetID);
            
            return budget;
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }finally{
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }
    /**
     * Inserts the data of a budget object in the DB.
     * This function should only be used to save a budget object if the budget object has never been saved to the DB before.
     * @param budget
     * @return Unique id that was generated by the DB
     */
    public int saveBudget(Budget budget) {
        
        String query = "INSERT INTO Budget (Month) VALUES ('" + budget.getMonth() + "')";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        //test data to build category object
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt(1);
            
            //CategoryDAO dao = new CategoryDAO();
            //dao.saveCategories(budget.getSpendingCategories(), connection, generatedKey);
            
            return generatedKey;
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }finally{
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
        return -1;
    }
    /**
     * UPDATES the data of the objects corresponding record in the DB.
     * @param budget Budget object to update.
     */
    public void updateBudget(Budget budget){
        String query = "UPDATE Budget SET Month='" + budget.getMonth() + "' WHERE Pk_Budget_Id='" + budget.getId() + "'";
        
        Connection connection = null;
        Statement statement = null;
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
            statement = connection.createStatement();
            statement.executeUpdate(query);
           
            CategoryDAO dao = new CategoryDAO();
            
            dao.updateCategories(budget.getSpendingCategories(), connection, dao.getNumCategories());
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
    }
    
    /**
     * Deletes the record associated with the current budget object's ID
     * @param budget 
     */
    public void deleteBudget(Budget budget){
        String query = "DELETE FROM Budget WHERE Pk_Budget_Id='" + budget.getId() + "'";
        
        Connection connection = null;
        Statement statement = null;
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
    }

    private class CategoryDAO {
        
        /**
         * Gets the number of records currently in the Category table in the DB
         * @return an int representing how many records exist in Category
         */
        public int getNumCategories(){
            String query = "SELECT * FROM Category";
        
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
        
            try{
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD); //DB_URL,DB_USER,DB_PASSWD
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
            
                int count = 0;
                while(resultSet.next()) {
                    count ++;
                }

                return count;
            
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }finally{
                try {
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                }
            }
            return -1;
        }
        /**
         * Selects data and builds corresponding category objects for a given budget ID.
         * @param budgetID Id of budget to get all categories for
         * @param connection DB connection
         * @param statement JDBC statement
         * @param resultSet JDBC result set
         * @return ArrayList of category objects
         */
        public ArrayList<Category> getCategories(int budgetID, Connection connection, Statement statement, ResultSet resultSet) {
            String query = "SELECT * FROM Category WHERE Fk_Budget_Id=" + budgetID;
            ArrayList<Category> categories = new ArrayList<Category>();
        
        try {
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){//curser starts before first row and returns false when there are no more rows
                int id = resultSet.getInt("Pk_Category_Id");
                String name = resultSet.getString("Name");
                int amtAllocated = resultSet.getInt("Amt_Allocated");
                int amtSpentTD = resultSet.getInt("Amt_Spent_Td");
                Category category = new Category(name.trim(), amtAllocated, amtSpentTD);
                category.setId(id);
                categories.add(category);
            }
            
            return categories;
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
        
        return null;
        }
        /**
         * Inserts all categories for a new budget object into the Category table in the DB
         * @param categories ArrayList of Category objects from Budget object
         * @param connection DB connection
         * @param budgetID Id of corresponding Budget object
         */
        public void saveCategories(ArrayList<Category> categories, Connection connection, int budgetID) {
            
            try {
                String query = "INSERT INTO Category (Name, Amt_Allocated, Amt_Spent_Td, Fk_Budget_Id) VALUES(?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);

                for(int i = 0; i < categories.size(); i++) {
                    statement.setString(1, categories.get(i).getName());
                    statement.setInt(2, categories.get(i).getAmtAllocated());
                    statement.setInt(3, categories.get(i).getAmtSpentTD());
                    statement.setInt(4, budgetID);
                    statement.addBatch();
                }
                int[]rowsEffected = statement.executeBatch();
                //System.out.println("Rows Effected: " + rowsEffected[0]);
                
            } catch(SQLException ex) {
            
            }
        }
        
        /**
         * UPDATES existing Categories related to an existing Budget object
         * @param categories ArrayList of Category objects from Budget object
         * @param connection DB connection
         * @param numCategories 
         */
        public void updateCategories(ArrayList<Category> categories, Connection connection, int numCategories) {
            
            try {
                String query = "UPDATE Category SET Name=?, Amt_Allocated=?, Amt_Spent_Td=? WHERE Pk_Category_Id=?";
                PreparedStatement statement = connection.prepareStatement(query);
                
                for(int i = 0; i < categories.size(); i++) {
                    //System.out.println("i = " + i);
                    statement.setString(1, categories.get(i).getName());
                    statement.setInt(2, categories.get(i).getAmtAllocated());
                    statement.setInt(3, categories.get(i).getAmtSpentTD());
                    statement.setInt(4, categories.get(i).getId());
                    statement.addBatch();
                }
                int[]rowsEffected = statement.executeBatch();
                //System.out.println("Rows Effected: " + rowsEffected[0]);
                
            } catch(SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
