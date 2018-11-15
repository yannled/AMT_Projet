package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Login" page in the Amt Project.
 *
 * @author Yann Lederrey inspired by Olivier Liechti
 */
public class ApplicationsFluentPage extends AbstractMVCDemoFluentPage {

  private final static String addAppLink = "#addAppLink"; // id in the html code
  private final static String modifyAppLink = "#modifyAppButton-1";
  private final static String ApplicationName = "#ApplicationName";
  private final static String ApplicationDescription = "#ApplicationDescription";
  private final static String submitApp = "#submitApp";
  private final static String submitAppToModify = "#submitAppModify";
  private final static String deleteApp = "#deleteAppButton-1";
  private final static String submitDeleteApp = "#submitAppDelete";
  private final static String nextPageOfApplication = "#nextPage";
  private final static String previousPageOfApplication = "#previousPage";

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Projects");
  }

  public void goToNextPageOfApplication(){
    click(nextPageOfApplication);
  }

  public void goToPreviousPageOfApplication(){
    click(previousPageOfApplication);
  }

  public void clickAddApp() {
    click(addAppLink);
  }

  public void clickModifyApp(){
    click(modifyAppLink);
  }

  public void clickDeleteApp(){
    click(deleteApp);
  }

  public void clickSubmitDeleteApp(){
    click(submitDeleteApp);
  }

  public void typeAppName(String appName) {
    fill(ApplicationName).with(appName);
  }

  public void typeAppDescription(String appDescription) { fill(ApplicationDescription).with(appDescription); }

  public void clickSubmitApp() {
    click(submitApp);
  }

  public void clickSubmitAppModify(){
    click(submitAppToModify);
  }
  public String getUrl() {
    return "applications";
  }
}
