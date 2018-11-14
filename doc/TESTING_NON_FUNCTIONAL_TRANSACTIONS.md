# Gestion des transactions



### Implémentation de la gestion des transactions :

- Pour gérer les transaction nous avons utilisé l'annotation suivante :

  `@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)`

- Nous avons choisi de throw les exceptions de nos DAO afin de les "Catch" dans nos servlet pour rediriger l'utilisateur sur une page d'erreur.



### Erreur générée : 

Dans la méthode `updateEmail` du `UserDAO.java` j'ai manuellement throw une excecption:

```java
@Override
public void updateEmail(Long id, String email) throws Exception {
    try (Connection connection = dataSource.getConnection()) {
        PreparedStatement ps = connection.prepareStatement(updateUserEmail);

        // insert data into statement.
        ps.setString(1, email);
        ps.setLong(2, id);

        ps.execute();
    } catch (SQLException e) {
        throw new Exception(e);
    }
    throw new Exception("EXPLOSSSSSIONNNNN !!!!");
}
```

Cette méthode est utilisée dans le `ProfileServlet.java`

```java
case "MODIFY":
    try {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Part filePart = request.getPart("avatar");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();

        if (fileName != null && !fileName.isEmpty()) {

            userDAO.updateAvatar(currentUserId, fileContent);
        }
        userDAO.updateName(currentUserId, firstName, lastName);

        // if new inserted email already exists we prevent a runtime error at database insert and inform the user to change it.
        if (!userDAO.isExist(email)) {
            userDAO.updateEmail(currentUserId, email);
        } else {

            User user = userDAO.findById(currentUserId);
            user.setEmail(email);
            request.setAttribute("currentUser", user);

            String emailError = "This email already exists";
            request.setAttribute("emailError", true);
            request.setAttribute("emailErrorText", emailError);
            request.setAttribute("modify", true);
            request.getRequestDispatcher(PROFILE).forward(request, response);
        }

        //Mettre à jour la session en fonction du changement de profil
        currentUser.setName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        session.setAttribute("user", currentUser);
    } catch (Exception e){
        request.setAttribute("error","There was a problem when the user modify his profile");
        request.setAttribute("errorContent",e.getMessage());
        request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
    }
    break;
```

**Deroulement du problème** :

Lors que l'on est sur la page `Profile` et qu'on modifie son profile, si on modifie l'email de l'utilisateur et que l'on charge un nouvel avatar. L'erreur précédement ajoutée dans la méthode `updateEmail` va throw une exception et nous seront redirigé sur la page d'erreur.

Le problème est l'email a tout de même modifié