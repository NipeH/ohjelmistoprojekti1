
### Poista tapahtuma

**URL** : `DELETE /api/events/{id}`

**Method** : `DELETE`

**Auth required** : `YES`

**Permissions required** : None



### Success Response

**Condition** : Poisto onnistui

**Code** : 204 NO CONTENT


### Error Responses

**Condition** : Jos eventid:tä ei löydy

**Code** : `404 NOT FOUND`

**Content example**

```json
{
    "timestamp": 1589221896914,
    "status": 404,
    "error": "Not Found",
    "message": "Entity Not Found",
    "path": "/api/events/22"
}
```

**Condition** : `Jos toiminnon suorittaja ei ole kirjautunut sisään`

**Code** : `401 UNAUTHORIZED`

**Content example**

```json

{
    "timestamp": 1588883234540,
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/api/events/22"
}

```


