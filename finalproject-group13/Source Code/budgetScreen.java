/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group13.budgets;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author Brian Mounce
 * @author Carter King
 */
public class budgetScreen extends javax.swing.JFrame {
    
    DefaultTableModel budgetModel;
    ArrayList<Category> thisMonthsBudget;
    Budget budget;
    /**
     * Creates new form mainScreen
     */
    public budgetScreen(Budget budget) {
        initComponents();
        budgetModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        budgetTable.setModel(budgetModel);
        budgetModel.addColumn("Name");
        budgetModel.addColumn("Spent to Date");
        budgetModel.addColumn("Spend Limit");
        budgetModel.addColumn("Remaining");
        buttonGroup1.add(addRadio);
        buttonGroup1.add(subtractRadio);
        totalTable.getTableHeader().setUI(null);
        thisMonthsBudget = budget.getSpendingCategories();
        this.budget = budget;
        
        for (int i = 0; i < thisMonthsBudget.size(); i++) {
            budgetModel.addRow(new Object[] {"", "", "", ""});
            budgetModel.setValueAt(thisMonthsBudget.get(i).getName(), i, 0);
            budgetModel.setValueAt(intToDollars(thisMonthsBudget.get(i).getAmtSpentTD()), i, 1);
            budgetModel.setValueAt(intToDollars(thisMonthsBudget.get(i).getAmtAllocated()), i, 2);
            budgetModel.setValueAt(intToDollars(thisMonthsBudget.get(i).getAmtRemaining()), i, 3);
        }
        updateTotal();
    }
    public budgetScreen() {
        initComponents();
        budgetModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        budgetTable.setModel(budgetModel);
        budgetModel.addColumn("Name");
        budgetModel.addColumn("Spent to Date");
        budgetModel.addColumn("Spend Limit");
        budgetModel.addColumn("Remaining");
        buttonGroup1.add(addRadio);
        buttonGroup1.add(subtractRadio);
        totalTable.getTableHeader().setUI(null);
        updateTotal();
    }
    
    /**
     * Accepts an integer and returns a string containing the dollar value of the
     * parameter.
     * @param i
     * @return String representing the dollar value of i
     */
    private String intToDollars(int i){
        if(i == 0 ){
            return "$0.00";
        }
        String temp = "$"+i;
        temp = temp.substring(0, temp.length() - 2)+"."+temp.substring(temp.length() - 2, temp.length());
        return temp;
    }
    
    /**
     * Removes the '$' and '.' character from the passed String and returns an integer
     * @param dollars
     * @return int equal to the value of dollars in pennies
     */
    private int dollarsToInt(String dollars){
        int temp;
        String test = dollars;
        if(test.contains("$")){
            test = test.substring(1);
        }
        if(test.contains(".")){
            if(test.indexOf(".") == test.length() - 3){
                test = test.substring(0, test.indexOf(".")) + test.substring(test.indexOf(".")+1);
            }
            else if(test.indexOf(".") == test.length() - 2){
               test = test.substring(0, test.indexOf(".")) + test.substring(test.indexOf(".")+1) + "0";
            }
            else if(test.indexOf(".") == test.length() - 1){
              test = test.substring(0, test.indexOf(".")) + test.substring(test.indexOf(".")+1) + "00";
            }
      }
        else{
            test = test+"00";
        }
        temp = Integer.parseInt(test);
        return temp;
    }
    
    /**
     * Checks that the given string only contains number characters, '$'. or '.'
     * @param number
     * @return 
     */
    public boolean isDollar(String number){
        for(int i = 0 ; i < number.length(); i++){
            if((number.charAt(i) >= 48 && number.charAt(i) <= 57) || number.charAt(i) == 36 || number.charAt(i) == 46)
                return true;
        }
        return false;
    }
    
    /**
     * Finds the difference between the values stored in the 
     * spentToDateField and the limitField and updates remainingField 
     * with the new value.
     */
    private void updateRemaining(){
        try{
            int limit;
            int toDate;
            if(limitField.getText().isEmpty()){
                limit = 0;
            }
            else{
                limit = dollarsToInt(limitField.getText());
            }
            if(spentToDateField.getText().isEmpty()){
                toDate = 0;
            }
            else{
                toDate = dollarsToInt(spentToDateField.getText());
            }
            int remaining = limit - toDate;
            remainingField.setText(intToDollars(remaining));
        }
        catch(Exception e){
        }
    }
    
