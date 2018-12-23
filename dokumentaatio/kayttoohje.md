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

Kun käynnistät sovelluksen, vasemmassa laidassa on lomakkeet yksittäisten ruokien poistamiseen ja lisäämiseen. Jos klikkaat edit the database nappia pääset näkymään, jossa voi lisätä tai poistaa useita ruokia kerralla. Jos painat nappia jossa lukee commit changes, kaikki tekemäsi muutokset muutetaan tietokantaan. Jos painat cancel, pääset taas näkemään tavallisen näkymän.

Taulukoissa olevien asioiden järjestystä pystyy muuttamaan taulukon yläosassa olevien "nappien" avulla. Kussakin napissa lukee mitä informaatiota sillä järjestysnumerolla olevassa solussa on.

Oikean toiminnan mahdollistamiseksi ohjelma olettaa että tietokannat sijaitsevat samassa kansiossa kuin ohjelma.

FoodTracker kertoo tietoja syötettäessä jos ruuan nimi on liian pitkä tai puuttuu, ruuan määrään ei voi syöttää muita merkkejä kuin numeroita ja tämäkin kenttä kertoo jos on tyhjä. Ruuan määrän määrittävä toggleGroup valitsee automaattisesti arvon grammoiksi, mutta ruuan tyyppi pitää valita manuualisesti.

Tietokannassa päivämäärät ovat kirjainyhdistelminä muodossa dd.MM.yyyy (esim 04.12.2018), pituus on 11 merkkiä jotta tietokanta on validi vuoden 9999 jälkeen, vaikkei ohjelma todennäköisesti sinne asti selviä.
