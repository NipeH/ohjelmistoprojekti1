# Luodaan uusia lippuja ostokseen

Luodaan uusia lippuja tietyllä lipputyypillä haluttuun tapahtumaan. Oletuksena, että lippuja on vielä saatavilla.

**URL** : `/api/events/{eventid}/tickets`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Orderin id, TicketTypen id ja lippujen lukumäärä vaaditaan.

```json
      {
       "pcs" : "[VARCHAR]",
      "orderid" : "[VARCHAR]",
      "tickettypeid" : "[VARCHAR]"
      }
```

**Data example** All fields must be sent.

```json
      {
       "pcs" : "3",
      "orderid" : "15",
      "tickettypeid" : "3"
      }
``` 

## Success Response

**Condition** : Jos kaikki kentät on annettu ja orderid sekä tickettypeid on olemassa.

**Code** : `201 CREATED`

**Content example**

```json
[
    {
        "ticketid": 34,
        "price": 200.0,
        "type": {
            "type": "normal",
            "discount": 0.0,
            "ticketypeid": 3
        },
        "orders": {
            "orderid": 15,
            "total": 600,
            "timestamp": "2020-03-13T18:21:16.959+02:00"
        },
        "valid": true
    },
    {
        "ticketid": 35,
        "price": 200.0,
        "type": {
            "type": "normal",
            "discount": 0.0,
            "ticketypeid": 3
        },
        "orders": {
            "orderid": 15,
            "total": 600,
            "timestamp": "2020-03-13T18:21:16.959+02:00"
        },
        "valid": true
    },
    {
        "ticketid": 36,
        "price": 200.0,
        "type": {
            "type": "normal",
            "discount": 0.0,
            "ticketypeid": 3
        },
        "orders": {
            "orderid": 15,
            "total": 600,
            "timestamp": "2020-03-13T18:21:16.959+02:00"
        },
        "valid": true
    }
]
```

## Error Responses

**Condition** : Jos orderidtä, tickettypeidtä ei löydy tai jos kaikkia kenttiä ei ole annettu

**Code** : `400 SEE BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2020-03-13T16:45:32.483+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Tarkista pakolliset kentät, orderid, pcs ja tyckettypeid",
    "trace": "",
    "path": "/api/events/2/tickets"
}
```





### Luodaan uusi lippu tapahtumaan edellyttäen, että lippuja on vielä saatavilla

URL : /api/events/{eventid}/tickets

Method : POST

Auth required : NO (to be changed, see: https://github.com/NipeH/ohjelmistoprojekti1#avoimet-ja-autentikointia-vaativat-endpointit)

Permissions required : None (to be changed, see: https://github.com/NipeH/ohjelmistoprojekti1#avoimet-ja-autentikointia-vaativat-endpointit)



Content examples:

POST http://localhost:8080/api/events/2/tickets

```json
      {
       "pcs" : "3",
      "orderid" : "15",
      "tickettypeid" : "3"
      }
```      
Success Response
Code : 201 CREATED

Response: 
```json
[
    {
        "ticketid": 34,
        "price": 200.0,
        "type": {
            "type": "normal",
            "discount": 0.0,
            "ticketypeid": 3
        },
        "orders": {
            "orderid": 15,
            "total": 600,
            "timestamp": "2020-03-13T18:21:16.959+02:00"
        },
        "valid": true
    },
    {
        "ticketid": 35,
        "price": 200.0,
        "type": {
            "type": "normal",
            "discount": 0.0,
            "ticketypeid": 3
        },
        "orders": {
            "orderid": 15,
            "total": 600,
            "timestamp": "2020-03-13T18:21:16.959+02:00"
        },
        "valid": true
    },
    {
        "ticketid": 36,
        "price": 200.0,
        "type": {
            "type": "normal",
            "discount": 0.0,
            "ticketypeid": 3
        },
        "orders": {
            "orderid": 15,
            "total": 600,
            "timestamp": "2020-03-13T18:21:16.959+02:00"
        },
        "valid": true
    }
]
```
