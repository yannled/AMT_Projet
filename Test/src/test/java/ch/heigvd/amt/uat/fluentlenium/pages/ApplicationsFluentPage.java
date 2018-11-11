package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Login" page in the Amt Project.
 *
 * @author Yann Lederrey inspired by Olivier Liechti
 */
public class ApplicationsFluentPage extends AbstractMVCDemoFluentPage {

  private final static String addAppLink = "#addAppLink"; // id in the html code
  private final static String ApplicationName = "#ApplicationName";
  private final static String ApplicationDescription = "#ApplicationDescription";
  private final static String submitApp = "#submitApp";
  private final static String modifyApp = "#modifyApp-0";

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Projects");
  }

  public void clickAddApp() {
    $("#addAppLink").click();
  }

  public void clickModifyApp(){
    click(modifyApp);
  }

  public void typeAppName(String appName) {
    fill(ApplicationName).with(appName);
  }

  public void typeAppDescription(String appDescription) { fill(ApplicationDescription).with(appDescription); }

  public String getUrl() {
    return "projects";
  }

  public void clickSubmitApp() {
    click(submitApp);
  }

}
