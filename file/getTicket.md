# Yksittäisen lipun tietojen haku

Jos halutaan tietää mihin tapahtumaan kyseinen lippu on lipun koodin tai id:n perusteella perusteella. 


**URL** : `/api/tickets/{id}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Lipun id lähetetään URL:issa.

## Success Response

**Condition** : Jos kaikki kentät on annettu ja lippukoodi tai lipun id on olemassa.

**Code** : `200 OK`

**Content example**

```json
[
{
    "ticketid": 8,
    "event": {
        "eventid": 2,
        "name": "Ruisrock",
        "description": "Esiintyjinä mm. Major Lazer",
        "venue": "Turun ruissalo",
        "price": 200.0,
        "startTime": "2020-07-04T16:00:00.078Z[Etc/UTC]",
        "endTime": "2020-07-05T20:59Z[Etc/UTC]",
        "ticketInventory": 200
    },
    "price": 0.0,
    "type": {
        "type": "student",
        "discount": 0.8,
        "ticketypeid": 7
    },
    "used": null,
    "ticketcode": "4bb6f723-bf4d-44a7-ac54-c42bc2a6ea13",
    "valid": true
}

]
```

## Error Responses

**Condition** : Jos lippukoodia tai lipun id:tä ei löydy tai se on kirjoitettu väärin

**Code** : `400 SEE BAD REQUEST`

**Content example**

```json
{
    "timestamp": 1589036277388,
    "status": 400,
    "error": "Bad Request",
    "message": "400 BAD_REQUEST",
    "path": "/api/tickets/4bb6f723-bf4d-44a7-ac54-c42bc2a613"
}
```




