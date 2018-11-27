# FoodTracker

FoodTracker on sovellus joka mahdollistaa käyttäjälle paremman tarkkailun ruuan säilymiseen.

Tietokantaan voi lisätä tietoja kaikkien kenttien ollessa oikein, mutta testejä ei ole.

# Dokumentaatio

[Käyttöohje](https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmaarittely](https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus]

[Testausdokumentti]

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
