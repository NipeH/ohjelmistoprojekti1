### Etsitään tiettyä tapahtumaa eri hakusanoilla: 

**URL** : `/api/events/search/{property}={value}`

**Method** : GET

**Auth required** : YES

**Permissions required** : NOT

### Success Response

**Code** : 200 OK

**Content examples**:

GET: /api/events/search/description=lazer

**Response**: 

  ```json
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
  ```
  GET: /api/events/search/venue=nipe
  
  ```json
  
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
    
   ````
    


GET /api/events/search/venue=makkara

**Condition** : If event doesn't exist with given values, response is an empty list

**Content example**

```json
[
]
```
