package ch.heigvd.amt.uat.fluentlenium;

import ch.heigvd.amt.uat.fluentlenium.pages.ApplicationsFluentPage;
import ch.heigvd.amt.uat.fluentlenium.pages.LoginFluentPage;
import ch.heigvd.amt.uat.fluentlenium.pages.RegisterFluentPage;
import io.probedock.client.annotations.ProbeTest;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.fluentlenium.core.annotation.Page;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class amtProjectTest extends FluentTest {

  private final String baseUrl = "http://amtprojet:8080/amtprojetRemote/";

  @Page
  public LoginFluentPage loginPage;

  @Page
  public RegisterFluentPage registerPage;

  @Page
  public ApplicationsFluentPage applicationsPage;

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
    registerPage.typeEmailAddress("test@test2.com");
    registerPage.typeFirstName("Testeur");
    registerPage.typeLastName("test");
    registerPage.typeFirstPassword("1234");
    registerPage.typeSecondPassword1("1234");
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
    loginPage.typeEmailAddress("test@test2.com");
    loginPage.typePassword("1234");
    loginPage.clickSignin();
    applicationsPage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToAddApp() {
    goTo(baseUrl);
    loginPage.typeEmailAddress("test@test2.com");
    loginPage.typePassword("1234");
    loginPage.clickSignin();
    goTo(baseUrl+applicationsPage.getUrl());
    applicationsPage.clickAddApp();
    /*
    applicationsPage.clickAddApp();
    applicationsPage.typeAppName("application Test");
    applicationsPage.typeAppDescription("Application de testing");
    applicationsPage.clickSubmitApp();
    applicationsPage.clickModifyApp();
    */
    applicationsPage.isAt();
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
