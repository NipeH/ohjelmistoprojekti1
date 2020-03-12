Haetaan kaikki tilaustapahtumat eli tilaukset eli orders

URL : /api/orders/

Method : GET

Auth required : NO (not yet, later, YES)

Permissions required : None (to be changed)

Success Response
Code : 200 OK

Content examples:

[
    {
        "orderid": 15,
        "total": 30.5,
        "timestamp": "2020-03-12T12:20:19.298+02:00"
    },
    {
        "orderid": 26,
        "total": 20.0,
        "timestamp": "2020-03-12T12:20:19.308+02:00"
    }
]