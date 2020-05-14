### Haetaan kaikki tapahtumat

**URL** : `/api/events`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

**Data constraints**


## Success Response


**Code** : `200 OK`

**Content example**

```json
[{
    "eventid": 1,
    "name": "Syksyn sävel",
    "description": "Suomen luonnon ja vuodenaikojen innoittama konserttiesitys. Soittimina tusina sadeputkea ja märkä rätti",
    "price": 50.0,
    "venue": "Nipen takapiha. Kelivaraus. Poudan yllättäessä siirrymme roskakatokseen",
    "startTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
    "endTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
    "ticketInventory": 2
},
{
    "eventid": 2,
    "name": "Talven sävel",
    "description": "Suomen luonnon ja vuodenaikojen innoittama konserttiesitys. Soittimina tusina sadeputkea ja märkä rätti",
    "price": 50.0,
    "venue": "Nipen takapiha. Kelivaraus. Poudan yllättäessä siirrymme roskakatokseen",
    "startTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
    "endTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
    "ticketInventory": 2
}
]
```
## Error Responses

**Condition** : `Jos toiminnon suorittaja ei ole kirjautunut sisään`

**Code** : `401 UNAUTHORIZED`

**Content example**

```json

{
    "timestamp": 1588883234540,
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/api/events"
}

```








