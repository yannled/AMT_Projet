# Enable JDBC in Payara Server

### Librairie à télécharger

* aller sur https://dev.mysql.com/downloads/connector/j/
* choisi "Platform Independant" ensuite "Go to Download Page >"
![alt](img/1.png)
* choisi l'archive que tu veux et télécharge là
![alt](img/2.png)
* dans cet archive prendre mysql-connector-java-x.x.x.jar
* l'extraire dans "payaraX\javadb\lib\"

### Partie serveur

* lancer le serveur payara
* aller sur la page admin par défaut http://localhost:4848/
* puis onglet ressource > JDBC > JDBC Connection Pool
![alt](img/3.png)
* cliquer sur new
![alt](img/4.png)
* puis next et finish
* il apparait
![alt](img/5.png)
* sélectionner "NomDuPool"
* changer le " Datasource Classname:" 
  * "com.mysql.jdbc.jdbc2.optional.MysqlDataSource" en "com.mysql.cj.jdbc.MysqlDataSource"
![alt](img/6.png)
* sauver
* dans "Additional Properties", ajouter les différentes propriétés :
  * serverTimezone n'est pas nécessairement obligatoire

![alt](img/7.png)
* sauver à nouveau
* retourner sur "General"
* faire un test de ping
* si ceci apparait 
![alt](img/8.png)

* c'est bon? sinon recommencer 

* aller dans JDBC Ressource
![alt](img/9.png)
* cliquer sur new
![alt](img/10.png)
  * le Pool Name est celui précédemment créé
  * le JNDI Name doit être le même que celui se trouvant dans "persistence.xml" du projet
![alt](img/11.png) ![alt](img/12.png)

* rerun payara et normalement c'est bon !!!