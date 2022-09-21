# javaTranslate
Ob buildanju se zaženeta 2 instanci, ena uporablja bazo druga external api.

/hello in /hello-rest privzeto vrneta hello world ( en ), ob uporabi parametra pa vrneta pozdrav glede na parameter 
Uporaba -> /hello?q=sl

DB instanca vrne podatke iz baze, za jezike ki so vnaprej vneseni, ter nov jezik ki ga lahko doda zgolj admin.

Endpoint /secure/hello zahteva prijavo za userja 

Username : user
Password : password

Endpoint /ustvarJezik je dostopen zgolj za admina na instanci, kjer se uporablja DB

Username : admin
Password : password
