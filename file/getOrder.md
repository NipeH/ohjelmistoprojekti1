### Haetaan tietty tilaus

**URL** : GET /api/orders/{orderid}

**Method** : GET

**Auth required** : YES

**Permissions required** : None

**Data constrains**

Orderin id vaaditaan
"orderid" :  "[varchar]"

##Success Response

**Condition** : Jos orderId on olemassa

**Code** : 200 OK
```json
{
    "orderid": 15,
    "total": 50.0,
    "timestamp": "2020-05-11T19:16:06.994Z[Etc/UTC]",
    "tickets": [
        {
            "ticketid": 16,
            "event": {
                "eventid": 1,
                "name": "Syksyn sävel",
                "description": "Suomen luonnon ja vuodenaikojen innoittama konserttiesitys. Soittimina tusina sadeputkea ja märkä rätti",
                "venue": "Nipen takapiha. Kelivaraus. Poudan yllättäessä siirrymme roskakatokseen",
                "price": 50.0,
                "startTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
                "endTime": "2020-10-16T18:00Z[Etc/UTC]",
                "ticketInventory": 1
            },
            "price": 50.0,
            "type": {
                "type": "normal",
                "discount": 0.0,
                "ticketypeid": 5
            },
            "used": null,
            "ticketcode": "25f83e1c-9457-4427-ad1b-0ea79aac4d7d",
            "valid": true
        }
    ],
    "user": null,
    "customer": null
}
````

###Error Response

**Condition** : Jos orderid:tä ei ole olemassa

**Code** :  404 Not Found
```json
{
    "timestamp": 1589224737874,
    "status": 404,
    "error": "Not Found",
    "message": "No order with an id of 1 found",
    "path": "/api/orders/1"
}

````




