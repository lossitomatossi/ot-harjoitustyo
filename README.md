# FoodTracker

FoodTracker on sovellus joka mahdollistaa käyttäjälle paremman tarkkailun ruuan säilymiseen. Ruuat lisätään graafisen käyttöliittymän kautta, jonka jälkeen ne ilmestyvät sille listalle johon ne kuuluvat.

Eri listoja ovat mm. pian pilaantuvat joka sisältää kaikki tuoreet ruuat sekä seuraavan kolmen päivän aikana parasta ennen päivämääränsä saavuttavat ruuat. Tämän lisäksi ruuat voi listata seuraavan viikon, kuukauden ja vuoden aikana pilaantuviin sekä aakkosjärjestykseen.

Pilaantuneet ruuat näkyvät omalla listallaan josta ne voi helposti poistaa. Ruokien määriä voi myös muuttaa kulutuksen mukaan.

Ohjelma myös ilmoittaa käyttäjälle jos jossain kentässä on virheellistä tietoa, mutta tämä toiminnallisuus ei vielä toimi.

# Dokumentaatio

[Käyttöohje](https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmaarittely](https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus]

[Testausdokumentti](https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

# Releaset

# Komentorivitoiminnot

## Testaus

Testit suoritetaan komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla
```
mvn jacoco:report
```
Kattavuusrapottia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

## Suoritettavan jarin generointi

Komento
```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston FoodTracker-1.0-SNAPSHOT.jar #"¤%#¤%¤#%¤#% tarkista tää

## JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/FoodTracker/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
