package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SearchResultPage extends BasePage {

    @FindBy(id = "sfn-nav-pricing")
    private WebElement pricingButton;

    @FindBy(xpath = "//ccl-view-result-container")
    private WebElement resultContainer;

    private final String CARDS_PATH = "//article";
    private final String TITLE_CARD_PATH = "//span[contains(@class,'main-text')]";
    private final String INFORMATION_BUTTON_PATH = "//div[@class='vrgf-price-box']";
    private final String DATES_PATH = "//div[@class='spg-content']//div[@class='slick-track']//p";
    private final String CRUISE_DURATION_PATH = "//ccl-cruise-glance//span[contains(@class,'duration-title')]//" +
            "span[contains(text(), 'Day')]";
    private final String LEARN_MORE_BUTTON_PATH = "//div[@class='vrgf-learn-more']/a";

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void assertSailTo(String sailTo) {
        webDriverFacade.waitForVisibilityOfElement(resultContainer);

        List<WebElement> resultList = webDriverFacade.findElementsByLocator(By.xpath(CARDS_PATH));

        int index = Utils.getRandomSelectIndex(resultList.size());
        WebElement randomChoice = webDriverFacade.waitForVisibilityOfElement(resultList.get(index));
        WebElement actualResult = randomChoice.findElement(By.xpath(TITLE_CARD_PATH));

        assertThat("Sail to does not match", actualResult.getText().toLowerCase(), containsString(sailTo));
    }

    public String openCruiseInformation() {
        webDriverFacade.waitForVisibilityOfElement(resultContainer);
        List<WebElement> resultList = webDriverFacade.findElementsByLocator(By.xpath(CARDS_PATH));

        int index = Utils.getRandomSelectIndex(resultList.size());
        WebElement randomChoice = webDriverFacade.waitForVisibilityOfElement(resultList.get(index));

        WebElement informationButton = randomChoice.findElement(By.xpath(INFORMATION_BUTTON_PATH));
        webDriverFacade.waitForVisibilityOfElement(informationButton);
        informationButton.click();

        List<WebElement> dates = webDriverFacade.findElementsByLocator(By.xpath(DATES_PATH));

        int indexDate = Utils.getRandomSelectIndex(dates.size());
        WebElement selectedDate = webDriverFacade.waitForVisibilityOfElement(dates.get(indexDate));
        return selectedDate.getText().toLowerCase();
    }

    public void assertDates(String expectedDate, String actualDate) {
        assertThat("Date does not match", actualDate, containsString(expectedDate));
    }

    public void assertDuration(List<Integer> durationLimits) {
        webDriverFacade.waitForVisibilityOfElement(resultContainer);
        List<WebElement> resultList = webDriverFacade.findElementsByLocator(By.xpath(CARDS_PATH));

        int index = Utils.getRandomSelectIndex(resultList.size());
        WebElement randomChoice = webDriverFacade.waitForVisibilityOfElement(resultList.get(index));
        WebElement actualResult = randomChoice.findElement(By.xpath(CRUISE_DURATION_PATH));
        List<Integer> duration = Utils.parseDayText(actualResult.getText());

        if(durationLimits.size() > 1) {
            assertThat("Duration does not match", duration.get(0),
                    is(both(greaterThanOrEqualTo(durationLimits.get(0))).and(lessThan(durationLimits.get(1)))));
        } else {
            assertThat("Duration does not match", duration.get(0), greaterThanOrEqualTo(durationLimits.get(0)));
        }
    }

    public void filterByPrice() {
        webDriverFacade.waitForElementToBeClickable(pricingButton);
        changePricing();
    }

    private void changePricing() {
        pricingButton.click();
    }

    public ItineraryPage openItinerary() {
        webDriverFacade.waitForVisibilityOfElement(resultContainer);
        List<WebElement> resultList = webDriverFacade.findElementsByLocator(By.xpath(CARDS_PATH));

        int index = Utils.getRandomSelectIndex(resultList.size());
        WebElement randomChoice = webDriverFacade.waitForVisibilityOfElement(resultList.get(index));

        WebElement learnMoreButton = randomChoice.findElement(By.xpath(LEARN_MORE_BUTTON_PATH));
        webDriverFacade.waitForVisibilityOfElement(learnMoreButton);
        learnMoreButton.click();

        return PageFactory.initElements(webDriverFacade.getWebDriver(), ItineraryPage.class);
    }
}
