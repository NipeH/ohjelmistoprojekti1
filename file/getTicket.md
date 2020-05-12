# Yksittäisen lipun tietojen haku

Yksittäisen lipun tietojen haku joko id:llä tai lippukoodilla


**URL** : `/api/tickets/{id}` tai `/api/tickets/{ticketcode}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None


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


**Condition** : Jos käyttäjä ei ole kirjautunut

**Code** : `401 UNUTHORIZED`

**Content example**

```json
{
    "timestamp": 1589217998881,
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/api/tickets/613b6840-3b2f-4262-968e-8bdfc5d1d4b2"
}
```







