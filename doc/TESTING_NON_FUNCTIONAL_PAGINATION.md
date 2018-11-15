# Gestion de la pagination

### Problème sans pagination

Sans la pagination, la page devrait télécharger l'intégralité des informations de la base de données et les afficher sur une seule page ce qui rendrait le cahrgement très long, les données à gérer très lourdes et surchargerait visuelement la page.

### Utilité de la pagination

La pagination permet de diminuer les quantités affichées sur une page ce qui en allège le contenu et le rend plus lisible. Suivant l'implémentation, on peut agir sur la requête faite à la base de données pour alléger la réponse en diminuant la quantité de données demandées par la page et par conséquent diminuer le temps de chargement.

### Implémentation de la pagination :

- Pour implémenter la pagination, une classe `Pagination` a été créée permettant de garder le numéro de la page courante ainsi que le nombre d'éléments la composant.

- Le code qui suit a été ajouté dans les Servlets gérant les pages avec de la pagination. Il permet de n'afficher sur la page que `recordPerPage` éléments.

```java
  pagination = new Pagination(1,1);

  //PAGINATION
  int recordPerPage = 10;

  // define number of applications per page
  pagination.setRecordsPerPage(recordPerPage, applications.size());

  // define if a page is choose
  if(request.getParameter("value") != null)
      pagination.setCurrentPage(Integer.parseInt(request.getParameter("value")));

  // define position of first Element and last element
  int firstElement = pagination.getFirstElement();
  int lastElement = pagination.getLastElement(applications.size());

  // define a sublist with element to show
  List<Application> tempList = applications.subList(firstElement,lastElement);

  int noOfRecords = applications.size();
  int noOfPages = (int)Math.ceil(noOfRecords * 1.0 / pagination.getRecordsPerPage());

  request.setAttribute("applications", tempList);
  request.setAttribute("noOfPages", noOfPages);
  request.setAttribute("currentPage", pagination.getCurrentPage());

```

### Résultat de la pagination :

- On demande au serveur uniquement la partie des applications ou des users que l'on veut afficher et l'on voyage dans les différentes pages pour avoir accès au reste des informations.

