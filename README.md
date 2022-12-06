# angular-oauth2-springboot-rest

based on https://www.baeldung.com/rest-api-spring-oauth2-angular

## Setup keycloak server locally 

### prerequisites
1.  java 11 or new

### Install Latest version of keycloak
1. Download latest http://www.keycloak.org/downloads.html
2. unzip keycloak-20.0.1.zip
3. cd keycloak-20.0.1/bin
4. start the keycloak server 
   ```
    ./kc.sh start-dev
    or
    ./kc.sh start-dev --http-port=8083
    or
    ./kc.sh start-dev --http-port=8083 --log-level=DEBUG
    ```

* Old versions: https://github.com/keycloak/keycloak/releases/download/19.0.1/keycloak-19.0.1.zip

## Accessing keycloak server
1. setup admin user by accessing http://localhost:8083
2. Fill in the admin user password and confirmation password

## create keycloak realm
1. login as admin http://localhost:8083/admin/
2. Hover the mouse over the dropdown in the top-left corner where it says master, then click on Create realm
3. Fill in the form with the following values:
        Realm name: demo1
4. Click Create

## create sample customer in keycloak
1. Open the Keycloak Admin Console
2. Click Users (left-hand menu) and Click Create new user
3. Fill in the form with the following values:
   ```
        Username: test_user1
        Email: test_user1@gmail.com
        First Name: Your first name
        Last Name: Your last name
    ```
4. Click Create
5. Click Credentials tab
6. Fill in the Set password form with a password
7. Click "off" next to Temporary to prevent having to update password on first login
8. you can login with newly created user in http://localhost:8083/realms/demo1/account/#/

## create keycloak client
1. Open the Keycloak Admin Console
2. Click 'Clients' and Click 'Create client'
3. Fill in the form with the following values:
   ```
        Client type: OpenID Connect
        Client ID: angular-oauth2-springboot-rest-client_id
        Click 'Next'
   ```
4. Make sure 'Standard flow' is enabled and click 'Save'
5. After the client is created you need to update the following values for the client
   ```
    Valid redirect URIs: http://localhost:8089/
    Valid post logout redirect URIs: http://localhost:8089/
    Web origins: http://localhost:8089
    ```
6. click save

## create keycloak custom client scope
1. Open the Keycloak Admin Console
2. Click Client scopes (left-hand menu) and Click Create Client scope
3. Fill in the form with the following values:
    ```
        Name: read
        Type : optional
        Click 'Next'
   ```
## configuring your client to use custom scope
1. Open the Keycloak Admin Console
2. Click Clients scopes (left-hand menu) and select the client you want to apply the scope
3. In the client details page select the tab 'Client scopes'
4. Click 'Add client scope' 
5. Select the scope from the list . for example select the newly created 'read' scope
6. Click add and select 'optional' 


### Start resource server(oauth2-springboot-resource-server)
check the readme file

### Start angular client(oauth2-angular-client)
check the readme file


### manually accessing the endpoints of resource server(oauth2-springboot-resource-server)
1. Generate a access token using grant_type=password
    ```
   curl --location --request POST 'http://localhost:8083/realms/demo1/protocol/openid-connect/token' \
   --header 'Content-Type: application/x-www-form-urlencoded' \
   --data-urlencode 'username=test_user1' \
   --data-urlencode 'password=test_user1' \
   --data-urlencode 'client_id=angular-oauth2-springboot-rest-client_id' \
   --data-urlencode 'grant_type=password' \
   --data-urlencode 'scope=openid read write'
   ```
2. call resource server endpoint using the above generated access token
    ```
   curl --location --request GET 'http://localhost:8081/resource-server/api/foos/1' \
   --header 'Authorization: Bearer <above-access-token>'
   ```


