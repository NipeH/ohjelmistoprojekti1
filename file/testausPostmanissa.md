##Testaaminen käyttäen Postmania

Testit ovat e2e-testejä, postman lähettää http/https-pyynnön palvelimelle, pyyntö käsitellään palvelinohjelmiston controllerissa, joka puolestaan välittää pyynnöstä laaditun kyselyn tietokantaan. Kontrolleri lähettää vastauksensa takaisin http-clientille (Postman), joka suorittaa sen jälkeen testausskriptin, joka vertaa testaajan määrittämiä oletuksia palvelimelta saatuun vastaukseen.
Koska testit läpäisevät kaikki tietotasot UI:ta lukuunottamatta, ne käsitetään e2e-testeiksi. 

Testien toteutuksessa on käytetty ensinnäkin Postmanin ydintoiminnallisuuksiin kuuluvaa mahdollisuutta lähettää tarkoin muotoiltuja http-pyyntöjä. pyyntöjen onnistuminen esitetään ensimmäisessä sarakkeessa, tästä voidaan jo päätellä, että ainakin järjestelmä on toimintakunnossa ja vastaa pyyntöihin, joissa on asianmukainen autentikointi, ja että vaadittavat toiminnallisuudet ovat toteutettu odotetusti. 
200-alkuiset vastaukset näkyvät vihreinä ja 400 ja 500-alkuiset vastaukset punaisena. 

Testien tarkentamiseen käytetään javascript-kielellä kirjoitettuja skriptejä, joissa verrataan vastausta tiettyihin ennakko-odotuksiin. Postmanin skriptit mahdollistavat myös ei-toiminnallisten vaatimusten testaamisen. Tässä on toteutettu kaikkien pyyntöjen osalta testivaatimus palvelimen vastausnopeudesta (alle 200 ms).  

Testausskriptien toteutuksessa on soveltuvin osin pyritty hyödyntämään 'snippettejä' eli Postmanin tarjoamia valmiita koodinpätkiä, jotka testaavat yleisimpiä sovellusten ongelmakohtia/yleisimpiä toivottuja testauskohteita. Lisäksi on käytetty testausympäristöön integroituvia Tiny Validator-validointeja. 

Jo pelkästään em. toiminnallisuuksia hyödyntämällä saadaan ohjelmiston kehitysvaiheeseen nähden melko kattava kuva sen toimintaedellytyksistä. Testausta pystytään helposti myös automatisoimaan. Tällä hetkellä testausproseduuri käydään läpi vain manuaalisesti käynnistämällä, koska lähdekoodin kanssa ollaan tekemisissä joka tapauksessa päivittäin. Ylläpitovaiheessa/sprinttien harventuessa otetaan käyttöön oletettavasti kerran vuorokaudessa tapahtuva testaaminen. 

Testien kattavuutta pyritään lisäämään ajan ja resurssien puitteissa, sekä aina lisättäessä ohjelmaan uusia ominaisuuksia/tehtäessä muutoksia, joilla voi olettaa olevan mahdollisia vaikutuksia ohjelman toimintaan. 



