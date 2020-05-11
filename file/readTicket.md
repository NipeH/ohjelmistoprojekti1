
# Yksittäisen lipun lukeminen käytetyksi


**URL** : `/api/tickets/read/{code}

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : None

**Data constraints**


## Success Response


**Code** : `200 OK`

**Content example**

```json
    {
 OK!
}
```

**Code** : `200 OK`

**Jos lippu on jo käytetty:**

**Content example**

```json
    {
 Lippu on jo käytetty
}
```


## Error Responses

**Condition** : Jos orderidtä, tickettypeidtä ei löydy tai jos kaikkia kenttiä ei ole annettu

**Code** : `500 Internal server error`

**Content example**

```json
{
    "timestamp": 1589219447871,
    "status": 500,
    "error": "Internal Server Error",
    "message": "Index: 0, Size: 0",
    "path": "/api/tickets/read/8b0ad00f-3dc0-47a5-9871-07c7471671e2"
}
```





