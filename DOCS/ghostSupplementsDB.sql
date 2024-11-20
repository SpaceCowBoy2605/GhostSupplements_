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
  idUser int NOT NULL, 
  PRIMARY KEY (idWallet));
ALTER TABLE Wallet ADD CONSTRAINT FKWallet671344 FOREIGN KEY (idUser) REFERENCES `User` (idUser);

CREATE TABLE Card (
  idCard        int NOT NULL AUTO_INCREMENT, 
  number         int NOT NULL, 
  type           VARCHAR(30) NOT NULL, 
  expirationDate date NOT NULL, 
  cvv            int NOT NULL, 
  isExpired      boolean NOT NULL, 
  idWallet      int NOT NULL, 
  PRIMARY KEY (idCard));
ALTER TABLE Card ADD CONSTRAINT FKCard420303 FOREIGN KEY (idWallet) REFERENCES Wallet (idWallet);

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
  description VARCHAR (255) NOT NULL,
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

#INSERTS FPR DATA BASE
#
#
#
#
#
INSERT INTO Nutrient (name) VALUES 
('Vitamin A'),
('Vitamin B1'),
('Vitamin B2'),
('Vitamin B3'),
('Vitamin B5'),
('Vitamin B6'),
('Vitamin B7'),
('Vitamin B9'),
('Vitamin B12'),
('Vitamin C'),
('Vitamin D'),
('Vitamin E'),
('Vitamin K'),
('Calcium'),
('Iron'),
('Magnesium'),
('Zinc'),
('Selenium'),
('Copper'),
('Manganese');

INSERT INTO Ingredient (name) VALUES 
('Whey Protein'),
('Creatine Monohydrate'),
('Beta-Alanine'),
('L-Carnitine'),
('BCAA'),
('Glutamine'),
('Caffeine'),
('Fish Oil'),
('Multivitamin'),
('Green Tea Extract');


INSERT INTO AdministrationVia (name) VALUES 
('Oral'),
('Topical'),
('Injection'),
('Inhalation'),
('Sublingual'),
('Intramuscular'),
('Transdermal'),
('Buccal');

INSERT INTO Category (name) VALUES 
('Pre-Workout'),
('Post-Workout'),
('Protein Powders'),
('Creatine'),
('Amino Acids'),
('Fat Burners'),
('Energy Boosters'),
('Recovery'),
('Hydration'),
('Endurance');

INSERT INTO Brand (name) VALUES 
('Optimum Nutrition'),
('MuscleTech'),
('BSN'),
('Cellucor'),
('Dymatize'),
('Universal Nutrition'),
('Gaspari Nutrition'),
('Evlution Nutrition'),
('JYM Supplement Science'),
('ProSupps'),
('BPI Sports'),
('Nutrex Research'),
('Animal'),
('Redcon1'),
('Ghost Lifestyle'),
('MyProtein'),
('Scivation'),
('RSP Nutrition'),
('GAT Sport'),
('Kaged Muscle');

