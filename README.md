# Ohjelmistoprojekti I Haaga Helia UAS

Tiimi: OnnenOravat 

## TicketGuru -ohjelma 

Ohjelmistoprojekti I kurssilla toteutimme lipunmyyntiin tarkoitetun ohjelmiston: järjestelmä tarjoaa REST-rajapinnan lipunmyyntiin ja tapahtumien käsittelyyn. Asiakkaan toiveissa oli, että järjestelmän avulla voidaan lisätä, muokata ja poistaa tapahtumia sekä myydä eri tyyppisiä lippuja tapahtumiin. Myymättä jääneet liput pitää voida tulostaa ovelle myytäväksi. Lippujen tulee olla uniikkeja ja luettavissa olevia. Lisäksi asiakkaan toiveissa oli raportointi tapahtumien myynnistä kertyneistä tuloista ja kappalemääristä. [Toteutimme ohjelman lisäksi yksinkertaisen REST-rajapintaa käyttävän clientin Reactilla](https://github.com/justuskeinanen/front/tree/master/src). Clientilla pystytään demoamaan järjestelmän perustoiminnallisuuksia: lipunmyyntiä, lippujen lukemista sekä tapahtumien raporttien lukua. Tarkoituksena kuitenkin on, että erillinen client-tiimi luo lopullisen käyttöliittymän, jolla voi käyttää kaikkia ohjelman tarjoamia ominaisuuksia.

Projekti on toteutettu pääasiassa seuraavilla teknologioilla: Java, SpringBoot, PostqreSQL ja React.


## Järjestelmän määrittely 

Järjestelmän tärkein käyttäjäryhmä on lipunmyyjät (rooli 1), lisäksi järjestelmää käyttävät järjestelmänvalvojat (rooli 2) sekä tulevaisuudessa mahdollisesti myös asiakkaat (rooli 3), mikäli järjestelmästä kehitetään verkkokauppasovellus. Asiakkaan roolia sivutaan, mutta ei käsitellä tässä kohtaa. Järjestelmän määrittely on tehty luomalla myyjän ja järjestelmänvalvojan käyttäjärooleista käyttäjätarinoita, joiden perusteella on identifioitu toiminnallisia ja ei toiminnallisia vaatimuksia. 

<details><summary>Käyttäjätarinat ja toiminnallisuudet käyttäjäryhmittäin</summary>
  

### Rooli 1: Lipunmyyjän käyttäjätarinat

* Lipunmyyjänä haluan etsiä lipun, jota asiakas toivoo kaupassa, jotta voin myydä sen hänelle. 
* Lipunmyyjänä haluan tulostaa lipun asiakkaalle, jotta asiakas saa lipun ja pääsee keikalle. 
* Lipun myyjäni haluan nähdä, onko haluttua lipputyyppiä saatavilla, jotta tiedän, voinko myydä lipun asiakkaalle. 
* Lipun myyjänä haluan lisätä halutut tuotteet ostoskoriin, jotta voin laskuttaa kaikki kerralla asiakkaalta. 
* Lipun myyjänä haluan löytää asiakkaan ostaman lipun tietokannasta nimellä tai koodilla, jotta asiakkaan esimerkiksi hukatessa lipun voin tulostaa hänelle uuden. 
* Lipunmyyjänä tahdon tarjota asiakkaalle lipun, jossa on hänelle oikeutettu hinnanalennus.
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

 </details>

## Käyttöliittymä 
![kayttoliittymakaavio](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/kayttoliittymakaavio.png)

(luonnos client-tiimille | ei vastaa demo frontendia)

## Tietokanta 

[Tietokantakaavio ja datatyypit](https://dbdiagram.io/d/5e41478c9e76504e0ef1400c)


## REST-rajapinnat

Ohjelman tarkoitus on tarjota REST-rajapinta, jonka avulla voidaan toteuttaa lipunmyynnissä tarvittavia toiminnallisuuksia. Rajapintojen toteutuksessa on käytetty yleisesti käytössä olevia käytänteitä koskien mm. palveluiden nimeämistä. Ohjelma tarjoaa oleellisimmat toiminnot ja endpointit JSON-muotoisina rajapintoina. 

Ohjelman basepath on https://ticketguru.herokuapp.com/
 
### /autoapi/ ja /api/

Endpointin /autoapi/ takaa löytyy JSON-muotoisena kaikki tietokannassa oleva data linkityksineen. Endpoint on on luotu automaattisesti pom.xml rest dependenssillä ja se on tarkoitettu lähinnä testausvaiheeseen sekä GET-pyyntöihin (autentikointi vaadittu).
Endpointin /api/ takaa löytyy lipunmyyntiin tarvittavat rajapinnat, jotka on on dokumentoitu alla. Ainoastaan tulevat tapahtumat on avoin rajapinta, kaikissa muissa autentikointi on vaadittu. Käyttäjien hallintaan liittyvät endpointit ovat myös auktorisoitu.

<details><summary>Yksityiskohtainen kuvaus /api/ rajapintojen käytöstä ja toteutuksesta:</summary>
 
 
### Tapahtumat / Events:

Hae tapahtumat: [GET /api/events](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/getevents.md)

Hae tietty tapahtuma [GET /api/events/{id}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/getEvent.md)

Etsi tiettyä tapahtumaa eri hakusanoilla: [GET /api/events/search/{property}={value}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/eventSearch.md)

Lisää tapahtuma [POST/api/events](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/postevents.md)

Muokkaa tapahtumaa [PUT or PATCH /api/events/{eventid}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/putevents.md)

Poista tapahtuma [DELETE /api/events/{id}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/deleteEvent.md)

Hae tapahtuman myyntitiedot [GET/api/events/{eventid}/raport](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/eventRaport.md)


### Tilaustapahtumat / Orders:
Hae tilaustapahtumat: [GET /api/orders](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/getorders.md)

Hae tietty tilaustapahtuma: [GET /api/orders/{orderid}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/getOrder.md)

Hae tiettyyn tilaukseen kuuluvat kaikki liput: [GET /api/orders({orderid}/tickets](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/getordersTickets.md)

Lisää tyhjä tilaustapahtuma-pohja [POST/api/orders](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/orders.md)

Lisää tilaustapahtuma [POST/orders/{eventid}/{typeid}/{lkm}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/postorders.md)

### Liput / Tickets:
Luodaan lippu tapahtumaan: [POST /api/events/{eventid}/tickets](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/eventTickets.md)

Deaktivoidaan tai aktivoidaan lippu (peruutustilanteet:) [PATCH /api/tickets/{ticketid}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/ticketActivate.md)

Hae kaikki liput [GET /api/tickets](https://github.com/NipeH/ohjelmistoprojekti1/tree/master/file/getTickets.md)

Hae yksittäinen lippu id tai ticketcode [GET /api/tickets/{id}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/getTicket.md)

Luetaan lippu käytetyksi [PATCH /api/tickets/read{ticketcode}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/readTicket.md)

Poista lippu [DELETE api/tickets/{id}](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/deleteTicket.md)

### Käyttäjät / Users
Käyttäjän lisäys: [POST /api/users](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/users.md)


</details>


## Turvallisuus

Järjestelmän turvallisuus on pyritty varmistamaan lähes kaiken kattavalla autentikoinnilla: lähes jokainen sivu vaatii sisäänkirjautumisen. Autentikoinnissa on käytetty Basic Authenticointia, lisäksi järjestelmään on alustettu JWT-autentikointi mahdollista myöhäisempää käyttöönottoa varten. Käyttäjien hallinta on lisäksi auktorisoinnin takana ja vain admin-oikeudelliset käyttäjät pääsevät käsittelemään käyttäjiä. Uusien käyttäjien salasanat enkoodataan tietokantaan. Tällä hetkellä, kun ohjelman kehitystyöt jatkuvat vielä mobiiliversion kehittämisen suhteen, sallitaan Cors-konfiguroinnissa kaikki pyynnöt kaikista lähteistä. Tämä ei luonnollisesti ole turvallista ja heti kun client-kehitystyö on saatu valmiiksi, muutetaan konfigurointia niin, että järjestelmä sallii pyynnöt vain tietyistä lähteistä, kuitenkin niin, että kaikki metodit ovat käytettävissä (kirjautuneille).


## Testaus 

Järjestelmässä suoritettiin joitain yksikkötestejä, useita integraatiotestejä sekä e2e-testejä. [Testit eivät löydy nykyisellään ohjelmasta, mutta niitä pääsee tarkastelemaan historiasta linkin takaa](https://github.com/NipeH/ohjelmistoprojekti1/tree/8593bb630ff3c1704e7610c983f96d42df4a39a0/src/test)

Testauksen raportointi:

[JUnit 5 testausta](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/testaus.md)

[Testaaminen Postman-ohjelmalla](https://github.com/NipeH/ohjelmistoprojekti1/blob/master/file/testausPostmanissa.md)


### Korjattavaa ja huomioita jatkokehitykseen

/api/appusers antaa lisätä käyttäjiä ilman usernamea, vaikka username on @NotNull

Virheiden käsittely on tässä vaiheessa puutteellista: kaikki käyttäjän aiheuttamat virhetilanteet tulisi käydä läpi REST-rajapintaa ajatellen.

REST-rajapinnan endpointien nimeäminen ei ole täysin hyvien käytäntöjen mukaista: verbejä tulisi poistaa ja sisäkkäisyyksiä poistaa.

Ohjelmaan olisi järkevää toteuttaa palvelukerros

## Asennustiedot 

### Järjestelmän kehitysympäristön asennus

Asenna/tarkista, että sinulla on asennettuna seuraavat apuvälineet, kirjastot ja työkalut:
<ul>
 <li>java</li>
 <li>jdk</li>
 <li>java ide</li>
 <li>postgre</li>
 <li>pgadmin tai postgresql:n komentorivityökalu</li>
 <li>node</li>
 <li>npm</li>
 <li>jos haluat hallinnoida herokua komentoriviltä käsin, niin heroku cli</li>
</ul>

 Lisäksi tarvitset käyttäjätilin ja -tunnukset tietokantaan ja versionhallintaan: 
 
 <ul>
  <li>postgresql-tunnukset, luodaan postgresql:n asennuksen yhteydessä</li>
  <li>github-tunnukset, jos ei ole oikeuksia tähän projektiin, voit forkata sen ja lähettää tarkistettavaksi. </li>
  <li>heroku tunnukset (ei välttämätön, jos et joudu tekemään muutoksia herokussa)</li>
 </ul>
 
 **Lue huolellisesti dokumentaatio.**
 Voit ladata viimeisimmän version githubista https://github.com/NipeH/ohjelmistoprojekti1
 luo kehitystyölle oma branch 
 mene valitsemaasi kehitysympäristöön ja tuo projekti sinne. 
 muista vaihtaa `application.properties` -tiedostossa postgresql- tunnukset omiksesi, jotta voit testata tietokanta-toimintoja paikallisesti. Älä kuitenkaan lähetä näitä muutoksia gitiin. 
 herokuun päivittyy kaikki projektin githubrepositoryn master-haaraan tehtävät muutokset. Kun versio on valmis ja testattu, kehityksessä käytetty haara voidaan yhdistää masteriin, jolloin muutokset päivittyvät myös herokussa olevaan tuotantoversioon. 
 Muista tehdä vähintään kaikki automatisoidut testit ennen julkaisua(et.yksikkötestit) ja sen jälkeen (E2E-testipatteri Postmanissa). 
 

## Käynnistys- ja käyttöohje 

Ohjelman tarjoamaaa REST-rajapintaa pääsee käyttämään: https://ticketguru.herokuapp.com/ -linkin takaa tunnuksilla: niilo:salasana, joka on kovakoodattu peruskäyttäjä. Ohjelman demoamiseen käytettävä front löytyy https://ticketgurufront.herokuapp.com -linkin takaa. Sen käyttöön ei tarvita tällä hetkellä salasanaa, vaan tunnukset on koodattu REST-rajapintaa kutsuviin pyyntöihin.



