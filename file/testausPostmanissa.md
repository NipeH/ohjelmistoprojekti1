## Testaaminen käyttäen Postmania

Testit ovat e2e-testejä, postman lähettää http/https-pyynnön palvelimelle, pyyntö käsitellään palvelinohjelmiston controllerissa, joka puolestaan välittää pyynnöstä laaditun kyselyn tietokantaan. Kontrolleri lähettää vastauksensa takaisin http-clientille (Postman), joka suorittaa sen jälkeen testausskriptin, joka vertaa testaajan määrittämiä oletuksia palvelimelta saatuun vastaukseen.
Koska testit läpäisevät kaikki tietotasot UI:ta lukuunottamatta, ne käsitetään e2e-testeiksi. 

Testien toteutuksessa on käytetty ensinnäkin Postmanin ydintoiminnallisuuksiin kuuluvaa mahdollisuutta lähettää tarkoin muotoiltuja http-pyyntöjä. Pyyntöjen vastauskoodista voidaan jo mahdollisesti tehdä tiettyjä johtopäätöksiä. (vastausten ei pitäisi sisältää ainakaan 500-alkuisia statuksia (internal server error)).

Testien tarkentamiseen käytetään javascript-kielellä kirjoitettuja skriptejä, joissa verrataan vastausta tiettyihin ennakko-odotuksiin. Postmanin skriptit mahdollistavat myös ei-toiminnallisten vaatimusten testaamisen. Tässä on toteutettu kaikkien pyyntöjen osalta testivaatimus palvelimen vastausnopeudesta (alle 200 ms).  

Testausskriptien toteutuksessa on soveltuvin osin pyritty hyödyntämään 'snippettejä' eli Postmanin tarjoamia valmiita koodinpätkiä, jotka testaavat yleisimpiä sovellusten ongelmakohtia/yleisimpiä toivottuja testauskohteita. Lisäksi on käytetty testausympäristöön integroituvia Tiny Validator-validointeja. 

Jo pelkästään em. toiminnallisuuksia hyödyntämällä saadaan ohjelmiston kehitysvaiheeseen nähden melko kattava kuva sen toimintaedellytyksistä. Testausta pystytään helposti myös automatisoimaan. Tällä hetkellä testausproseduuri käydään läpi vain manuaalisesti käynnistämällä, koska lähdekoodin kanssa ollaan tekemisissä joka tapauksessa päivittäin. Ylläpitovaiheessa/sprinttien harventuessa voidaan ottaa käyttöön esimerkiksi kerran vuorokaudessa tapahtuva testaaminen. 

Testien kattavuutta pyritään lisäämään ajan ja resurssien puitteissa, sekä aina lisättäessä ohjelmaan uusia ominaisuuksia/tehtäessä muutoksia, joilla voi olettaa olevan mahdollisia vaikutuksia ohjelman toimintaan. 

<details>
  <summary>LUO TILAUSPOHJA 
  </summary>
  <h3> ODOTUKSET </h3>
  <ol>
    <li>Odotetaan, että vastaus on "onnistunut POST-pyyntö" ts. joko 201 tai 202. </li>
    <li>Vastauksen odotetaan saapuvan alle 200 ms.</li>
    <li>Vastauksessa odotetaan olevan Content-Type-header.</li>
  </ol>
    <h3> PYYNTÖ </h3>
  POST: https://ticketguru.herokuapp.com/api/orders <br/>
  AUTH: Basic auth <br/>
  BODY: {} <br/>
    <h3> TESTISKRIPTI </h3>
  
  <details>
  <summary>
  TESTS:
  </summary>
  
  
  <code>
    
 1
  
      pm.test("Successful POST request", function () {
        pm.expect(pm.response.code).to.be.oneOf([201,202]);
    });
    
 2
 
    pm.test("Response time is less than 200ms", function () {
        pm.expect(pm.response.responseTime).to.be.below(200);
    });

3

    pm.test("Content-Type is present", function () {
        pm.response.to.have.header("Content-Type");
    });

</code>
</details>
</details>


<details>
  <summary>HAE TILAUKSET  
  </summary>
  <h3> ODOTUKSET </h3>
  <ol>
    <li>Odotetaan, että vastaus on "onnistunut GET-pyyntö", 200 OK. </li>
    <li>Odotetaan, että vastaus sisältää edellisessä testissä luodun tilauksen orderid:n </li>
  </ol>
    <h3> PYYNTÖ </h3>
  GET: https://ticketguru.herokuapp.com/api/orders <br/>
  AUTH: Basic auth, inherited (admin) <br/>
  BODY: Ei bodya <br/>
    <h3> TESTISKRIPTI </h3>
  
  <details>
  <summary>
  TESTS: 
  </summary>
  
  <code>
  
1  
   
    pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
    });
    
2

    pm.test("Edellä luodun tilauksen orderid löytyy vastauksesta", 
    function () {
        var jsonData = pm.response.json();
        var expectedOID = pm.globals.get("expectedOID");
        
    var foundOIDs = [];
    for (var i = 0; i < jsonData.length; i++) {
            foundOIDs.push(jsonData[i].orderid);
        }
        
        var loytyi = foundOIDs.includes(expectedOID);
        
        pm.expect(Boolean(loytyi)).to.eql(Boolean(true));
     });
</code>
</details>
</details>
  

