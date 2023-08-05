# LAP_Build

WebSecurityConfig:<br> 
Can stay the same for the most part except any changes in the endpoint names.<br>
<br>
Controller:<br> 
The controller folder contains the code that handles the requests to the api endpoints.
<br>
Important to note that when interacting with many-to-many relationships you only need to save/delete
the in-between table. On the next select you will have it included in both outer tables. 
As long as the in-between instance already exists you can also edit them on one of the outer tables
and save them by saving the outer table.
<br>
You can also embed a value in the url for the endpoint. Usefull if only one value is needed.
Should be mainly used for get requests.
<br>
For changes, you can use the existing controllers to create ones for your test. Except for 
WebUserController. It can stay the same. You just need to change the necessary user data to
whatever is required.
<br>
You should also map received data in case of a post request to a DTO class. The same should
be the case for returned data. It's best to create a service class for that.<br>
<br>
Dao:<br>
Every dao class is marked as an entity which will get translated to database as a table.
For relationships, for many-to-one both unidirectional and bidirectional are viable but in this case only
bidirectional is possible to avoid unnecessary additional work. The same goes for many-to-many except if 
the in-between table contains additional data then you have to create a new dao class for it and create an
embedded key class for the ids. A good example is BasketProduct and BasketProductKey. If a table has a required
relationship you can mark the attribute as not null for the constraint to be respected.
<br>
Each dao requires a repository for its database interactions.

For cross-site requests to you need to generate a p12 certificate for the webservice tomcat server <a href="https://www.tutorialspoint.com/spring_boot/spring_boot_enabling_https.htm"> here </a> and default web-certificates
for the react frontend apache server <a href="https://medium.com/@praveenmobdev/localhost-as-https-with-reactjs-app-on-windows-a1270d7fbd1f"> here </a>



