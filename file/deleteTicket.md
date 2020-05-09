# Lipun poistaminen

Poistetaan lippu lipun id perusteella perusteella

**URL** : `/api/tickets/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Lipun id lähetetään URL:issa.

## Success Response

**Condition** :Jos lipun id on olemassa.

**Code** : `204 NO CONTENT`

**Content example**

## Error Responses

**Condition** : Jos lipun id:tä ei ole 

**Code** : `400 SEE BAD REQUEST`

**Content example**

```json
{
    "timestamp": 1589045297248,
    "status": 404,
    "error": "Not Found",
    "message": "Entity Not Found",
    "path": "/api/tickets/9"
}
```




