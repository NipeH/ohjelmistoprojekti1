## Testaus 

Testattavia kohteita ovat autentikointi, tietokannasta haku, tietokantaan lisäys sekä lippu-luokan ominaisuudet. 

Autentikoinnin ja tietokannan testaamisessa on käytetty annotaatiota @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
palvelimen käynnistämiseen testausta varten. Portti asetetaan @LocalServerPort annotaatiolla omaan muuttujaansa. Lisäksi käytetään TestRestTempleteä,
annetaan käyttäjätunnus ja salasana. Tietokanta testit ovat kattavuudeltaan E2E-testejä, sillä ne käyvät sovelluksen tasot läpi clientista
tietokantaan asti. Eri tasojen välillä liikkuminen tekee niistä myös integraatiotestejä. 

Autentikointia testataan ottamalla yhteys sovellukseen kutsumalla basebathia väärillä käyttäjätunnuksilla. Pyynnön vastauksen oletetaan
olevan "UNAUTHORIZED" johon sitä verrataan assertEquals funktiolla. 

Tietokannasta hakua testataan kutsumalla /api/events endpointia oikeilla tunnuksilla ja vastauksen statuksen oletetaan olevan 200 ja vastauksen
bodyn sisältävän "eventid":n. Vastausta ja oletuksia verrataan assertEquals funktiolla.

Tietokantaan lisäystä testataan kutsumalla /api/events endpointia oikeilla tunnuksilla ja lähettämällä uuden Event-olio kutsun bodyssa. Vastauksen
statuksen oletetaan olevan 201, lisätty. Vastausta ja oletusta verrataan assertEquals funktiolla.

Lippuluokan ominaisuuksista testataan uuden lipun luontia sekä lipun lukemista. Testit määritetään käyttämään H2-tietokantaa @ActiveProfiles('test')
annotaation ja application-test.properties ominaisuuksien määrittelyillä. 

Lipun luomisesta halutaan testata, että uudet liput ovat "käyttämättömiä". Testissä luodaan uusi lippu asettamalla arvot: tapahtuma, lipputyyppi, tilaus ja lippukoodi.
Uuden lipun Used attribuutin oletetan olevan null ja vertaus tehdään assertEquals funktiolla.

Lipun lukeminen testataan luomalla uusi lippu ja hakemalla tämän hetkinen aika. Uusi lippu luetaan lippu-luokan read() toiminnolla. Lopuksi verrataan
että tämän hetkinen aika on joko ennen tai sama kuin lipun Used-attribuutti (LocalDateTime). Arvoja verrataan assertTrue(ehto1 tai ehto2) funktiolla.
