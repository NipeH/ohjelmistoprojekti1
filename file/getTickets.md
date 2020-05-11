# Hakee kaikki liput


**URL** : `/api/tickets

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

**Data constraints**


## Success Response


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
        "ticketcode": "7b5accbb-3396-4e01-9cc1-07c2ab375327",
        "valid": true
    },
    {
        "ticketid": 9,
        "event": {
            "eventid": 1,
            "name": "Syksyn sävel",
            "description": "Suomen luonnon ja vuodenaikojen innoittama konserttiesitys. Soittimina tusina sadeputkea ja märkä rätti",
            "venue": "Nipen takapiha. Kelivaraus. Poudan yllättäessä siirrymme roskakatokseen",
            "price": 50.0,
            "startTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
            "endTime": "2020-10-16T18:00Z[Etc/UTC]",
            "ticketInventory": 2
        },
        "price": 0.0,
        "type": {
            "type": "student",
            "discount": 0.8,
            "ticketypeid": 7
        },
        "used": null,
        "ticketcode": null,
        "valid": true
    }
]
```








