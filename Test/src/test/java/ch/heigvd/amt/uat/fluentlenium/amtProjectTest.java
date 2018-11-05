package ch.heigvd.amt.uat.fluentlenium;

import ch.heigvd.amt.uat.fluentlenium.pages.LoginFluentPage;
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

  private final String baseUrl = "http://amtprojet:8080/myapp/";

  @Page
  public LoginFluentPage loginPage;


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
  public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("not a valid email");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
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
