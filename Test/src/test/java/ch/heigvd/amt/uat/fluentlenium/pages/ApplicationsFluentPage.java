package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Login" page in the Amt Project.
 *
 * @author Yann Lederrey inspired by Olivier Liechti
 */
public class ApplicationsFluentPage extends AbstractMVCDemoFluentPage {

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Projects");
  }



  public String getUrl() {
    return "projects";
  }

}
