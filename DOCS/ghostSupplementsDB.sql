CREATE DATABASE ghostsupplements;
USE ghostsupplements;

#########################################################
#														#
#				      PRODUCTS							#
#														#
#########################################################
CREATE TABLE Nutrient (
  idNutrient int NOT NULL AUTO_INCREMENT, 
  name       varchar(100) NOT NULL, 
  PRIMARY KEY (idNutrient));
  
  CREATE TABLE Ingredient (
  idIngredient int NOT NULL AUTO_INCREMENT, 
  name         varchar(100) NOT NULL, 
  PRIMARY KEY (idIngredient));

CREATE TABLE AdministrationVia (
  idAdministrationVia int NOT NULL AUTO_INCREMENT, 
  name                varchar(50) NOT NULL, 
  PRIMARY KEY (idAdministrationVia));

CREATE TABLE Category (
  idCategory int NOT NULL AUTO_INCREMENT, 
  name       varchar(100) NOT NULL, 
  PRIMARY KEY (idCategory));
  INSERT INTO Category (name) 
VALUES ('Suplementos Proteicos');
	
  CREATE TABLE Brand (
  idBrand int NOT NULL AUTO_INCREMENT, 
  name    varchar(100) NOT NULL, 
  PRIMARY KEY (idBrand));

CREATE TABLE Product (
  idProduct            int NOT NULL AUTO_INCREMENT, 
  comercialName        varchar(255) NOT NULL, 
  price                decimal(10, 2) NOT NULL, 
  stock                smallint NOT NULL, 
  servingSize          int NOT NULL, 
  unitServingSize      varchar(8) NOT NULL, 
  servings             smallint NOT NULL, 
  netContent           smallint NOT NULL, 
  unitNetContent       varchar(8) NOT NULL, 
  presentation         varchar(50) NOT NULL, 
  description          varchar(500) NOT NULL, 
  caducity             date NOT NULL, 
  lote                 varchar(25) NOT NULL, 
  flavor               varchar(100) NOT NULL, 
  productRecomendation varchar(255) NOT NULL, 
  imgProductPath       varchar(255) NOT NULL, 
  idCategory           int NOT NULL, 
  idBrand              int NOT NULL, 
  idAdministrationVia  int NOT NULL, 
  PRIMARY KEY (idProduct));
  
###....TABLES RELATIONSHIPS....###

CREATE TABLE NutrientProduct (
  idProduct              int NOT NULL, 
  idNutrient             int NOT NULL, 
  quantity               int NOT NULL, 
  daily_value_percentage smallint NOT NULL, 
  PRIMARY KEY (idProduct, 
  idNutrient));

CREATE TABLE IngredientProduct (
  idProduct            int NOT NULL, 
  idIngredient         int NOT NULL, 
  dailyValuePercentage tinyint NOT NULL, 
  quantity             smallint NOT NULL, 
  units                varchar(8) NOT NULL, 
  PRIMARY KEY (idProduct, 
  idIngredient));


###....ALTER FOR RELATIONSHIPS....###

ALTER TABLE Product ADD CONSTRAINT FKProduct126843 FOREIGN KEY (idCategory) REFERENCES Category (idCategory);
ALTER TABLE Product ADD CONSTRAINT FKProduct590342 FOREIGN KEY (idBrand) REFERENCES Brand (idBrand);
ALTER TABLE Product ADD CONSTRAINT FKProduct724853 FOREIGN KEY (idAdministrationVia) REFERENCES AdministrationVia (idAdministrationVia);

ALTER TABLE NutrientProduct ADD CONSTRAINT FKNutrientPr490939 FOREIGN KEY (idProduct) REFERENCES Product (idProduct);
ALTER TABLE NutrientProduct ADD CONSTRAINT FKNutrientPr808299 FOREIGN KEY (idNutrient) REFERENCES Nutrient (idNutrient);

ALTER TABLE IngredientProduct ADD CONSTRAINT FKIngredient350102 FOREIGN KEY (idProduct) REFERENCES Product (idProduct);
ALTER TABLE IngredientProduct ADD CONSTRAINT FKIngredient160064 FOREIGN KEY (idIngredient) REFERENCES Ingredient (idIngredient);