    /**
     * Iterates through the rows of budgetTable and sums the values stored in 
     * the second third and fourth columns and stores them in the second, third 
     * and fourth column of totalTable
     */
    private void updateTotal(){
        int totalRemaining = 0;
        int totalLimit = 0;
        int totalToDate = 0;
        
        for(int i = 0; i < budgetTable.getRowCount(); i++){
            try {
                if(budgetTable.getValueAt(i, 1) != ""){
                    totalToDate += dollarsToInt(budgetTable.getValueAt(i, 1).toString());
                }
            } catch (Exception e) {
            }
            try {
                if(budgetTable.getValueAt(i, 2) != ""){
                totalLimit += dollarsToInt(budgetTable.getValueAt(i, 2).toString());
                }
            } catch (Exception e) {
            }
            try {
                if(budgetTable.getValueAt(i, 1) != ""){
                totalRemaining += dollarsToInt((budgetTable.getValueAt(i, 3).toString()));
                }
            } catch (Exception e) {
            }
        }
        totalTable.setValueAt(intToDollars(totalToDate), 0, 1);
        totalTable.setValueAt(intToDollars(totalLimit), 0, 2);
        totalTable.setValueAt(intToDollars(totalRemaining), 0, 3);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        budgetTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        nameField = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        spentToDateField = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        limitField = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        remainingField = new javax.swing.JTextPane();
        saveChangesButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        adjustSpentToDateField = new javax.swing.JTextField();
        addRadio = new javax.swing.JRadioButton();
        subtractRadio = new javax.swing.JRadioButton();
        submitButton = new javax.swing.JButton();
        returnButton = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        totalTable = new javax.swing.JTable();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Budget Application");
        setPreferredSize(new java.awt.Dimension(1080, 720));

        budgetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Spent to Date", "Spend Limit", "Remaining"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        budgetTable.getTableHeader().setReorderingAllowed(false);
        budgetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                budgetTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(budgetTable);
        if (budgetTable.getColumnModel().getColumnCount() > 0) {
            budgetTable.getColumnModel().getColumn(0).setResizable(false);
            budgetTable.getColumnModel().getColumn(1).setResizable(false);
            budgetTable.getColumnModel().getColumn(2).setResizable(false);
            budgetTable.getColumnModel().getColumn(3).setResizable(false);
        }

        addButton.setText("Add Row");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete Row");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Name");

        jLabel3.setText("Spent to Date");

        jScrollPane2.setViewportView(nameField);

        spentToDateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                spentToDateFieldKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(spentToDateField);

        jLabel1.setText("Spend Limit");

        limitField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        limitField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                limitFieldKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(limitField);

        jLabel4.setText("Remaning");

        remainingField.setEditable(false);
        jScrollPane5.setViewportView(remainingField);

        saveChangesButton.setText("Save Changes");
        saveChangesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveChangesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Adjust Spent to Date");

        addRadio.setSelected(true);
        addRadio.setText("Add");