INSERT INTO Product (
  comercialName, price, stock, servingSize, unitServingSize, servings, netContent, unitNetContent, 
  presentation, description, caducity, lote, flavor, productRecomendation, imgProductPath, 
  idCategory, idBrand, idAdministrationVia
) VALUES 
('Whey Protein Isolate', 29.99, 100, 30, 'g', 75, 2250, 'g', 'Powder', 'High-quality whey protein isolate', '2024-12-31', 'WP12345', 'Vanilla', 'Mix with water or milk', '/images/whey_protein_isolate.jpg', 3, 1, 1),
('Creatine Monohydrate', 19.99, 200, 5, 'g', 60, 300, 'g', 'Powder', 'Pure creatine monohydrate', '2025-06-30', 'CM67890', 'Unflavored', 'Mix with water or juice', '/images/creatine_monohydrate.jpg', 4, 2, 1),
('BCAA 2:1:1', 24.99, 150, 10, 'g', 50, 500, 'g', 'Powder', 'Branched-chain amino acids', '2023-11-30', 'BCAA11223', 'Fruit Punch', 'Take during workout', '/images/bcaa.jpg', 4, 3, 1),
('Pre-Workout', 34.99, 80, 15, 'g', 30, 450, 'g', 'Powder', 'Energy and focus booster', '2024-05-31', 'PW33445', 'Blue Raspberry', 'Take 30 minutes before workout', '/images/pre_workout.jpg', 1, 4, 1),
('Fish Oil', 14.99, 120, 2, 'caps', 60, 120, 'caps', 'Capsules', 'Omega-3 fatty acids', '2025-01-31', 'FO55667', 'Unflavored', 'Take with meals', '/images/fish_oil.jpg', 6, 5, 1),
('Multivitamin', 12.99, 180, 1, 'tab', 90, 90, 'tabs', 'Tablets', 'Daily multivitamin', '2024-08-31', 'MV77889', 'Unflavored', 'Take one tablet daily', '/images/multivitamin.jpg', 1, 6, 1),
('Glutamine', 21.99, 130, 5, 'g', 60, 300, 'g', 'Powder', 'L-Glutamine powder', '2024-03-31', 'GL99001', 'Unflavored', 'Mix with water or juice', '/images/glutamine.jpg', 4, 7, 1),
('Caffeine Pills', 9.99, 250, 1, 'tab', 100, 100, 'tabs', 'Tablets', 'Caffeine for energy', '2023-12-31', 'CP11223', 'Unflavored', 'Take one tablet as needed', '/images/caffeine_pills.jpg', 7, 8, 1),
('Green Tea Extract', 16.99, 140, 1, 'cap', 60, 60, 'caps', 'Capsules', 'Antioxidant support', '2024-10-31', 'GTE33445', 'Unflavored', 'Take with meals', '/images/green_tea_extract.jpg', 6, 9, 1),
('Beta-Alanine', 22.99, 110, 3, 'g', 60, 180, 'g', 'Powder', 'Beta-Alanine powder', '2024-07-31', 'BA55667', 'Unflavored', 'Mix with water or juice', '/images/beta_alanine.jpg', 4, 10, 1);

INSERT INTO Product (
  comercialName, price, stock, servingSize, unitServingSize, servings, netContent, unitNetContent, 
  presentation, description, caducity, lote, flavor, productRecomendation, imgProductPath, 
  idCategory, idBrand, idAdministrationVia
) VALUES 
('Mass Gainer', 39.99, 90, 50, 'g', 30, 1500, 'g', 'Powder', 'High-calorie mass gainer', '2024-11-30', 'MG12345', 'Chocolate', 'Mix with water or milk', '/images/mass_gainer.jpg', 3, 11, 1),
('Casein Protein', 29.99, 100, 30, 'g', 75, 2250, 'g', 'Powder', 'Slow-digesting casein protein', '2024-12-31', 'CP67890', 'Vanilla', 'Mix with water or milk before bed', '/images/casein_protein.jpg', 3, 12, 1),
('L-Carnitine', 19.99, 150, 1, 'tab', 60, 60, 'tabs', 'Tablets', 'L-Carnitine for fat metabolism', '2025-06-30', 'LC11223', 'Unflavored', 'Take with meals', '/images/l_carnitine.jpg', 4, 13, 1),
('Nitric Oxide Booster', 34.99, 80, 15, 'g', 30, 450, 'g', 'Powder', 'Enhances blood flow and pumps', '2024-05-31', 'NO33445', 'Fruit Punch', 'Take 30 minutes before workout', '/images/nitric_oxide_booster.jpg', 1, 14, 1),
('Electrolyte Powder', 14.99, 120, 5, 'g', 60, 300, 'g', 'Powder', 'Replenishes electrolytes', '2025-01-31', 'EP55667', 'Lemon Lime', 'Mix with water', '/images/electrolyte_powder.jpg', 9, 15, 1),
('ZMA', 12.99, 180, 1, 'cap', 90, 90, 'caps', 'Capsules', 'Zinc, Magnesium, and Vitamin B6', '2024-08-31', 'ZMA77889', 'Unflavored', 'Take before bed', '/images/zma.jpg', 2, 16, 1),
('CLA', 21.99, 130, 1, 'cap', 60, 60, 'caps', 'Capsules', 'Conjugated Linoleic Acid', '2024-03-31', 'CLA99001', 'Unflavored', 'Take with meals', '/images/cla.jpg', 6, 17, 1),
('Ashwagandha', 9.99, 250, 1, 'cap', 100, 100, 'caps', 'Capsules', 'Adaptogen for stress relief', '2023-12-31', 'ASH11223', 'Unflavored', 'Take with meals', '/images/ashwagandha.jpg', 5, 18, 1),
('Turmeric Curcumin', 16.99, 140, 1, 'cap', 60, 60, 'caps', 'Capsules', 'Anti-inflammatory support', '2024-10-31', 'TC33445', 'Unflavored', 'Take with meals', '/images/turmeric_curcumin.jpg', 5, 19, 1),
('Beet Root Powder', 22.99, 110, 5, 'g', 60, 300, 'g', 'Powder', 'Natural nitric oxide booster', '2024-07-31', 'BR55667', 'Unflavored', 'Mix with water or juice', '/images/beet_root_powder.jpg', 4, 20, 1);