#########################################################
#														#
#				      COUPONS							#
#														#
#########################################################

CREATE TABLE Coupon (
  idCoupon           int NOT NULL AUTO_INCREMENT, 
  codeDiscount       varchar(10) NOT NULL, 
  description        varchar(255) NOT NULL, 
  initDate           date NOT NULL, 
  expirationDate     date NOT NULL, 
  discountPercentage tinyint NOT NULL, 
  status bit Not null,
  idCategory         int NOT NULL, 
  PRIMARY KEY (idCoupon));
ALTER TABLE Coupon ADD CONSTRAINT FKCoupon44711 FOREIGN KEY (idCategory) REFERENCES Category (idCategory);

#########################################################
#														#
#				      USER								#
#														#
#########################################################


CREATE TABLE User (
  idUser     int NOT NULL AUTO_INCREMENT, 
  name       varchar(100) NOT NULL, 
  lastName   varchar(100) NOT NULL, 
  email      varchar(150) NOT NULL, 
  password   varchar(30) NOT NULL, 
  phone      varchar(10) NOT NULL, 
  gender     varchar(15) NOT NULL, 
  status     bit NOT NULL, 
  bornDate   date NOT NULL, 
  isCostumer bit NOT NULL, 
  PRIMARY KEY (idUser));

#########################################################
#														#
#		       PERSONAL MONITORING						#
#														#
#########################################################

CREATE TABLE MedicalData (
  idMedicalData    int NOT NULL AUTO_INCREMENT, 
  sleepingTime     tinyint NOT NULL, 
  physicalGoal     varchar(50) NOT NULL, 
  physicalActivity varchar(25) NOT NULL, 
  dailySteps       int NOT NULL, 
  idUser           int NOT NULL, 
  PRIMARY KEY (idMedicalData));
ALTER TABLE MedicalData ADD CONSTRAINT FKMedicalDat582410 FOREIGN KEY (idUser) REFERENCES User (idUser);

CREATE TABLE PhysicalRegister (
  idPhysicalRegister int NOT NULL AUTO_INCREMENT, 
  height             smallint NOT NULL, 
  weight             smallint NOT NULL, 
  bmi                tinyint NOT NULL, 
  registrationDate   date NOT NULL, 
  bodyFatPercent     tinyint NOT NULL, 
  idUser             int NOT NULL, 
  PRIMARY KEY (idPhysicalRegister));
ALTER TABLE PhysicalRegister ADD CONSTRAINT FKPhysicalRe836776 FOREIGN KEY (idUser) REFERENCES `User` (idUser);

CREATE TABLE PhysicalConditions (
  idPhysicalCondition int NOT NULL AUTO_INCREMENT, 
  `condition`         varchar(255) NOT NULL, 
  conditionLevel      varchar(50) NOT NULL, 
  idMedicalData       int NOT NULL, 
  PRIMARY KEY (idPhysicalCondition));
ALTER TABLE PhysicalConditions ADD CONSTRAINT FKPhysicalCo857967 FOREIGN KEY (idMedicalData) REFERENCES MedicalData (idMedicalData);

CREATE TABLE MedicalCondition (
  idChronicalCondition int NOT NULL AUTO_INCREMENT, 
  `condition`          int NOT NULL, 
  idMedicalData        int NOT NULL, 
  PRIMARY KEY (idChronicalCondition));
ALTER TABLE MedicalCondition ADD CONSTRAINT FKMedicalCon356136 FOREIGN KEY (idMedicalData) REFERENCES MedicalData (idMedicalData);

CREATE TABLE Excercise (
  idExcercise int NOT NULL AUTO_INCREMENT, 
  name        varchar(100) NOT NULL, 
  difficulty  varchar(50) NOT NULL, 
  PRIMARY KEY (idExcercise));

CREATE TABLE TrainigRoutine (
  idUser      int NOT NULL, 
  idExcercise int NOT NULL, 
  sets        tinyint NOT NULL, 
  reps        tinyint NOT NULL, 
  PRIMARY KEY (idUser, 
  idExcercise));
  
