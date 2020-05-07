# Ohjelmistoprojekti I Haaga Helia UAS

Tiimi: OnnenOravat 

## TicketGuru -ohjelma 

Ohjelmistoprojekti I kurssilla toteutimme lipunmyyntiin tarkoitetun ohjelmiston: järjestelmä tarjoaa REST-rajapinnan lipunmyyntiin ja tapahtumien käsittelyyn. Asiakkaan toiveissa oli, että järjestelmän avulla voidaan lisätä, muokata ja poistaa tapahtumia sekä myydä erilaisia lippuja tapahtumiin. Myymättä jääneet liput pitää voida tulostaa ns. ovelle myytäväksi. Lippujen tulee olla myös uniikkeja ja luettavissa olevia. Lisäksi toiveissa oli raportointi tapahtumien myynnistä kertyneistä tuloista ja kappalemääristä. [Toteutimme ohjelman lisäksi yksinkertaisen REST-rajapintaa käyttävän clientin Reactilla](https://github.com/justuskeinanen/front/tree/master/src). Clientilla pystyy demoamaan järjestelmän perustoiminnallisuuksia: lipunmyyntiä, lippujen lukemista sekä tapahtumien raporttien lukua. Tarkoituksena kuitenkin on, että erillinen client-tiimi luo lopullisen käyttöliittymän, jolla voi käyttää kaikkia ohjelman tarjoamia ominaisuuksia.

Projekti on toteutettu pääasiassa seuraavilla teknologioilla: Java, SpringBooti, PostqreSQL ja React.


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

[Tietokantakaavio, tietokannan hakemisto datyyppeineen löytyy]https://dbdiagram.io/d/5e41478c9e76504e0ef1400c



## Tekninen kuvaus 

Teknisessä kuvauksessa esitetään järjestelmän toteutuksen suunnittelussa tehdyt tekniset ratkaisut, esim. 

Missä mikäkin järjestelmän komponentti ajetaan (tietokone, palvelinohjelma) ja komponenttien väliset yhteydet (vaikkapa tähän tyyliin: https://security.ufl.edu/it-workers/risk-assessment/creating-an-information-systemdata-flow-diagram/) 

Palvelintoteutuksen yleiskuvaus: teknologiat, deployment-ratkaisut yms. 

Keskeisten rajapintojen kuvaukset, esimerkit REST-rajapinta. Tarvittaessa voidaan rajapinnan käyttöä täsmentää UML-sekvenssikaavioilla. 

## REST-rajapinnat

Ohjelman tarkoitus on tarjota REST-rajapinta, jonka avulla voidaan toteuttaa lipunmyynnissä tarvittavia toiminnallisuuksia. Rajapintojen toteutuksessa on käytetty yleisesti käytössä olevia käytänteitä koskien mm. palveluiden nimeämistä. Ohjelma tarjoaa oleellisimmat toiminnot ja endpointit JSON-muotoisina API:nä. 

### ticketguru.herokuapp.com

Ohjelman basepath on https://ticketguru.herokuapp.com/
 
### /api/

Endpointin /autoapi/ takaa löytyy JSON-muotoisena kaikki tietokannassa oleva data linkityksineen. Endpoint on on luotu automaattisesti ja se on tarkoitettu lähinnä testausvaiheeseen sekä GET-pyyntöihin. Autentikointi vaadittu.
Endpointin /api/ takaa löytyy lipunmyyntiin tarvittavat rajapinnat, jotka on on dokumentoitu alla. Ainoastaan tulevat tapahtumat on avoin rajapinta, kaikissa muissa autentikointi on vaadittu. Käyttäjien hallintaan liittyvän endpointit ovat myös autorisoitu.

<details><summary>Yksityiskohtainen kuvaus rajapintojen käytöstä ja toteutuksesta:</summary>
 
 
### Tapahtumat / Events:

Hae tapahtumat: [GET /api/events](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/getevents.md)

Hae tietty tapahtuma GET /api/events/{id}

Etsi tiettyä tapahtumaa eri hakusanoilla: [GET /api/events/search/{property}={value}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/eventSearch.md)

Lisää tapahtuma [POST/api/events](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/postevents.md)

Muokkaa tapahtumaa [PUT or PATCH /api/events/{eventid}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/putevents.md)

Poista tapahtuma DELETE /api/events/{id}

Hae tapahtuman myyntitiedot [GET/api/events/{eventid}/raport](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/eventRaport.md)


### Tilaustapahtumat / Orders:
Hae tilaustapahtumat: [GET /api/orders](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/getorders.md)

Hae tietty tilaustapahtuma: [GET /api/orders/{orderid}]

Hae tiettyyn tilaukseen kuuluvat kaikki liput: [GET /api/orders({orderid}/tickets](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/getordersTickets.md)

Lisää tyhjä tilaustapahtuma-pohja [POST/api/orders](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/orders.md)

Lisää tilaustapahtuma [POST/orders/{eventid}/{typeid}/{lkm}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/postorders.md)

### Liput / Tickets:
Luodaan lippu tapahtumaan: [POST /api/events/{eventid}/tickets](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/eventTickets.md)

Deaktivoidaan tai aktivoidaan lippu (peruutustilanteet:) [PATCH /api/tickets/{ticketid}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/ticketActivate.md)

### Käyttäjät / Users
Käyttäjän lisäys: [POST /api/appusers](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/users.md)


</details>


## Turvallisuus

Järjestelmän turvallisuus on pyritty varmistamaan lähes kaiken kattavalla autentikoinnilla: lähes jokainen sivu vaatii sisäänkirjautumisen. Autentikoinnissa on käytetty Basic Authenticointia, lisäksi järjestelmään on alustettu JWT-autentikointi mahdollista myöhäisempää käyttöönottoa varten. Käyttäjien hallinta on lisäksi autorisoinnin takana ja vain admin-oikeudelliset käyttäjät pääsevät käsittelemään käyttäjiä. Uusien käyttäjien salasanat enkoodataan tietokantaan. Tällä hetkellä, kun ohjelman kehitystyöt jatkuvat vielä mobiiliversion kehittämisen suhteen, sallitaan Cors-konfiguroinnissa pyynnöt, sisältäen kaikki metodit, kaikista lähteistä. Tämä ei luonnollisesti ole turvallista ja heti kun client-kehitystyö on saatu valmiiksi, muutetaan konfigurointia niin, että se sallii pyynnöt vain tietyistä lähteistä, kuitenkin niin, että kaikki metodit ovat käytettävissä (kirjautuneille).


## Testaus 

Järjestelmässä suoritettiin joitain yksikkötestejä, useita integraatiotestejä sekä e2e-testejä. [Testit eivät löydy nykyisellään ohjelmasta, mutta niitä pääsee tarkastelemaan historiasta linkin takaa](https://github.com/NipeH/ohjelmistoprojekti1/tree/8593bb630ff3c1704e7610c983f96d42df4a39a0/src/test)

Testauksen raportointi:

[JUnit 5 testausta](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/testaus.md)

[Testaaminen Postman-ohjelmalla](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/testausPostmanissa.md)


### Korjattavaa ja huomioita jatkokehitykseen

/api/appusers antaa lisätä käyttäjiä ilman usernamea, vaikka username on @NotNull

### Asennustiedot 

Järjestelmän asennus on syytä dokumentoida kahdesta näkökulmasta: 

järjestelmän kehitysympäristö: miten järjestelmän kehitysympäristön saisi rakennettua johonkin toiseen koneeseen 

järjestelmän asentaminen tuotantoympäristöön: miten järjestelmän saisi asennettua johonkin uuteen ympäristöön. 

Asennusohjeesta tulisi ainakin käydä ilmi, miten käytettävä tietokanta ja käyttäjät tulee ohjelmistoa asentaessa määritellä (käytettävä tietokanta, käyttäjätunnus, salasana, tietokannan luonti yms.). 

### Käynnistys- ja käyttöohje 

Tyypillisesti tässä riittää kertoa ohjelman käynnistykseen tarvittava URL sekä mahdolliset kirjautumiseen tarvittavat tunnukset. Jos järjestelmän käynnistämiseen tai käyttöön liittyy joitain muita toimenpiteitä tai toimintajärjestykseen liittyviä asioita, nekin kerrotaan tässä yhteydessä. 

Usko tai älä, tulet tarvitsemaan tätä itsekin, kun tauon jälkeen palaat järjestelmän pariin ! 


