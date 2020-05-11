### Haetaan kaikki tilaustapahtumaan kuuluvat liput:

**URL** : /api/orders/{orderid}/tickets

**Method** : GET

**Auth required** : YES

**Permissions required** : YES

**Success Response**

**Code** : 200 OK

**Content examples**

### Success Responses

```json
:[
    {
        "ticketid": 28,
        "price": 0.0,
        "valid": false
    },
    {
        "ticketid": 29,
        "price": 0.0,
        "valid": false
    },
    {
        "ticketid": 30,
        "price": 0.0,
        "valid": false
    },
    {
        "ticketid": 31,
        "price": 0.0,
        "valid": false
    },
    {
        "ticketid": 32,
        "price": 0.0,
        "valid": false
    }
]
```
### Error Responses

**Condition** : Haettua orderid:tä ei löydy

**Code** : 404 Not Found



```json
{
    "timestamp": 1589224969410,
    "status": 404,
    "error": "Not Found",
    "message": "No order with an id of 15 found",
    "path": "/api/orders/15/tickets"
}
```
