# Ohjelmistoprojekti1
Haaga-Helia UAS Ohjelmistoprojekti 1 

## TicketGuru

Tiimi: OnnenOravat 

## Johdanto 

Projektimme aiheena on lipunmyyntijärjestelmä TicketGuru lipputoimistoon. Järjestelmän on tarkoitus tulevaisuudessa toimia myös verkkokauppana, joten järjestelmä valmistetaan myös verkkokaupan edellytykset mielessä pitäen. Järjestelmään voidaan lisätä tapahtumia ja tapahtumiin erilaisia lipputyyppejä haluttu määrä. Asiakkaille myydään liput tulostamalla ne liikkeessä. Lippuihin tulostuu asiakkaan nimi ja uniikki lippukoodi. Järjestelmä varmistaa, ettei lippuja myydä liikaa tai esimerkiksi tapahtuman jälkeen. Myymättä jääneet liput tulostetaan tapahtumien ovelle myytäväksi. Lipunmyyjien työtä helpottaaksemme, lippujen etsinnän on oltava helppoa ja lippuja tulee voida ostaa ja tulostaa useampi kerrallaan. Lisäksi järjestelmä tarjoaa omistajalle liiketoiminnan kannalta tarvittavaa dataa, kuten tapahtuman myyntitapahtumat. Projekti luodaan Spring Boot tekniikalla, käyttäen todennäköisesti MariaDB tietokantaa. Tarkoituksena on luoda responsiivinen, ensisijaitsesti kuitenkin desktopille tarkoitettu järjestelmä. 

## Järjestelmän määrittely 

Ohessa kuvattu järjestelmän käyttäjäryhmät ja heidän roolinsa yrityksessä. Käyttäjistä on luotu käyttäjätarinat, joiden pohjalta on luotu projektion työjono Trelloon sekä tarkempi toiminnallisten ominaisuuksien erittely. 

Rooli 1: Lipunmyyjä  
Rooli 2: Admin-käyttäjä / järjestelmänvalvoja 
Rooli 3: Asiakas (Verkkokauppa)  
  
### Rooli 1: Lipunmyyjä (ja fyysinen asiakas) käyttäjätarinat
* Lipunmyyjänä haluan etsiä lipun, jota asiakas toivoo kaupassa, jotta voin myydä sen hänelle. 
* Lipunmyyjänä haluan tulostaa lipun asiakkaalle, jotta asiakas saa lipun ja pääsee keikalle. 
* Lipun myyjäni haluan nähdä, onko haluttua lipputyyppiä saatavilla, jotta tiedän, voinko myydä lipun asiakkaalle. 
* Lipun myyjänä haluan lisätä halutut tuotteet ostoskoriin, jotta voin laskuttaa kaikki kerralla asiakkaalta. 
* Lipun myyjänä haluan löytää asiakkaan ostaman lipun tietokannasta nimellä tai koodilla, jotta asiakkaan esimerkiksi hukatessa lipun voin tulostaa hänelle uuden. 
* Asiakkaana haluan valita lipputyypin, jotta saan mahdollisesti alennusta. 
* Lipunmyyjänä en halua myydä lippuja, joita ei ole enää saatavilla, sillä se olisi huonoa asiakaspalvelua 
* Lipunmyyjänä edustan omistajaa, enkä halua, että samalla lipulla pääsee useampi sisälle, sillä haluan maksimoida tuotot 

#### Toiminnalliset vaatimukset

* Mahdollisuus valita lipputyyppi (Aikuinen, Lapsi, Eläkeläinen/Varusmies)  
* Etsiä tietokannasta haluttu lippu: päivämäärällä? nimellä? tapahtumapaikka? paikkakunta? esiintyjän nimi 
* Mahdollisuus valita tapahtuma, johon lippua myydään  
* Siirtää ostoskoriin 
* Mahdollisuus ostaa monta lippua kerralla  
* Mahdollisuus ottaa lippu pois käytöstä, mikäli asiakas palauttaa sen lipun koodi deaktivoidaan palauttaessa ja yksi uusi lippu vapautetaan myyntiin? automaatio? 
* Samaa lippua ei voi käyttää useammin kuin yhden kerran  
* Lippuja ei voi myydä enempää kuin määrän X: jos kpl määrä 0 -> ei onnistu 
* Lippuja ei voi myydä ajan dd.mm.yyyy jälkeen: jos pvm x -> ei onnistu 
* Myydyn lipun on oltava uniikki koodi tietokannassa 
* Lipun uniikin koodin on pystyttävä skannata  
* Myytyjen lippujen määrien (ja rahojen) on oltavissa nähtävissä datassa (ja oltava tulostettavissa)  
* Sovellusta voi käyttää Windows & OSX käyttöjärjestelmillä  
* Tietoturva: asiakkaan tietojenkäsittely, tarviiko lipunmyyjän nähdä kaikki asiakkaan tiedot? yms. gdpr jutut? salasanat..  
* Sisäänkirjautuminen, omat kirjautumistiedot   
* Asiakastiedot tarvittaessa 
* Sovellus mahdollistaa usean samanaikaisen asiakkaan vierailun  
* Sovellus mahdollistaa usean samanaikaisen myyntitapahtuman  
* Ennakkomyynnin loputtua loput liput tulostetaan ovella myytäviksi 

  
#### Ei-toiminnalliset vaatimukset (ei koodattavissa olevat vaatimukset)
* Sovelluksen on oltava asiakkaan saatavilla 24/7  
* Sovellusta on mahdollista käyttää mobiilisti  

  
### Rooli 2: Admin-käyttäjän käyttäjätarinat
* Admin käyttäjänä haluan lisätä myyjiä, jotta lippujen myynti onnistuu. 
* Admin käyttäjänä haluan muokata myyjän tietoja, jotta esimerkiksi salasanan unohdus ei vaikuta liiketoimintaan.
* Admin käyttäjänä haluan lisätä tapahtuman, jotta se saadaan myyntiin. 
* Admin käyttäjänä haluan muokata ja poistaa tapahtumia, jotta lipunmyyjillä on ajantasaiset tiedot. 
* Admin käyttäjänä haluan saada kaikki myymättä olevat liput tulostettua helposti, jotta ne saadaan ovelle myyntiin. 
* Admin käyttäjänä haluan kirjautua sisään, jotta voin käyttää admin-toiminnallisuuksia, joita muut eivät voi käyttää.
* Admin käyttäjänä haluan tarkastella kaikkia tehtyjä varauksia, esimerkiksi virhetilanteiden sattuessa.
 

