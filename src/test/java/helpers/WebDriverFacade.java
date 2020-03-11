package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebDriverFacade {
    private static WebDriver webDriver;
    private static int WAIT_SECONDS = 5;

    public WebDriverFacade(WebDriver webDriver){
        this.webDriver =  webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebElement waitForVisibilityOfElement(WebElement webElement) {
        return new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitForVisibilityOfElementLocated(By location) {
        return new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(location));
    }

    public List<WebElement> waitForVisibilityOfElementsLocated(By location) {
        return new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(location));
    }

    public WebElement waitForElementToBeClickable(WebElement webElement) {
        return new WebDriverWait(webDriver,WAIT_SECONDS).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public WebElement findElementByLocator(By locator){
        return webDriver.findElement(locator);
    }

    public List<WebElement> findElementsByLocator(By locator){
        return webDriver.findElements(locator);
    }

    public void moveSliderAction(WebElement slider, int move){
        int width = slider.getSize().getWidth();
        Actions moveAction = new Actions(webDriver);
        moveAction.moveToElement(slider, ((width*move)/100), 0).click();
        moveAction.build().perform();
    }
}
