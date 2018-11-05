package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Login" page in the Amt Project.
 *
 * @author Yann Lederrey inspired by Olivier Liechti
 */
public class LoginFluentPage extends AbstractMVCDemoFluentPage {

  private final static String inputEmail = "#inputEmail"; // id in the html code
  private final static String inputPassword = "#inputPassword"; // id in the html code
  private final static String buttonSignin = "#buttonSubmit"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Login Page");
  }

  public void typeEmailAddress(String email) {
    fill(inputEmail).with(email);
  }

  public void typePassword(String password) {
    fill(inputPassword).with(password);
  }

  public void clickSignin() {
    click(buttonSignin);
  }

  public String getUrl() {
    return "/";
  }

}