INSERT INTO NutrientProduct (idProduct, idNutrient, quantity, daily_value_percentage) VALUES 
(1, 1, 1000, 95),  -- Product 1 with Vitamin A
(1, 2, 100, 85),   -- Product 1 with Vitamin B1
(1, 6, 60, 90),    -- Product 1 with Vitamin C
(2, 4, 5, 75),     -- Product 2 with Vitamin B6
(2, 5, 2, 80),     -- Product 2 with Vitamin B12
(2, 14, 1000, 100),-- Product 2 with Calcium
(3, 3, 1, 70),     -- Product 3 with Vitamin B2
(3, 7, 400, 65),   -- Product 3 with Vitamin D
(3, 15, 18, 55),   -- Product 3 with Iron
(4, 8, 30, 60),    -- Product 4 with Vitamin E
(4, 9, 120, 50),   -- Product 4 with Vitamin K
(4, 16, 400, 85),  -- Product 4 with Magnesium
(5, 10, 1000, 95), -- Product 5 with Calcium
(5, 11, 15, 90),   -- Product 5 with Iron
(5, 17, 11, 75),   -- Product 5 with Zinc
(6, 12, 55, 80),   -- Product 6 with Selenium
(6, 13, 2, 65),    -- Product 6 with Copper
(6, 18, 2, 70),    -- Product 6 with Manganese
(7, 1, 900, 90),   -- Product 7 with Vitamin A
(7, 6, 50, 83),    -- Product 7 with Vitamin C
(8, 2, 110, 95),   -- Product 8 with Vitamin B1
(8, 3, 1.2, 100),  -- Product 8 with Vitamin B2
(8, 4, 2, 85),     -- Product 8 with Vitamin B6
(9, 5, 2.4, 75),   -- Product 9 with Vitamin B12
(9, 7, 600, 90),   -- Product 9 with Vitamin D
(9, 8, 30, 95),    -- Product 9 with Vitamin E
(10, 9, 120, 100), -- Product 10 with Vitamin K
(10, 10, 1000, 85),-- Product 10 with Calcium
(10, 11, 18, 80);  -- Product 10 with Iron

INSERT INTO NutrientProduct (idProduct, idNutrient, quantity, daily_value_percentage) VALUES 
(11, 1, 1000, 95),  -- Product 11 with Vitamin A
(11, 2, 100, 85),   -- Product 11 with Vitamin B1
(11, 6, 60, 90),    -- Product 11 with Vitamin C
(12, 4, 5, 75),     -- Product 12 with Vitamin B6
(12, 5, 2, 80),     -- Product 12 with Vitamin B12
(12, 14, 1000, 100),-- Product 12 with Calcium
(13, 3, 1, 70),     -- Product 13 with Vitamin B2
(13, 7, 400, 65),   -- Product 13 with Vitamin D
(13, 15, 18, 55),   -- Product 13 with Iron
(14, 8, 30, 60),    -- Product 14 with Vitamin E
(14, 9, 120, 50),   -- Product 14 with Vitamin K
(14, 16, 400, 85),  -- Product 14 with Magnesium
(15, 10, 1000, 95), -- Product 15 with Calcium
(15, 11, 15, 90),   -- Product 15 with Iron
(15, 17, 11, 75),   -- Product 15 with Zinc
(16, 12, 55, 80),   -- Product 16 with Selenium
(16, 13, 2, 65),    -- Product 16 with Copper
(16, 18, 2, 70),    -- Product 16 with Manganese
(17, 1, 900, 90),   -- Product 17 with Vitamin A
(17, 6, 50, 83),    -- Product 17 with Vitamin C
(18, 2, 110, 95),   -- Product 18 with Vitamin B1
(18, 3, 1.2, 100),  -- Product 18 with Vitamin B2
(18, 4, 2, 85),     -- Product 18 with Vitamin B6
(19, 5, 2.4, 75),   -- Product 19 with Vitamin B12
(19, 7, 600, 90),   -- Product 19 with Vitamin D
(19, 8, 30, 95),    -- Product 19 with Vitamin E
(20, 9, 120, 100),  -- Product 20 with Vitamin K
(20, 10, 1000, 85), -- Product 20 with Calcium
(20, 11, 18, 80);   -- Product 20 with Iron

