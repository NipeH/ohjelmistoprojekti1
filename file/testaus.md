## Testaus 

Testattavia kohteita ovat autentikointi, tietokannasta haku, tietokantaan lisäys sekä lippu-luokan ominaisuudet. Testit on toteutettu JUnit 5:lla.

Autentikoinnin ja tietokannan testaamisessa on käytetty annotaatiota @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
palvelimen käynnistämiseen testausta varten. Portti asetetaan @LocalServerPort annotaatiolla omaan muuttujaansa. Lisäksi käytetään TestRestTempleteä, jolle
annetaan käyttäjätunnus ja salasana. Tietokantatestit ovat kattavuudeltaan käytännössä E2E-testejä, sillä ne käyvät sovelluksen eri tasot läpi clientista
tietokantaan asti. Eri tasojen välillä liikkuminen tekee niistä myös toisaalta myös integraatiotestejä. 

Autentikointia testataan ottamalla yhteys sovellukseen kutsumalla basebathia väärillä käyttäjätunnuksilla. Pyynnön vastauksen oletetaan
olevan "UNAUTHORIZED". Vastauksen statusta ja oletusta verrataan assertEquals funktiolla. 

Tietokannasta hakua testataan kutsumalla /api/events endpointia oikeilla tunnuksilla ja vastauksen statuksen oletetaan olevan OK 200 ja vastauksen
bodyn sisältävän eventien listauksien ominaisuuksia, eli esim. "eventid":n. Vastausta ja oletuksia verrataan assertEquals funktiolla.

Tietokantaan lisäystä testataan kutsumalla /api/events endpointia oikeilla tunnuksilla ja lähettämällä uuden Event-olio kutsun bodyssa. Vastauksen
statuksen oletetaan olevan 201, lisätty. Vastausta ja oletusta verrataan assertEquals funktiolla.

Lippuluokan ominaisuuksista testataan uuden lipun luontia sekä lipun lukemista. Testit määritetään käyttämään H2-tietokantaa @ActiveProfiles('test')
annotaation ja application-test.properties ominaisuuksien määrittelyillä. 

Lipun luomisesta halutaan testata, että uudet liput ovat "käyttämättömiä". Testissä luodaan uusi lippu asettamalla arvot: tapahtuma, lipputyyppi, tilaus ja lippukoodi.
Uuden lipun Used attribuutin oletetan olevan null ja vertaus tehdään assertEquals funktiolla.

Lipun lukeminen testataan luomalla uusi lippu ja hakemalla tämän hetkinen aika. Uusi lippu luetaan lippu-luokan read() toiminnolla. Lopuksi verrataan
että tämän hetkinen aika on joko ennen tai sama kuin lipun Used-attribuutti (LocalDateTime). Arvoja verrataan assertTrue(ehto1 tai ehto2) funktiolla.
