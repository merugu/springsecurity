# springsecurity

The Spring security project is used to Authenticate the user by reading JWT token from request header.

The App flow is user will singup with username,email,password the app will ask the user to login after signup.
Once the user enter username and password and login using /signin restapi. The rest api will generate JWT token and send back to client.
The client need to send JWT token for each request .By implementing the abstract OncePerRequestFilter the JWTFilterPerRequest will validate the token entered by the client.

Steps to test API:

1)Create a JSON with SignUP request and submit to /signup api.
2)Create the JSON for SignIn request with username and password.The JWT rest API will return JWT token.
3)Send the token in authorization request headeralong along with request for api call
