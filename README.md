# AMT_Projet
Projet d'AMT en trois parties qui consiste à faire une API de gamification.

https://github.com/SoftEng-HEIGVD/Teaching-HEIGVD-AMT-2018-Project

Notre groupe est constitué de Yann Lederrey, Patrick Neto, Steve Henriquet et Joel Schär.



### Implémentation à choix 1

Un choix d'implémentation que nous avons fait est d'offrir la possibilité à l'utilisateur de personnaliser son profile avec un avatar. Il est donc possible sur la page Profile de choisir une image qui sera sauvée et va apparaître sur cette même page. 
Le seul endroit ou cette image apparait est le profile, mais dans une implémentation future, il sera possible de réutiliser cette image dans différents endroit afin de reconnaitre plus visuellement un utilisateur.
Nous sauvons cette image dans la base de donnée, il n'est donc pas possible de stocker une image trop volumineuse, et celle ci est donc limité à une taille de 160ko, ce qui est néanmoins suffisant pour une image de profile.

### Implémentation à choix 2

Nous avons imaginer que plusieurs développeurs pourraient être en charge d'une application. Nous avons donc implémenté la possibilité pour un développeur d'ajouter des collaborateurs à son projet. Cet ajout se fait sous la forme d'un choix dans une liste déroulante sur la page de détail d'une application.
Dès lors qu'un développeur est membre de la team de développement d'une application, il a les même droit que le propriétaire original.

### Déploiement de l'application

Nous avons encapsulé l'application au complet dans un docker. Il est donc possible de la démarrer en faisant un `docker-compose up --build` depuis le répertoire `docker `qui se trouve à la racine du répertoire.

Une fois l'application déployée, elle est alors atteignable à l'adresse : [http://localhost:8080/amt_project]()

Il existe différents utilisateur près configuré:

| email                      | password | rôle          |
| -------------------------- | -------- | ------------- |
| joel.schar@heig-vd.ch      | 1234     | admin         |
| yann.lederrey@heig-vd.ch   | 1234     | admin         |
| patrick.neto@heig-vd.ch    | 1234     | admin         |
| steve.henriquet@heig-vd.ch | 1234     | user          |
| user.inactif@heig-vd.ch    | 1234     | user(inactif) |

### Tests

Les documents expliquant le lancement des tests se trouvent dans le répertoire doc qui se trouve à la racine du répertoire.