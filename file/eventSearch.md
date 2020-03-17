### Etsitään tiettyä tapahtumaa eri hakusanoilla: 

URL : /api/orders//api/events/search/{property}={value}

Method : GET

Auth required : NO (to be changed, see: https://github.com/NipeH/ohjelmistoprojekti1#avoimet-ja-autentikointia-vaativat-endpointit)

Permissions required : None (to be changed, see: https://github.com/NipeH/ohjelmistoprojekti1#avoimet-ja-autentikointia-vaativat-endpointit)

### Success Response
Code : 200 OK

Content examples:
GET: http://localhost:8080/api/events/search/description=lazer

  Response: 
  [
      {
          "eventid": 2,
          "name": "Ruisrock",
          "description": "Esiintyjinä mm. Major Lazer",
          "price": 200.0,
          "venue": "Turun ruissalo",
          "startTime": "2020-07-16T19:00:00",
          "endTime": "2020-07-05T23:59:00",
          "ticketInventory": 198
      }
  ]
  
  GET: http://localhost:8080/api/events/search/venue=nipe
  
    Response: 
          [
        {
            "eventid": 1,
            "name": "Syksyn sävel",
            "description": "Suomen luonnon ja vuodenaikojen innoittama konserttiesitys. Soittimina tusina sadeputkea ja märkä rätti",
            "price": 50.0,
            "venue": "Nipen takapiha. Kelivaraus. Poudan yllättäessä siirrymme roskakatokseen",
            "startTime": "2020-09-16T19:00:00",
            "endTime": "2020-09-16T21:00:00",
            "ticketInventory": -9
        }
    ]