INSERT INTO IngredientProduct (idProduct, idIngredient, dailyValuePercentage, quantity, units) VALUES 
(1, 1, 100, 30, 'g'),  -- Product 1 with Whey Protein
(1, 2, 100, 5, 'g'),   -- Product 1 with Creatine Monohydrate
(1, 3, 100, 3, 'g'),   -- Product 1 with Beta-Alanine
(2, 4, 100, 2, 'g'),   -- Product 2 with L-Carnitine
(2, 5, 100, 10, 'g'),  -- Product 2 with BCAA
(2, 6, 100, 5, 'g'),   -- Product 2 with Glutamine
(3, 7, 100, 200, 'mg'),-- Product 3 with Caffeine
(3, 8, 100, 1000, 'mg'),-- Product 3 with Fish Oil
(3, 9, 100, 1, 'tab'), -- Product 3 with Multivitamin
(4, 10, 100, 500, 'mg'),-- Product 4 with Green Tea Extract
(4, 1, 100, 25, 'g'),  -- Product 4 with Whey Protein
(4, 2, 100, 3, 'g'),   -- Product 4 with Creatine Monohydrate
(5, 3, 100, 2, 'g'),   -- Product 5 with Beta-Alanine
(5, 4, 100, 1, 'g'),   -- Product 5 with L-Carnitine
(5, 5, 100, 8, 'g'),   -- Product 5 with BCAA
(6, 6, 100, 4, 'g'),   -- Product 6 with Glutamine
(6, 7, 100, 150, 'mg'),-- Product 6 with Caffeine
(6, 8, 100, 800, 'mg'),-- Product 6 with Fish Oil
(7, 9, 100, 1, 'tab'), -- Product 7 with Multivitamin
(7, 10, 100, 400, 'mg'),-- Product 7 with Green Tea Extract
(7, 1, 100, 20, 'g'),  -- Product 7 with Whey Protein
(8, 2, 100, 4, 'g'),   -- Product 8 with Creatine Monohydrate
(8, 3, 100, 2, 'g'),   -- Product 8 with Beta-Alanine
(8, 4, 100, 1, 'g'),   -- Product 8 with L-Carnitine
(9, 5, 100, 7, 'g'),   -- Product 9 with BCAA
(9, 6, 100, 3, 'g'),   -- Product 9 with Glutamine
(9, 7, 100, 100, 'mg'),-- Product 9 with Caffeine
(10, 8, 100, 600, 'mg'),-- Product 10 with Fish Oil
(10, 9, 100, 1, 'tab'),-- Product 10 with Multivitamin
(10, 10, 100, 300, 'mg');-- Product 10 with Green Tea Extract

INSERT INTO Coupon (codeDiscount, description, initDate, expirationDate, discountPercentage, status, idCategory) VALUES 
('SAVE10', '10% off on all vitamins', '2023-01-01', '2023-12-31', 10, 1, 1),
('SAVE15', '15% off on all minerals', '2023-01-01', '2023-12-31', 15, 1, 2),
('SAVE20', '20% off on all proteins', '2023-01-01', '2023-12-31', 20, 1, 3),
('SAVE25', '25% off on all amino acids', '2023-01-01', '2023-12-31', 25, 1, 4),
('SAVE30', '30% off on all herbs', '2023-01-01', '2023-12-31', 30, 1, 5),
('SAVE35', '35% off on all fatty acids', '2023-01-01', '2023-12-31', 35, 1, 6),
('SAVE40', '40% off on all antioxidants', '2023-01-01', '2023-12-31', 40, 1, 7),
('SAVE45', '45% off on all probiotics', '2023-01-01', '2023-12-31', 45, 1, 8),
('SAVE50', '50% off on all prebiotics', '2023-01-01', '2023-12-31', 50, 1, 9),
('SAVE55', '55% off on all energy boosters', '2023-01-01', '2023-12-31', 55, 1, 10);

