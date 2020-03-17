### Haetaan kaikki tilaustapahtumaan kuuluvat liput:

URL : /api/orders/{orderid}/tickets

Method : GET

Auth required : NO (to be changed, see: https://github.com/NipeH/ohjelmistoprojekti1#avoimet-ja-autentikointia-vaativat-endpointit)

Permissions required : None (to be changed, see: https://github.com/NipeH/ohjelmistoprojekti1#avoimet-ja-autentikointia-vaativat-endpointit)

Success Response
Code : 200 OK
```json
Content examples:[
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