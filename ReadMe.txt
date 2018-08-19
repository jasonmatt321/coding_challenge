Get All Phone Numbers:

A get request that retrieves all telephone objects in response.
Example request:
method:     GET
url:        /getPhoneNumbers

Example response
Success:

statusCode:    200
responseBody:  {
                   "data" : [
                       {
                           "name":"George Smith",
                           "telephones": [
                           "telephone":"07512345632",
                           "isActivated": false
                           ]
                       }
                   ]
               }

 Failure:

 statusCode:     404
 responseBody:   {
                     "errors" : [
                         {
                             "code" : "NOT_FOUND",
                             "description" : "No telephone numbers present"
                         }
                     ]
                 }


Get Phone Number by Name:

A post that requires a string name as parameter and returns telephone object for the given name.
Example request:
method: POST
url:    /getPhoneNumberByName
parameter: customerName="Mark Jones"

Example response
Success:

statusCode:    200
responseBody:  {
                   "data" : [
                       {
                           "name":"George Smith",
                           "telephones": [
                           "telephone":"07512345632",
                           "isActivated": false
                           ]
                       }
                   ]
               }

 Failure:

 statusCode:     404
 responseBody:   {
                     "errors" : [
                         {
                             "code" : "NOT_FOUND",
                             "description" : "No telephone numbers present for this name"
                         }
                     ]
                 }


Activate Phone Number
A put request that requires a string phone number as parameter and returns success message if it is activated or if it has
been activated already. It will return an error if the number doesn't exist.
Example request:
method: POST
url:    /getPhoneNumberByName
parameter: customerName="Mark Jones"

Example response
Success:

statusCode:    200
responseBody:  {
                   "data" : [
                       {
                           "number already activated"
                       }
                   ]
               }

 Failure:

 statusCode:     404
 responseBody:   {
                     "errors" : [
                         {
                             "code" : "NOT_FOUND",
                             "description" : "Number doesn't exist"
                         }
                     ]
                 }

