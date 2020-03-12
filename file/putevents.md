### Edit event

URL : /api/events/{id}

Method : PUT ja PATCH

Auth required : YES (in future)

Data:

```json
{
"name": "häät",
"description": "Bailataan niin, että tietää bailanneensa",
"price": 500.0,
"venue": "botski",
"startTime": "2020-09-16T19:00:00",
"endTime": "2020-09-16T21:00:00",
"ticketInventory": 50
}
```
PUT: If there are no new values, old ones will be sent
PATCH: Only the values wanted to be changed

### Success Responses

Code : 200 OK

Content example:
```json
{
"eventid": 27,
"name": "häät",
"description": "Bailataan niin, että tietää bailanneensa",
"price": 500.0,
"venue": "botski",
"startTime": "2020-09-16T19:00:00",
"endTime": "2020-09-16T21:00:00",
"ticketInventory": 50
}
```
### Error Response

Condition : If parameter is missing

Code : 400 BAD REQUEST

