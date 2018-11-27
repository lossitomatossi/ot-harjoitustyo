# Testausdokumentti

Ohjelmaa on testattu JUnitilla sekä manuaalisesti

## Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 40% ja haarautumakattavuus on 16%.

<img src="https://github.com/lossitomatossi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/jacoco.png" width="800">

Testaamatta jäänyt lukuisia tilanteita.


# Sovellukseen jääneet laatuongelmat:

- sovellus ei tiedosta onko ruuan määrää kuvaavalla rivillä numero
- sovellus ei ole vielä korjannut sql-tietokannan kanssa olevaa ongelmaa päivämäärien oikean näyttämistavan kanssa
- sovellus ei vielä tarkista ruuan tyyppiä ennen lisäämistä
- Dao luokkien testausta ei toteutettu
