# Lipun voimassaolon tarkistus lippukoodin perusteella 


**URL** : `/api/tickets/read/{ticketCode}`

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Lipun id lähetetään URL:issa.

## Success Response

**Condition** : Jos lippukoodi on olemassa.

**Code** : `200 OK`

**Content example**
Jos lippu on voimassa tulee vastauksena:
OK!
Jos lippu on peruttu palauttaa vastauksena:
Lippu on peruttu
Jos lippu on käytetty palauttaa vastauksena:
Lippu on jo käytetty


## Error Responses

**Condition** : Jos lippukoodi on annettu väärin

**Code** : `500 INTERNAL SERVER ERROR`

**Content example**

```json
{
    "timestamp": 1589046062277,
    "status": 500,
    "error": "Internal Server Error",
    "message": "Index: 0, Size: 0",
    "path": "/api/tickets/read/9423e0a3-e681-4744-bb99-403b913c764"
}
```

**Condition** : Jos lippukoodia ei ole annettu 

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": 1589046189273,
    "status": 400,
    "error": "Bad Request",
    "message": "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; nested exception is java.lang.NumberFormatException: For input string: \"read\"",
    "path": "/api/tickets/read/"
}
```




