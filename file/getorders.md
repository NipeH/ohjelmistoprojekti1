### Haetaan kaikki tilaustapahtumat eli orders



**URL** : /api/orders

**Method** : GET

## Success Response
**Code** : 200 OK

## Content examples:

```json
[
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
    },
    {
        "orderid": 17,
        "total": 800.0,
        "timestamp": "2020-05-11T19:18:05.684Z[Etc/UTC]",
        "tickets": [
            {
                "ticketid": 18,
                "event": {
                    "eventid": 2,
                    "name": "Ruisrock",
                    "description": "Esiintyjinä mm. Major Lazer",
                    "venue": "Turun ruissalo",
                    "price": 200.0,
                    "startTime": "2020-07-04T16:00:00.078Z[Etc/UTC]",
                    "endTime": "2020-07-05T20:59Z[Etc/UTC]",
                    "ticketInventory": 195
                },
                "price": 160.0,
                "type": {
                    "type": "student",
                    "discount": 0.8,
                    "ticketypeid": 7
                },
                "used": null,
                "ticketcode": "bde91b5c-1317-4045-9008-6d03d99183c8",
                "valid": true
            },
            {
                "ticketid": 19,
                "event": {
                    "eventid": 2,
                    "name": "Ruisrock",
                    "description": "Esiintyjinä mm. Major Lazer",
                    "venue": "Turun ruissalo",
                    "price": 200.0,
                    "startTime": "2020-07-04T16:00:00.078Z[Etc/UTC]",
                    "endTime": "2020-07-05T20:59Z[Etc/UTC]",
                    "ticketInventory": 195
                },
                "price": 160.0,
                "type": {
                    "type": "student",
                    "discount": 0.8,
                    "ticketypeid": 7
                },
                "used": null,
                "ticketcode": "e496e845-9ffe-4b40-97a7-268ec5e83ca4",
                "valid": true
            },
            {
                "ticketid": 20,
                "event": {
                    "eventid": 2,
                    "name": "Ruisrock",
                    "description": "Esiintyjinä mm. Major Lazer",
                    "venue": "Turun ruissalo",
                    "price": 200.0,
                    "startTime": "2020-07-04T16:00:00.078Z[Etc/UTC]",
                    "endTime": "2020-07-05T20:59Z[Etc/UTC]",
                    "ticketInventory": 195
                },
                "price": 160.0,
                "type": {
                    "type": "student",
                    "discount": 0.8,
                    "ticketypeid": 7
                },
                "used": null,
                "ticketcode": "fece9b0f-ffb5-4135-8694-20fd1dc46665",
                "valid": true
            },
            {
                "ticketid": 21,
                "event": {
                    "eventid": 2,
                    "name": "Ruisrock",
                    "description": "Esiintyjinä mm. Major Lazer",
                    "venue": "Turun ruissalo",
                    "price": 200.0,
                    "startTime": "2020-07-04T16:00:00.078Z[Etc/UTC]",
                    "endTime": "2020-07-05T20:59Z[Etc/UTC]",
                    "ticketInventory": 195
                },
                "price": 160.0,
                "type": {
                    "type": "student",
                    "discount": 0.8,
                    "ticketypeid": 7
                },
                "used": null,
                "ticketcode": "d187c7b7-6e6c-4e58-ad47-9f2c45bbe00b",
                "valid": true
            },
            {
                "ticketid": 22,
                "event": {
                    "eventid": 2,
                    "name": "Ruisrock",
                    "description": "Esiintyjinä mm. Major Lazer",
                    "venue": "Turun ruissalo",
                    "price": 200.0,
                    "startTime": "2020-07-04T16:00:00.078Z[Etc/UTC]",
                    "endTime": "2020-07-05T20:59Z[Etc/UTC]",
                    "ticketInventory": 195
                },
                "price": 160.0,
                "type": {
                    "type": "student",
                    "discount": 0.8,
                    "ticketypeid": 7
                },
                "used": null,
                "ticketcode": "5e92c651-cd44-4268-ba04-73fff040a997",
                "valid": true
            }
        ],
        "user": null,
        "customer": null
    }
]
```
### Error Responses

Haetaan tilaukset ilman autentisointia

**Code **: 401 Unauthorized

**Content example**

```json
{
    "timestamp": 1589225148162,
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/api/orders"
}


