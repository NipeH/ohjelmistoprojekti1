# Lisätään käyttäjä

Haetaan tiedot kuinka monta kutakin lipputyyppiä on myyty ja millä sumalla sekä yhteenveto.

**URL** : `/api/appusers/`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : YES: admin authorization

**Data and constraints**
```json
{
"firstname": "string",
"lastname": "string",
"phone": "string",
"email": "string",
"username": "string",
"password": "string",
"usertype": "user"
}
```
Username needs to be unique and not null, password can't be null.

## Success Response

**Condition** : Jos käyttäjän lisäys onnistuu.

**Code** : `201 CREATED`

**Content example**

```json
{
    "userid": 15,
    "firstname": "kissaa",
    "lastname": "string",
    "phonenumber": "string",
    "email": "string",
    "password": "$2a$10$K0XomUTLgaW1.oEBeuEjH.sb/fhSz/PL2unVsp1Qx0UKB090Gt47G"
}

```

## Error Responses

**Condition** : Jos käyttäjätunnus on jo olemassa

**Code** : `400 SEE BAD REQUEST`

**Content example**

```json
{
    "timestamp": 1588876271057,
    "status": 400,
    "error": "Bad Request",
    "message": "Käyttäjä on jo olemassa",
    "trace": "",
    "path": "/api/appusers/"
}
```

**Condition** : Jos user-oikeudet omaava yrittää lisätä käyttäjää

**Code** : `403 FORBIDDEN`

**Content example**

```json

{
    "timestamp": 1588876483698,
    "status": 403,
    "error": "Forbidden",
    "message": "Forbidden",
    "path": "/api/appusers/"
}
```

**Condition** : Jos ei olla kirjauduttu sisään

**Code** : `401 UNAUTHORIZED`

**Content example**

```json

{
    "timestamp": 1588876483698,
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/api/appusers/"
}
```
