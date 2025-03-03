# Welcome to the Code With Issues Repository! 🚨🐇

Oh hey there, curious developer! 🕵️‍♂️

Before you dive into this beautiful mess of code, why not check out the open pull request on the current branch? I've integrated CodeRabbit for automated PR reviews, and trust me, watching a bot try to make sense of this code is pure entertainment.

Hop over to the PR, let CodeRabbit do its magic, and maybe, just maybe, you'll save yourself from the horrors within this codebase. 🐰💻

Happy coding (and good luck)! 🍀



## springsecurity

The Spring security project is used to Authenticate the user by reading JWT token from request header.

The App flow is user will singup with username,email,password the app will ask the user to login after signup.
Once the user enter username and password and login using /signin restapi. The rest api will generate JWT token and send back to client.
The client need to send JWT token for each request .By implementing the abstract OncePerRequestFilter the JWTFilterPerRequest will validate the token entered by the client.

### Steps to test API:

1)Create a JSON with SignUP request and submit to /signup api.
2)Create the JSON for SignIn request with username and password.The JWT rest API will return JWT token.
3)Send the token in authorization request headeralong along with request for api call