        subtractRadio.setText("Subtract");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(adjustSpentToDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(subtractRadio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(addRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subtractRadio))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(adjustSpentToDateField)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(submitButton)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(saveChangesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(247, 247, 247))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(saveChangesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );

        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        totalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Total", null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalTable.setEnabled(false);
        jScrollPane9.setViewportView(totalTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addComponent(deleteButton)
                        .addGap(141, 141, 141)
                        .addComponent(returnButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane9)
                            .addComponent(jScrollPane1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(deleteButton)
                    .addComponent(returnButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Creates a new row object for the budget table
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        budgetModel.addRow(new Object[] {"", "", "", ""});
        Category category = new Category("", 0, 0);
        
        BudgetDAO dao = new BudgetDAO();
        //System.out.println("ID: " + budget.getId() + "Category: " + category);
        int id = dao.insertSingleCategory(budget.getId(), category);
        category.setId(id);
        thisMonthsBudget.add(category);
        
        //insert category into category DB with ID of current budget as FK
        
    }//GEN-LAST:event_addButtonActionPerformed
    
    /**
     * Selects a row of budgetTable for the getSelectedRow() function to operate 
     * @param evt 
     */
    private void budgetTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_budgetTableMouseClicked

        nameField.setText((budgetTable.getValueAt(budgetTable.getSelectedRow(), 0)).toString());
        spentToDateField.setText((budgetTable.getValueAt(budgetTable.getSelectedRow(), 1)).toString());
        limitField.setText((budgetTable.getValueAt(budgetTable.getSelectedRow(), 2)).toString());
        remainingField.setText((budgetTable.getValueAt(budgetTable.getSelectedRow(), 3)).toString());
    }//GEN-LAST:event_budgetTableMouseClicked

    /**
     * Updates the table based on entered values, and stores values in DB
     * @param evt 
     */
    private void saveChangesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesButtonActionPerformed
 
        if(budgetTable.getSelectedRow() == -1){
            return;
        }
        if(!isDollar(spentToDateField.getText())){
            JOptionPane.showMessageDialog(rootPane, "Invalid input in Spent to Date", "Error", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        if(!isDollar(limitField.getText())){
            JOptionPane.showMessageDialog(rootPane, "Invalid input in Spend Limit", "Error", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        else{
            String spent;
            String limit;

            spent = spentToDateField.getText();
            limit = limitField.getText();

            budgetTable.setValueAt(nameField.getText(), budgetTable.getSelectedRow(), 0);
            budgetTable.setValueAt(intToDollars( dollarsToInt(spent)) , budgetTable.getSelectedRow(), 1);
            budgetTable.setValueAt(intToDollars( dollarsToInt(limit)), budgetTable.getSelectedRow(), 2);
            budgetTable.setValueAt(remainingField.getText(), budgetTable.getSelectedRow(), 3);

            thisMonthsBudget.get(budgetTable.getSelectedRow()).setName(nameField.getText());
            thisMonthsBudget.get(budgetTable.getSelectedRow()).setAmtSpentTD(dollarsToInt(spentToDateField.getText()));
            thisMonthsBudget.get(budgetTable.getSelectedRow()).setAmtAllocated(dollarsToInt(limitField.getText()));
            thisMonthsBudget.get(budgetTable.getSelectedRow()).setAmtRemaining(dollarsToInt(remainingField.getText()));

            updateTotal();

            //System.out.println("Row selected when save changes was clicked: " + thisMonthsBudget.get(budgetTable.getSelectedRow()).getId());

            BudgetDAO dao = new BudgetDAO();
            //System.out.println(budget.getId());
            dao.updateSingleCategory(budget.getId() ,thisMonthsBudget.get(budgetTable.getSelectedRow()));
            //update row in db to reflect changes
        }
    }//GEN-LAST:event_saveChangesButtonActionPerformed

    /**
     * The value in remainingField is updated to reflect changes in limitField
     *      when program detects a key release
     * @param evt 
     */
    private void limitFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_limitFieldKeyReleased

        updateRemaining();
    }//GEN-LAST:event_limitFieldKeyReleased

    /**
     * The value in spentToDateField is updated to reflect changes in limitField
     *      when program detects a key release
     * @param evt 
     */
    private void spentToDateFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spentToDateFieldKeyReleased

        updateRemaining();
    }//GEN-LAST:event_spentToDateFieldKeyReleased

    /**
     * Removes the selected row from the budgetTable.
     * @param evt 
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if(budgetTable.getSelectedRow() == -1){
            return;
        }
        String row = budgetTable.getValueAt(budgetTable.getSelectedRow(), 0).toString();
        int choice = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete " + row + "?", "Delete", JOptionPane.YES_NO_OPTION);
        if(choice == 0){
            
            BudgetDAO dao = new BudgetDAO();
            dao.deleteSingleCategory(thisMonthsBudget.get(budgetTable.getSelectedRow()).getId());
            
            thisMonthsBudget.remove(budgetTable.getSelectedRow());
            budgetModel.removeRow(budgetTable.getSelectedRow());
            updateTotal();
            nameField.setText("");
            spentToDateField.setText("");
            limitField.setText("");
            remainingField.setText("");            
        }
        
        
    }//GEN-LAST:event_deleteButtonActionPerformed
    
    /**
     * Updates the value stored in spentToDateFeild by adding or subtracting the
     * value stored in adjustSpentToDateField depending on selected radio button
     * from choices subtractRadioButton or addRadioButton
     * @param evt 
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        if(!isDollar(adjustSpentToDateField.getText())){
            JOptionPane.showMessageDialog(rootPane, "Invalid input in Adjust Spent to Date", "Error", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        else{
            int total = 0;
            int adjust = dollarsToInt(adjustSpentToDateField.getText());
            int spent = dollarsToInt(spentToDateField.getText());
            if(subtractRadio.isSelected()){
                total = spent - adjust;
                spentToDateField.setText(intToDollars(total));
            }
            else{
                total = spent + adjust;
                spentToDateField.setText(intToDollars(total));
            }
            updateRemaining();
        }
    }//GEN-LAST:event_submitButtonActionPerformed
    
    /**
     * Creates a new loadScreen object before disposing of the current budgetScreen
     * object. 
     * @param evt 
     */
    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed

        loadScreen newscreen = new loadScreen();
        dispose();
        newscreen.setVisible(true);
    }//GEN-LAST:event_returnButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(budgetScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(budgetScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(budgetScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(budgetScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new budgetScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JRadioButton addRadio;
    private javax.swing.JTextField adjustSpentToDateField;
    private javax.swing.JTable budgetTable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextPane limitField;
    private javax.swing.JTextPane nameField;
    private javax.swing.JTextPane remainingField;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton saveChangesButton;
    private javax.swing.JTextPane spentToDateField;
    private javax.swing.JButton submitButton;
    private javax.swing.JRadioButton subtractRadio;
    private javax.swing.JTable totalTable;
    // End of variables declaration//GEN-END:variables
}
