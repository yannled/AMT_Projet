package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeFluentPage extends AbstractMVCDemoFluentPage {

    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("Home");
    }

    public String getUrl() {
        return "Home";
    }

}