INSERT INTO User (name, lastName, email, password, phone, gender, status, bornDate, isCostumer) VALUES 
('John', 'Doe', 'john.doe@example.com', 'password123', '1234567890', 'Male', 1, '1990-01-01', 1),
('Jane', 'Smith', 'jane.smith@example.com', 'password123', '0987654321', 'Female', 1, '1992-02-02', 1),
('Alice', 'Johnson', 'alice.johnson@example.com', 'password123', '1122334455', 'Female', 1, '1985-03-03', 1),
('Bob', 'Brown', 'bob.brown@example.com', 'password123', '2233445566', 'Male', 1, '1988-04-04', 1),
('Charlie', 'Davis', 'charlie.davis@example.com', 'password123', '3344556677', 'Male', 1, '1991-05-05', 1),
('Emily', 'Wilson', 'emily.wilson@example.com', 'password123', '4455667788', 'Female', 1, '1993-06-06', 1),
('Frank', 'Miller', 'frank.miller@example.com', 'password123', '5566778899', 'Male', 1, '1987-07-07', 1),
('Grace', 'Taylor', 'grace.taylor@example.com', 'password123', '6677889900', 'Female', 1, '1994-08-08', 1),
('Henry', 'Anderson', 'henry.anderson@example.com', 'password123', '7788990011', 'Male', 1, '1989-09-09', 1),
('Isabella', 'Thomas', 'isabella.thomas@example.com', 'password123', '8899001122', 'Female', 1, '1995-10-10', 1);

INSERT INTO Excercise (name, difficulty) VALUES 
('Push-Up', 'Easy'),
('Pull-Up', 'Medium'),
('Squat', 'Easy'),
('Deadlift', 'Hard'),
('Bench Press', 'Medium'),
('Overhead Press', 'Medium'),
('Bicep Curl', 'Easy'),
('Tricep Dip', 'Medium'),
('Lunge', 'Easy'),
('Plank', 'Easy'),
('Burpee', 'Hard'),
('Mountain Climber', 'Medium'),
('Sit-Up', 'Easy'),
('Leg Press', 'Medium'),
('Lat Pulldown', 'Medium'),
('Rowing', 'Medium'),
('Jump Rope', 'Easy'),
('Box Jump', 'Hard'),
('Kettlebell Swing', 'Medium'),
('Russian Twist', 'Medium');

INSERT INTO TrainigRoutine (idUser, idExcercise, sets, reps) VALUES 
(1, 1, 3, 12),  -- User 1 with Push-Up
(1, 2, 3, 10),  -- User 1 with Pull-Up
(1, 3, 4, 15),  -- User 1 with Squat
(1, 4, 3, 8),   -- User 1 with Deadlift
(1, 5, 3, 10),  -- User 1 with Bench Press
(1, 6, 3, 10),  -- User 1 with Overhead Press
(2, 7, 3, 12),  -- User 2 with Bicep Curl
(2, 8, 3, 10),  -- User 2 with Tricep Dip
(2, 9, 4, 15),  -- User 2 with Lunge
(2, 10, 3, 60), -- User 2 with Plank (seconds)
(2, 11, 3, 12), -- User 2 with Burpee
(2, 12, 3, 20), -- User 2 with Mountain Climber
(3, 13, 3, 15), -- User 3 with Sit-Up
(3, 14, 3, 12), -- User 3 with Leg Press
(3, 15, 3, 10), -- User 3 with Lat Pulldown
(3, 16, 3, 15), -- User 3 with Rowing
(3, 17, 3, 100),-- User 3 with Jump Rope
(3, 18, 3, 10), -- User 3 with Box Jump
(4, 19, 3, 15), -- User 4 with Kettlebell Swing
(4, 20, 3, 20), -- User 4 with Russian Twist
(4, 1, 3, 12),  -- User 4 with Push-Up
(4, 2, 3, 10),  -- User 4 with Pull-Up
(4, 3, 4, 15),  -- User 4 with Squat
(4, 4, 3, 8),   -- User 4 with Deadlift
(5, 5, 3, 10),  -- User 5 with Bench Press
(5, 6, 3, 10),  -- User 5 with Overhead Press
(5, 7, 3, 12),  -- User 5 with Bicep Curl
(5, 8, 3, 10),  -- User 5 with Tricep Dip
(5, 9, 4, 15),  -- User 5 with Lunge
(5, 10, 3, 60); -- User 5 with Plank (seconds)

