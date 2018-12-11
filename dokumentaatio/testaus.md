# Testausdokumentti

Ohjelmaa on testattu JUnitilla sekä manuaalisesti

## Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 46% ja haarautumakattavuus on 28%.

<img src="https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/jacoco.png" width="800">

Testaamatta jäänyt lukuisia tilanteita.


# Sovellukseen jääneet laatuongelmat:

- Dao luokkien testaus ei vielä täysin valmis
- Ruokien tyyppi testataan mutta ingredient tyyppisiä ruokia ei saa vielä lisättyä
- Pian pilaantuvien lista päivittää vasta hedelmien lisäyksen sitä mukaan kun tietokantaan lisätään tietoa
- Kaikkia tietokannan ruokia ei saa vielä listattua kerralla
- Arkkitehtuurikuvaus ei ole ajantasalla 4.12.2018 jälkeen
