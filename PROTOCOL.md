# Inter Server Protocol
=========================

## Replicate File

**POST \<host>/endpoint.php**

Params enctype="multipart/form-data"

| Param         | Type          | Option    |
| ------------- |:-------------:| ---------:|
| file_name     | text          | optional  |
| user          | text          | optional  |
| key           | text          | mandatory |
| file          | multipart     | mandatory |

user use to identify where this file come from.
key is the secret predefined.

Response
 
 - HTTP 200 OK
 - HTTP 4XX
 - HTTP 5XX 

