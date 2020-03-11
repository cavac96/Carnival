package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class HomePage extends BasePage{

    @FindBy(id = "cdc-destinations")
    private WebElement sailToButton;
    @FindBy(id = "cdc-dates")
    private WebElement datesButton;
    @FindBy(id = "cdc-durations")
    private WebElement durationButton;
    @FindBy(xpath = "//div[@id='ccl-cruise-search']//li[.//span[contains(text(), 'SEARCH')]]")
    private WebElement searchCruisesButton;
    @FindBy(xpath = "//a[contains(text(), 'No, thanks')]")
    private WebElement closeModalButton;

    private final String NOT_DISABLED_BUTTON_PATH = "//button[not(contains(@disabled, 'disabled'))]";
    private final String FILTER_OPTIONS_PATH = "//ul[@class='cdc-filter-cols-wrapper']" + NOT_DISABLED_BUTTON_PATH;
    private final String YEAR_LIST_PATH = "//div[@class='ddc-calendar']//ul";


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void closeModal() {
        webDriverFacade.waitForElementToBeClickable(closeModalButton);
        closeModalButton.click();
    }

    public String selectSailTo() {
        webDriverFacade.waitForElementToBeClickable(sailToButton);
        sailToButton.click();

        By sailToListLocator = By.xpath(FILTER_OPTIONS_PATH);
        List<WebElement> destinations = webDriverFacade.waitForVisibilityOfElementsLocated(sailToListLocator);

        int index = Utils.getRandomSelectIndex(destinations.size());
        WebElement selectedSailTo = webDriverFacade.waitForElementToBeClickable(destinations.get(index));
        selectedSailTo.click();

        return selectedSailTo.getText().toLowerCase();
    }

    public SearchResultPage searchCruises() {
        webDriverFacade.waitForElementToBeClickable(searchCruisesButton);
        searchCruisesButton.click();
        return PageFactory.initElements(webDriverFacade.getWebDriver(), SearchResultPage.class);
    }

    public String selectDates() {
        webDriverFacade.waitForElementToBeClickable(datesButton);
        datesButton.click();

        By yearListLocation = By.xpath(YEAR_LIST_PATH);
        List<WebElement> years = webDriverFacade.waitForVisibilityOfElementsLocated(yearListLocation);
        int indexYear = Utils.getRandomSelectIndex(years.size());
        WebElement selectedYear = webDriverFacade.waitForElementToBeClickable(years.get(indexYear));

        By monthList = By.xpath(NOT_DISABLED_BUTTON_PATH);
        List<WebElement> months = selectedYear.findElements(monthList);
        int indexMonth = Utils.getRandomSelectIndex(months.size());
        WebElement selectedMonth = webDriverFacade.waitForVisibilityOfElement(months.get(indexMonth));

        selectedMonth.click();
        return selectedMonth.getText().toLowerCase();
    }

    public List<Integer> selectDuration() {
        webDriverFacade.waitForElementToBeClickable(durationButton);
        durationButton.click();

        By durationListLocator = By.xpath(FILTER_OPTIONS_PATH);
        List<WebElement> durations = webDriverFacade.waitForVisibilityOfElementsLocated(durationListLocator);

        int index = Utils.getRandomSelectIndex(durations.size());
        WebElement selectedDuration = webDriverFacade.waitForElementToBeClickable(durations.get(index));
        selectedDuration.click();

        return Utils.parseDayText(selectedDuration.getText());
    }

}
