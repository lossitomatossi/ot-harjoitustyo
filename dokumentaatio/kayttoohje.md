FoodTrackerin tietokantataulu on vastaavanlainen:

CREATE TABLE FoodItem(
id INTEGER PRIMARY KEY,
name varchar(50),
foodType varchar(30),
quantity INTEGER,
quantityType varchar(20),
expirationDate varchar(11),
dateAdded varchar(11),
opened Boolean
);

FoodTracker kertoo tietoja syötettäessä jos ruuan nimi on liian pitkä tai puuttuu, ruuan määrään ei voi syöttää muita merkkejä kuin numeroita ja tämäkin kenttä kertoo jos on tyhjä. Ruuan määrän määrittävä toggleGroup valitsee automaattisesti arvon grammoiksi, mutta ruuan tyyppi pitää valita manuualisesti. Siihen ei vielä ole tarkistajaa.

Tietokannassa päivämäärät ovat kirjainyhdistelminä muodossa dd.MM.yyyy (esim 04.12.2018), pituus on 11 merkkiä jotta tietokanta on validi vuoden 9999 jälkeen, vaikkei ohjelma todennäköisesti sinne asti selviä.
