
### Poista tapahtuma

**URL** : DELETE /api/events/{id}

**Method** : DELETE

**Auth required** : YES

**Permissions required** : None





### Success Response

**Condition** : Poisto onnistui

**Code** : 204 NO CONTENT


### Error Responses

**Condition** : Jos eventid:tä ei löydy

**Code** : 400 SEE BAD REQUEST

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


