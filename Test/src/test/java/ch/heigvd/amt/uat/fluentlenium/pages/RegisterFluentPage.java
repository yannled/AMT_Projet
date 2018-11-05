package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Register" page in the Amt Project.
 *
 * @author Yann Lederrey inspired by Olivier Liechti
 */
public class RegisterFluentPage  extends AbstractMVCDemoFluentPage {

    private final static String inputFirstName = "#inputFirstName"; // id in the html code
    private final static String inputLastName = "#inputLastName"; // id in the html code
    private final static String inputEmail = "#inputEmail"; // id in the html code
    private final static String inputPassword1 = "#inputPassword1"; // id in the html code
    private final static String inputPassword2 = "#inputPassword2"; // id in the html code
    private final static String buttonSubmit = "#buttonSubmit"; // id in the html code

    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("Register");
    }

    public void typeFirstName(String firstName) {
        fill(inputFirstName).with(firstName);
    }

    public void typeLastName(String lastName) {
        fill(inputLastName).with(lastName);
    }

    public void typeEmailAddress(String email) {
        fill(inputEmail).with(email);
    }

    public void typeFirstPassword(String password) {
        fill(inputPassword1).with(password);
    }

    public void typeSecondPassword1(String password) {
        fill(inputPassword2).with(password);
    }

    public void clickRegister() {
        click(buttonSubmit);
    }

    public String getUrl() {
        return "register";
    }

}
