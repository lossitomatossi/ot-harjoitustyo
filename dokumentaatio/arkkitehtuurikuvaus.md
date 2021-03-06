# Arkkitehtuurikuvaus
<img src="https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuurikuvaus2.png" width="800">

## Käyttöliittymä

Käyttöliittymä sisältää kaksi erillistä näkymää
- näkymä jossa näkyy vain tietokannassa olevat ruuat ja jossa voi lisätä yksittäisiä ruokia.
- näkymä jossa voi tehdä useita muutoksia kerrallaan, ja jotka tehdään tietokantaan vasta kun käyttäjä hyväksyy muutokset.

# Sekvenssikaaviot

## Sekvenssikaavio ruuan lisäämiselle

Sekvenssikaaviossa oletetaan käyttäjän täyttäneen kaikki kentät oikein ja valinneen ruokatyypiksi "fresh" sekä määrän tyypiksi "pieces". Ennen itse suoritusta tapahtuvat testit tarkastavat ensin ettei tekstikenttiä ole täytetty virheellisesti, jonka jälkeen luodaan FreshFood olio omena joka lisätään tietokantaan FreshFoodDao luokan metodin addToDatabase(FreshFood freshFood) avulla. Tämän jälkeen päivitämme listan vastaamaan lisäyksen jälkeistä tilannetta.

<img src="https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio%20ruuan%20lis%C3%A4%C3%A4misest%C3%A4.png" width="800">
