The ldapauthenticationjwttoken is used to generate a JWT token by authenticating user on ldap server.
I have created simple restful mictoservice that will accept username and password and generate a JWT token.

Sample json to generate token using generatetoken rest api

{
"username":"john",
"password":"secret"
}
