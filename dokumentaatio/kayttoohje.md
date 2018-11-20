FoodTrackerin tietokantataulu on vastaavanlainen:

CREATE TABLE FoodItem(
id INTEGER PRIMARY KEY,
name varchar(50),
foodType varchar(30),
quantity INTEGER,
quantityType varchar(20),
expirationDate Date,
dateAdded Date,
opened Boolean
);
