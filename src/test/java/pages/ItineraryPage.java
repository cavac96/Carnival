package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ItineraryPage extends BasePage {

    @FindBy(id = "sm-itin")
    private WebElement itineraryButton;
    @FindBy(xpath = "//div[@id='section-itinerary']//div[@class='duration-title']/p")
    private WebElement durationP;
    @FindBy(xpath = "//p[@class='day']")
    private List<WebElement> itineraryDays;
    @FindBy(xpath = "//li[@id='sm-booking-btn']/booking-button")
    private WebElement bookNowButton;

    public ItineraryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void assertBookingButtonExists() {
        webDriverFacade.waitForVisibilityOfElement(bookNowButton);
        String bookNowButtonText = bookNowButton.getText().toLowerCase();
        assertThat("Button does not exists", bookNowButtonText, containsString("book now"));
    }

    public void assertItineraryPageIsLoaded() {
        webDriverFacade.waitForVisibilityOfElement(itineraryButton);
        assertThat("Itinerary page did not load", true, is(itineraryButton.isDisplayed()));
    }

    public void assertCardForEachDay() {
        webDriverFacade.waitForVisibilityOfElement(durationP);
        int days = itineraryDays.size();
        List<Integer> duration = Utils.parseDayText(durationP.getText());

        assertThat("Invalid days information", duration.get(0), comparesEqualTo(days));
    }
}
