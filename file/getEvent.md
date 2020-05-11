### Haetaan tietty tapahtuma

**URL** : GET /api/events/{id}

**Method** : GET

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Haettavan eventin id vaaditaan

### Success Response

**Condition **: Haetaan tapahtumaa olemassaolevalla eventid:llä

**Code** : 200 OK

**Content example**

```json
{
    "eventid": 1,
    "name": "Syksyn sävel",
    "description": "Suomen luonnon ja vuodenaikojen innoittama konserttiesitys. Soittimina tusina sadeputkea ja märkä rätti",
    "venue": "Nipen takapiha. Kelivaraus. Poudan yllättäessä siirrymme roskakatokseen",
    "price": 50.0,
    "startTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
    "endTime": "2020-10-16T18:00Z[Etc/UTC]",
    "ticketInventory": 2
}
```
### Error Responses

Haetaan tapahtumaa eventid:llä jota ei ole käytössä

**Code **: 404 NOT FOUND

**Content example**

```json

{
    "timestamp": 1589218555925,
    "status": 404,
    "error": "Not Found",
    "message": "Tapahtumaa ei löydy",
    "path": "/api/events/12"
}
```
