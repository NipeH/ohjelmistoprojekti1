### Edit event

URL : /api/events/{id}

Method : PUT ja PATCH

Auth required : YES

Data for PUT:

```json
{
"name": "häät",
"description": "Bailataan niin, että tietää bailanneensa",
"price": 500.0,
"venue": "botski",
"startTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
"endTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
"ticketInventory": 50
}
```

Data for PATCH:

```json
{

"description": "Muutetaan vain nämä",
"price": 500.0,

"ticketInventory": 50
}
```


### Success Responses

Code : 200 OK

Content example:
```json
{
"eventid": 27,
"name": "Muutetut tiedot",
"description": "Bailataan niin, että tietää bailanneensa",
"price": 500.0,
"venue": "botski",
"startTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
"endTime": "2020-09-16T16:00:00.078Z[Etc/UTC]",
"ticketInventory": 50
}
```
### Error Response

Condition : If event is not found, or if PUT method has invalid information

Code : 400 BAD REQUEST