INSERT INTO TrainigRoutine (idUser, idExcercise, sets, reps) VALUES 
(6, 1, 3, 12),  -- User 6 with Push-Up
(6, 2, 3, 10),  -- User 6 with Pull-Up
(6, 3, 4, 15),  -- User 6 with Squat
(6, 4, 3, 8),   -- User 6 with Deadlift
(6, 5, 3, 10),  -- User 6 with Bench Press
(6, 6, 3, 10),  -- User 6 with Overhead Press
(7, 7, 3, 12),  -- User 7 with Bicep Curl
(7, 8, 3, 10),  -- User 7 with Tricep Dip
(7, 9, 4, 15),  -- User 7 with Lunge
(7, 10, 3, 60), -- User 7 with Plank (seconds)
(7, 11, 3, 12), -- User 7 with Burpee
(7, 12, 3, 20), -- User 7 with Mountain Climber
(8, 13, 3, 15), -- User 8 with Sit-Up
(8, 14, 3, 12), -- User 8 with Leg Press
(8, 15, 3, 10), -- User 8 with Lat Pulldown
(8, 16, 3, 15), -- User 8 with Rowing
(8, 17, 3, 100),-- User 8 with Jump Rope
(8, 18, 3, 10), -- User 8 with Box Jump
(9, 19, 3, 15), -- User 9 with Kettlebell Swing
(9, 20, 3, 20), -- User 9 with Russian Twist
(9, 1, 3, 12),  -- User 9 with Push-Up
(9, 2, 3, 10),  -- User 9 with Pull-Up
(9, 3, 4, 15),  -- User 9 with Squat
(9, 4, 3, 8),   -- User 9 with Deadlift
(10, 5, 3, 10), -- User 10 with Bench Press
(10, 6, 3, 10), -- User 10 with Overhead Press
(10, 7, 3, 12), -- User 10 with Bicep Curl
(10, 8, 3, 10), -- User 10 with Tricep Dip
(10, 9, 4, 15), -- User 10 with Lunge
(10, 10, 3, 60);-- User 10 with Plank (seconds)

INSERT INTO Wallet (idWallet, idUser) VALUES 
(1, 1),  -- Wallet 1 for User 1
(2, 2),  -- Wallet 2 for User 2
(3, 3),  -- Wallet 3 for User 3
(4, 4),  -- Wallet 4 for User 4
(5, 5),  -- Wallet 5 for User 5
(6, 6),  -- Wallet 6 for User 6
(7, 7),  -- Wallet 7 for User 7
(8, 8),  -- Wallet 8 for User 8
(9, 9),  -- Wallet 9 for User 9
(10, 10);-- Wallet 10 for User 10

INSERT INTO Card (number, type, expirationDate, cvv, isExpired, idWallet) VALUES 
(12345678, 'Visa', '2025-12-31', 123, false, 1),  -- Card for Wallet 1
(23456789, 'MasterCard', '2026-11-30', 234, false, 2),  -- Card for Wallet 2
(34567890, 'American Express', '2025-10-31', 345, false, 3),  -- Card for Wallet 3
(45678901, 'Discover', '2026-09-30', 456, false, 4),  -- Card for Wallet 4
(56789012, 'Visa', '2025-08-31', 567, false, 5),  -- Card for Wallet 5
(67890123, 'MasterCard', '2026-07-31', 678, false, 6),  -- Card for Wallet 6
(78901234, 'American Express', '2025-06-30', 789, false, 7),  -- Card for Wallet 7
(89012345, 'Discover', '2026-05-31', 890, false, 8),  -- Card for Wallet 8
(90123456, 'Visa', '2025-04-30', 901, false, 9),  -- Card for Wallet 9
(12345670, 'MasterCard', '2026-03-31', 123, false, 10);  -- Card for Wallet 10

INSERT INTO Points (accumulatedPoints, idUser) VALUES 
(100, 1),  -- 100 points for User 1
(200, 2),  -- 200 points for User 2
(150, 3),  -- 150 points for User 3
(300, 4),  -- 300 points for User 4
(250, 5),  -- 250 points for User 5
(350, 6),  -- 350 points for User 6
(400, 7),  -- 400 points for User 7
(450, 8),  -- 450 points for User 8
(500, 9),  -- 500 points for User 9
(550, 10); -- 550 points for User 10

#####DROP DATABASE ghostsupplements;