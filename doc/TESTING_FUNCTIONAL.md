# Test fonctionnels

Nous avons crée des tests permettant de vérifier le bon fonctionement de squelette principal de notre application, c'est à dire : 

- Login
- Création de compte
- Gestion de ses applications

### Tests effectués :

- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToRegisterWithoutEmailAddress() {
    goTo(baseUrl+"register");
    registerPage.isAt();
    registerPage.typeEmailAddress("");
    registerPage.typeFirstName("Joel");
    registerPage.typeLastName("Schar");
    registerPage.typeFirstPassword("1234");
    registerPage.typeSecondPassword1("1234");
    registerPage.clickRegister();
    registerPage.isAt();
  }
  ```

  - Page testée : "Register"
  - Problématique testée : Email manquant dans le formulaire de création de compte 
  - test principal effectué : page chargée à la fin de la création de compte
    - Si le test est fonctionnel, on est redirigé sur la page "Register".
    - Si la problématique n'est pas gérée, on est redirigé sur "Home".


- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToRegisterWithoutTwoSamePassword() {
    goTo(baseUrl+registerPage.getUrl());
    registerPage.isAt();
    registerPage.typeEmailAddress("jojo@jiji.com");
    registerPage.typeFirstName("Joel");
    registerPage.typeLastName("Schar");
    registerPage.typeFirstPassword("12345");
    registerPage.typeSecondPassword1("1234");
    registerPage.clickRegister();
    registerPage.isAt();
  }
  ```

  - Page testée : "Register"
  - Problématique testée : premier password et second password différents dans le formulaire de création de compte. 
  - test principal effectué : page chargée à la fin de la création de compte
    - Si le test est fonctionnel, on est redirigé sur la page "Register".
    - Si la problématique n'est pas gérée, on est redirigé sur "Home".

- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToRegister() {
    goTo(baseUrl+registerPage.getUrl());
    registerPage.isAt();
    registerPage.typeEmailAddress(TEST_EMAIL_OK);
    registerPage.typeFirstName("Testeur");
    registerPage.typeLastName("test");
    registerPage.typeFirstPassword(TEST_PASSWORD_OK);
    registerPage.typeSecondPassword1(TEST_PASSWORD_OK);
    registerPage.clickRegister();
    loginPage.isAt();
  }
  ```

  - Page testée : "Register"
  - Problématique testée : création d'un compte fonctionnelle.
  - test principal effectué : page chargée à la fin de la création de compte
    - Si le test est fonctionnel, on est redirigé sur la page "Home".
    - Si la problématique n'est pas gérée, on est redirigé sur "Register".

- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("not a valid email");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    loginPage.isAt();
  }
  ```

  - Page testée : "Login"
  - Problématique testée : login avec un email invalide : (sans @)
  - test principal effectué : page chargée à la fin de la création de compte
    - Si le test est fonctionnel, on est redirigé sur la page "Login".
    - Si la problématique n'est pas gérée, on est redirigé sur "Home".

- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToSigninWithoutAnEmail() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    loginPage.isAt();
  }
  ```

  - Page testée : "Login"

  - Problématique testée : login sans adresse email.

  - test principal effectué : page chargée à la fin de la création de compte

    - Si le test est fonctionnel, on est redirigé sur la page "Login".

    - Si la problématique n'est pas gérée, on est redirigé sur "Home".

- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToSigninWithoutAPassword() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("admin@cochonChinois.china");
    loginPage.typePassword("");
    loginPage.clickSignin();
    loginPage.isAt();
  }
  ```

  - Page testée : "Login"

  - Problématique testée : login sans mot de passe.

  - test principal effectué : page chargée à la fin de la création de compte

    - Si le test est fonctionnel, on est redirigé sur la page "Login".

    - Si la problématique n'est pas gérée, on est redirigé sur "Home".

- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToLogIn() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress(TEST_EMAIL_OK);
    loginPage.typePassword(TEST_PASSWORD_OK);
    loginPage.clickSignin();
    homePage.isAt();
  }
  ```

  - Page testée : "Login"
  - Problématique testée : login fonctionnel
  - test principal effectué : page chargée à la fin de la création de compte
    - Si le test est fonctionnel, on est redirigé sur la page "Home".
    - Si la problématique n'est pas gérée, on est redirigé sur "Login".



- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToAddAppAndFindIt() {
    final String appName = "application Test";
    goTo(baseUrl);
    loginPage.typeEmailAddress(TEST_EMAIL_OK);
    loginPage.typePassword(TEST_PASSWORD_OK);
    loginPage.clickSignin();
    goTo(baseUrl+applicationsPage.getUrl());
    applicationsPage.clickAddApp();
    await().atMost(2, TimeUnit.SECONDS).until("#addApp").areDisplayed();
    applicationsPage.typeAppName(appName);
    applicationsPage.typeAppDescription("Application de testing");
    applicationsPage.clickSubmitApp();
    assertThat(applicationsPage.pageSource()).contains(appName);
    applicationsPage.isAt();
  }
  ```

  - Page testée : "Applications"

  - Problématique testée : ajout d'une application et test si l'application existe.

  - test principal effectué : nous testons si nous retrouvons l'application  précédement créée dans la liste d'applications.

    - Si le test est fonctionnel, l'assertion est accéptée.

    - Si la problématique n'est pas gérée, l'assertion va générer une erreur.

- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToModifyAppAndFindIt() {
    final String appName = "application Test Modify";
    goTo(baseUrl);
    loginPage.typeEmailAddress(TEST_EMAIL_OK);
    loginPage.typePassword(TEST_PASSWORD_OK);
    loginPage.clickSignin();
    goTo(baseUrl+applicationsPage.getUrl());
    applicationsPage.clickModifyApp();
    await().atMost(2, TimeUnit.SECONDS).until("#modifyApp-0").areDisplayed();
    applicationsPage.typeAppName(appName);
    applicationsPage.typeAppDescription("Application de testing modify");
    applicationsPage.clickSubmitAppModify();
    assertThat(applicationsPage.pageSource()).contains(appName);
    applicationsPage.isAt();
  }
  ```

  - Page testée : "Applications"

  - Problématique testée : modification d'une application et test si l'application existe.

  - test principal effectué : nous testons si nous retrouvons l'application  précédement modifiée dans la liste d'applications.

    - Si le test est fonctionnel, l'assertion est accéptée.

    - Si la problématique n'est pas gérée, l'assertion va générer une erreur.

- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToDeleteApp() {
    final String appName = "application Test Modify";
    goTo(baseUrl);
    loginPage.typeEmailAddress(TEST_EMAIL_OK);
    loginPage.typePassword(TEST_PASSWORD_OK);
    loginPage.clickSignin();
    goTo(baseUrl+applicationsPage.getUrl());
    applicationsPage.clickDeleteApp();
    await().atMost(2, TimeUnit.SECONDS).until("#deleteApp-0").areDisplayed();
    applicationsPage.clickSubmitDeleteApp();
    assertThat(applicationsPage.pageSource()).doesNotContain(appName);
    applicationsPage.isAt();
  }
  ```

  - Page testée : "Applications"

  - Problématique testée : suppression d'une application et test si l'application n'existe pas.

  - test principal effectué : nous verifions que nous retrouvions  pas l'application précédement supprimée dans la liste d'applications.

    - Si le test est fonctionnel, l'assertion est accéptée.

    - Si la problématique n'est pas gérée, l'assertion va générer une erreur.

- ```java
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldCreateALotOfApplicationsThenBrowseBetweenPagesThenLogoutThenRetryToShowApps() {
    final int numberOfApplicationsToCreate = 25;
    String appName = "";
    goTo(baseUrl);
    loginPage.typeEmailAddress(TEST_EMAIL_OK);
    loginPage.typePassword(TEST_PASSWORD_OK);
    loginPage.clickSignin();
    goTo(baseUrl+applicationsPage.getUrl());
  
    // Create some applications
    for (int i = 0; i < numberOfApplicationsToCreate; i++){
      appName = "application Test" + Integer.toString(i);
      applicationsPage.clickAddApp();
      await().atMost(2, TimeUnit.SECONDS).until("#addApp").areDisplayed();
      applicationsPage.typeAppName(appName);
      applicationsPage.typeAppDescription("Application de testing" + Integer.toString(i));
      applicationsPage.clickSubmitApp();
    }
    applicationsPage.isAt();
  
    // Try to go to next pagination number
    applicationsPage.goToNextPageOfApplication();
    applicationsPage.goToNextPageOfApplication();
    applicationsPage.goToPreviousPageOfApplication();
    applicationsPage.isAt();
  
    // Logout
    goTo(baseUrl+"logout");
    loginPage.isAt();
  
    // Try to access application page
    goTo(baseUrl+applicationsPage.getUrl());
    loginPage.isAt();
  }
  ```

  - Page testée : "Login" , "Applications"
  - Problématique testée : Login avec un utilisateur précédement créé, création de plusieurs applications, déplacement avec les boutons de pagination, logout puis vérification que ne puissions plus accéder à la liste d'applications.
  - test numéro 1 : Nous vérifions que après la création des applications nous sommes toujours sur la page app et que nous puissions naviguer avec les boutons de pagination.
    - Si le test est fonctionnel, on est redirigé sur la page "Applications?value=<<monNuméroDePage>>".
    - Si la problématique n'est pas gérée, on est redirigé sur "Applications".
  - test numéro 2 : On se logout et on vérifie qu'on se trouve sur la bonne page.
    - Si le test est fonctionnel, on est redirigé sur la page "Login".
    - Si la problématique n'est pas gérée, on est redirigé sur "Applications".
  - test numéro 3 : on ré-essaie d'accéder à la page "Applications" sans que nous soyons connecté.
    - Si le test est fonctionnel, on est redirigé sur la page "Login".
    - Si la problématique n'est pas gérée, on est redirigé sur "Applications".