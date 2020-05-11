# Luodaan uusi tilaus

Luodaan uusi tilaus  eli order

**URL** : `/api/orders`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

## Success Response

**Condition** : Uusi tilaustapahtuma on luotu.

**Code** : `201 CREATED`

**Content example**

```json
[
{
    "orderid": 15,
    "total": 0.0,
    "timestamp": "2020-05-10T18:38:43.830Z[Etc/UTC]",
    "tickets": [],
    "user": null,
    "customer": null
}
]
```

## Error Responses

