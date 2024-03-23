[![Donate](https://img.shields.io/badge/Donate-PayPal-orange.svg)](https://www.paypal.com/donate/?cmd=_donations&business=8UK2BZP2K8NSS)

# jwe-token-decrypter

Simple command line util for decrypting JWE (JSON Web Encryption) tokens. Takes as input the token (compact serialization) and its encrypton key and prints both the token headers and payload.

## Usage:

    java -jar jwe-token-decrypter.jar

## Sample input

    Token: eyJhbGciOiJQQkVTMi1IUzI1NitBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiZXhwIjoiOTk5NDk2MTM4MjIwMDAiLCJhdWQiOiJteUF1ZGllbmNlIiwiaXNzIjoibXlJc3N1ZXIiLCJwMmMiOjgxOTIsInAycyI6IlVZSF9Qd2h5QmNORVd4QTQifQ.LF4nJD0m1uuv5GOdSAERphXSv_O6dhGEKB_QJvTXj-DuWE-bKCs4OQ.RdNrWnmURv5QDN-DmlkmzA.qypSoDf2jBLuNGSDCuwfwoQegZzoIFkjPpMhulkaBFpULhdmcecrQBiIvxtAWioY.xa7qJC6FAGCcZk8YpI1zyw
    Key: mySecret
    
## Sample output

    {
    	"alg": "PBES2-HS256+A128KW",
    	"enc": "A128CBC-HS256",
    	"exp": "99949613822000",
    	"aud": "myAudience",
    	"iss": "myIssuer",
    	"p2c": 8192,
    	"p2s": "UYH_PwhyBcNEWxA4"
    }
    {
    	"myPayloadField":"myPayloadValue"
    }

<sub>Copyright (c) 2019 Donato Rimenti</sub>
