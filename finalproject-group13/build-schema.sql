CREATE TABLE Budget
(
Pk_Budget_Id INT NOT NULL AUTO_INCREMENT,
Month VARCHAR(255),
PRIMARY KEY (Pk_Budget_Id)
);

CREATE TABLE Category
(
Pk_Category_Id INT NOT NULL AUTO_INCREMENT,
Name     VARCHAR(255),
Amt_Allocated INT,
Amt_Spent_Td INT,
Fk_Budget_Id INT,
PRIMARY KEY (Pk_Category_Id),
FOREIGN KEY(Fk_Budget_Id) REFERENCES Budget(Pk_Budget_Id) ON DELETE CASCADE
);