# Poistetaan käyttäjätili

Poistetaan ohjelmaan käyttöoikeudet antava käyttäjätili kokonaan tietokannasta. 

**URL** : `/api/users/{userid}`

**Method** : `DELETE`

**Auth required** : `YES`

**Permissions required** : `Admin`

**Data constraints**

`Poistamiseen tarvitaan vain järjestelmänvalvojan tunnukset ja poistettavan käyttäjätilin id-numero.`

**Data example** 

`localhost:8080/api/users/16/ `


## Success Response

**Condition** :

`Jos annetulla id-numerolla löytyy käyttäjätili ja toiminnon kutsujalta löytyy tarvittava käyttöoikeus`

**Code** : `204 NO CONTENT`


## Error Responses

**Condition** : `Jos userid:tä ei löydy`

**Code** : `404 NOT FOUND`

**Content example**

```json
{
    "timestamp": 1588882998976,
    "status": 404,
    "error": "Not Found",
    "message": "404 NOT_FOUND",
    "trace": "org.springframework.web.server.ResponseStatusException: 404 NOT_FOUND ... ",
    "path": "/api/users/16/"
}
```

**Condition** : `Jos toiminnon suorittajalla ei ole tarvittavaa käyttöoikeutta`

**Code** : `401 UNAUTHORIZED`

**Content example**

```json

{
    "timestamp": 1588883234540,
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/api/users/18/"
}

```





