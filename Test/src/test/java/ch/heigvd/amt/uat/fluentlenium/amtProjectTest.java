package ch.heigvd.amt.uat.fluentlenium;

import ch.heigvd.amt.uat.fluentlenium.pages.ApplicationsFluentPage;
import ch.heigvd.amt.uat.fluentlenium.pages.HomeFluentPage;
import ch.heigvd.amt.uat.fluentlenium.pages.LoginFluentPage;
import ch.heigvd.amt.uat.fluentlenium.pages.RegisterFluentPage;
import io.probedock.client.annotations.ProbeTest;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.fluentlenium.core.annotation.Page;

import java.util.concurrent.TimeUnit;

import static org.fluentlenium.core.filter.MatcherConstructor.contains;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class amtProjectTest extends FluentTest {

  private final String baseUrl = "http://amtprojet:8080/amtprojetRemote/";

  private final String TEST_EMAIL_OK = "test3@gmail.com";
  private final String TEST_PASSWORD_OK = "1234";

  @Page
  public LoginFluentPage loginPage;

  @Page
  public RegisterFluentPage registerPage;

  @Page
  public ApplicationsFluentPage applicationsPage;

  @Page
  public HomeFluentPage homePage;

  @Before
  public void information(){
    System.out.println("Be sure to haven't any projects in you database before tring the test !!!");
  }

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

  @Override
  public WebDriver getDefaultDriver() {
    //return new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", "/home/zutt/Documents/sync/Heig/AMT/ChromeDriver/chromedriver");
    return new ChromeDriver();
  }

  @Override
  public String getDefaultBaseUrl() {
    return baseUrl;
  }
  
}
