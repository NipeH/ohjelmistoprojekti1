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
        "tickettype": "Student",
        "pcs": 2,
        "total": 16.0
    },
    {
        "tickettype": "Adults",
        "pcs": 1,
        "total": 10.0
    },
    {
        "tickettype": "Children",
        "pcs": 1,
        "total": 5.0
    },
    {
        "tickettype": "Total",
        "pcs": 4,
        "total": 31.0
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




