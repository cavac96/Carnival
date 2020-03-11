package pages;

import helpers.WebDriverFacade;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public WebDriverFacade webDriverFacade;

    public BasePage(WebDriver webDriver) {
        this.webDriverFacade = new  WebDriverFacade(webDriver);
    }
}
