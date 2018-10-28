The ldapauthenticationjwttoken is used to generate a JWT token by authenticating user on ldap server.
I have created simple restful microservice that will accept username and password and generate a JWT token.

To test this service :

1)Download the app
2)Use "spring-boot:run" maven command to start the service 
3)Curl : curl -d '{"username":"john", "password":"secret"}' -H "Content-Type: application/json" -X POST http://localhost:8085/api/auth/generatetoken

Or If you are using API Development tool(like Postman) use the below details 

HTTP Method:Post
URL: http://localhost:8085/api/auth/generatetoken
Body: {"username":"john","password":"secret"}
Content-Type: application/json

