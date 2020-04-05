# Haetaan tapahtuman myyntitiedot

Haetaan tiedot kuinka monta kutakin lipputyyppiä on myyty ja millä sumalla sekä yhteenveto.

**URL** : `/api/events/{eventid}/raport`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Tapahtuman id lähetetään URL:issa.


## Success Response

**Condition** : Jos kaikki tapahtuman id:llä löytyy tapahtuma.

**Code** : `200 OK`

**Content example**

```json
[
    {
        "id": 54,
        "tickettype": "Student",
        "pcs": 6,
        "total": 1280.0
    },
    {
        "id": 52,
        "tickettype": "Adults",
        "pcs": 5,
        "total": 1000.0
    },
    {
        "id": 53,
        "tickettype": "Children",
        "pcs": 6,
        "total": 500.0
    }
]
```

## Error Responses

**Condition** : Jos tapahtumaa ei löydy

**Code** : `400 SEE BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2020-03-13T16:45:32.483+0000",
    "status": 404,
    "error": "Not found",
    "message": "Entity not found",
    "trace": "",
    "path": "/api/events/2/raport"
}
```