ALTER TABLE TrainigRoutine ADD CONSTRAINT FKTrainigRou500798 FOREIGN KEY (idUser) REFERENCES `User` (idUser);
ALTER TABLE TrainigRoutine ADD CONSTRAINT FKTrainigRou913910 FOREIGN KEY (idExcercise) REFERENCES Excercise (idExcercise);

CREATE TABLE RoutineProgress (
  idRoutineProgress int NOT NULL AUTO_INCREMENT, 
  sets              tinyint NOT NULL, 
  reps              tinyint NOT NULL, 
  totalWeight       smallint NOT NULL, 
  trainingDate      date NOT NULL, 
  idUser            int NOT NULL, 
  idExcercise       int NOT NULL, 
  PRIMARY KEY (idRoutineProgress));
ALTER TABLE RoutineProgress ADD CONSTRAINT FKRoutinePro453249 FOREIGN KEY (idUser, idExcercise) REFERENCES TrainigRoutine (idUser, idExcercise);


#########################################################
#														#
#		                  WALLET						#
#														#
#########################################################

CREATE TABLE Wallet (
  idWallet   int NOT NULL AUTO_INCREMENT, 
  idCustomer int NOT NULL, 
  PRIMARY KEY (idWallet));
ALTER TABLE Wallet ADD CONSTRAINT FKWallet671344 FOREIGN KEY (idCustomer) REFERENCES `User` (idUser);

CREATE TABLE Card (
  id_card        int NOT NULL AUTO_INCREMENT, 
  number         int NOT NULL, 
  type           int NOT NULL, 
  expirationDate int NOT NULL, 
  cvv            int NOT NULL, 
  isExpired      bit NOT NULL, 
  id_wallet      int NOT NULL, 
  PRIMARY KEY (id_card));
ALTER TABLE Card ADD CONSTRAINT FKCard420303 FOREIGN KEY (id_wallet) REFERENCES Wallet (idWallet);

#########################################################
#														#
#		                  REWWARDS						#
#														#
#########################################################

CREATE TABLE Points (
  idPoints          int NOT NULL AUTO_INCREMENT, 
  accumulatedPoints int NOT NULL, 
  idUser            int NOT NULL, 
  PRIMARY KEY (idPoints));
ALTER TABLE Points ADD CONSTRAINT FKPoints909700 FOREIGN KEY (idUser) REFERENCES `User` (idUser);

CREATE TABLE Reward (
  idReward   int NOT NULL AUTO_INCREMENT, 
  idProduct  int NOT NULL, 
  goalPoints int NOT NULL, 
  PRIMARY KEY (idReward));
ALTER TABLE Reward ADD CONSTRAINT FKReward480450 FOREIGN KEY (idProduct) REFERENCES Product (idProduct);

#########################################################
#														#
#		                  SALES 						#
#														#
#########################################################

CREATE TABLE Sale (
  idSale          int NOT NULL AUTO_INCREMENT, 
  `date`          date NOT NULL, 
  total           decimal(10, 2) NOT NULL, 
  paymentMethod   varchar(25) NOT NULL, 
  generatedPoints smallint NOT NULL, 
  idUser          int NOT NULL, 
  idCoupon        int NOT NULL, 
  PRIMARY KEY (idSale));
  
  CREATE TABLE SaleProduct (
  idSale    int NOT NULL, 
  idProduct int NOT NULL, 
  quantity  smallint NOT NULL);
  
ALTER TABLE SaleProduct ADD CONSTRAINT FKSaleProduc329121 FOREIGN KEY (idSale) REFERENCES Sale (idSale);
ALTER TABLE SaleProduct ADD CONSTRAINT FKSaleProduc702254 FOREIGN KEY (idProduct) REFERENCES Product (idProduct);  
ALTER TABLE Sale ADD CONSTRAINT FKSale367830 FOREIGN KEY (idUser) REFERENCES `User` (idUser);
ALTER TABLE Sale ADD CONSTRAINT FKSale556174 FOREIGN KEY (idCoupon) REFERENCES Coupon (idCoupon);

#}DROP DATABASE ghostsupplements;