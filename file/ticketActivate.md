# Lipun peruutus tai takaisin käyttöönotto

Jos asiakas esimerkiksi tahtoo peruuttaa lipun oston, lippu deaktivoidaan, jolloin sitä ei voi käyttää. 
Samalla kasvatetaan tapahtuman ticketinventorya, eli lippu ikään kuin vapautuu myyntiin.
Kyseisen lipun id lähetetään parametrina ja haluttu valid-arvo lähetetään bodyssa (isValid: true tai false).

**URL** : `/api/tickets/{id}`

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Ticketin id lähetetään parametrinä.

```json
      {
      "isValid" : "[boolean]"
      }
```

**Data example** All fields must be sent.

```json
      {
       "isValid" : false
      }
``` 

## Success Response

**Condition** : Jos kaikki kentät on annettu ja ticketid on olemassa.

**Code** : `200 OK`

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
        "valid": false
    }

]
```

## Error Responses

**Condition** : Jos ticketidtä ei löydy tai jos annettavaa booleania ei ole annettu

**Code** : `400 SEE BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2020-03-13T16:45:32.483+0000",
    "status": 400,
    "error": "Bad Request",
    "trace": "",
    "path": "/api/tickets/2"
}
```