#### Toiminnalliset vaatimukset
* Mahdollisuus valita lipputyyppi (Aikuinen, Lapsi, Eläkeläinen/Varusmies)  
* Mahdollisuus luoda tapahtuma 
* Mahdollisuus valita tapahtuma, johon lippua myydään  
* Mahdollisuus ostaa monta lippua kerralla  
* Mahdollisuus ottaa lippu pois käytöstä, mikäli asiakas palauttaa sen  
* Lippuja ei voi myydä enempää kuin määrän X  
* Lippuja ei voi myydä ajan dd.mm.yyyy jälkeen  
* Myydyn lipun on luotava uniikki koodi  
* Lipun uniikin koodin on pystyttävä skannata  
* Samaa lippua ei voi käyttää useammin kuin yhden kerran  
* Myytyjen lippujen määrien (ja rahojen) on oltavissa nähtävissä datassa (ja oltava tulostettavissa)  
* Tietoturva  
* Sisäänkirjautuminen, kirjautumistiedot 
* Mahdollisuus nollata/vaihtaa myyjän salasanaa   
* Sovellus mahdollistaa usean samanaikaisen asiakkaan vierailun  
* Sovellus mahdollistaa usean samanaikaisen myyntitapahtuman  
* Ennakkomyynnin loputtua loput liput tulostetaan ovella myytäviksi 

  
#### Ei-toiminnalliset vaatimukset (ei koodattavissa olevat vaatimukset) 
* Sovelluksen on oltava asiakkaan saatavilla 24/7  
* Sovellusta on mahdollista käyttää mobiilisti  

 

## Käyttöliittymä 
![kayttoliittymakaavio](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/kayttoliittymakaavio.png)

## Tietokanta 

Tietokannan kaavio löytyy ajantasaisena linkin takaa:

https://dbdiagram.io/d/5e41478c9e76504e0ef1400c



tobecontinued....





Tekninen kuvaus 

Teknisessä kuvauksessa esitetään järjestelmän toteutuksen suunnittelussa tehdyt tekniset ratkaisut, esim. 

Missä mikäkin järjestelmän komponentti ajetaan (tietokone, palvelinohjelma) ja komponenttien väliset yhteydet (vaikkapa tähän tyyliin: https://security.ufl.edu/it-workers/risk-assessment/creating-an-information-systemdata-flow-diagram/) 

Palvelintoteutuksen yleiskuvaus: teknologiat, deployment-ratkaisut yms. 

Keskeisten rajapintojen kuvaukset, esimerkit REST-rajapinta. Tarvittaessa voidaan rajapinnan käyttöä täsmentää UML-sekvenssikaavioilla. 

Toteutuksen yleisiä ratkaisuja, esim. turvallisuus. 

Tämän lisäksi 

ohjelmakoodin tulee olla kommentoitua 

luokkien, metodien ja muuttujien tulee olla kuvaavasti nimettyjä ja noudattaa johdonmukaisia nimeämiskäytäntöjä 

ohjelmiston pitää olla organisoitu komponentteihin niin, että turhalta toistolta vältytään 

Testaus 

Tässä kohdin selvitetään, miten ohjelmiston oikea toiminta varmistetaan testaamalla projektin aikana: millaisia testauksia tehdään ja missä vaiheessa. Testauksen tarkemmat sisällöt ja testisuoritusten tulosten raportit kirjataan erillisiin dokumentteihin. 

Tänne kirjataan myös lopuksi järjestelmän tunnetut ongelmat, joita ei ole korjattu. 

Asennustiedot 

Järjestelmän asennus on syytä dokumentoida kahdesta näkökulmasta: 

järjestelmän kehitysympäristö: miten järjestelmän kehitysympäristön saisi rakennettua johonkin toiseen koneeseen 

järjestelmän asentaminen tuotantoympäristöön: miten järjestelmän saisi asennettua johonkin uuteen ympäristöön. 

Asennusohjeesta tulisi ainakin käydä ilmi, miten käytettävä tietokanta ja käyttäjät tulee ohjelmistoa asentaessa määritellä (käytettävä tietokanta, käyttäjätunnus, salasana, tietokannan luonti yms.). 

Käynnistys- ja käyttöohje 

Tyypillisesti tässä riittää kertoa ohjelman käynnistykseen tarvittava URL sekä mahdolliset kirjautumiseen tarvittavat tunnukset. Jos järjestelmän käynnistämiseen tai käyttöön liittyy joitain muita toimenpiteitä tai toimintajärjestykseen liittyviä asioita, nekin kerrotaan tässä yhteydessä. 

Usko tai älä, tulet tarvitsemaan tätä itsekin, kun tauon jälkeen palaat järjestelmän pariin ! 



