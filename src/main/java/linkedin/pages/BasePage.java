package linkedin.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void waitForElementPresent(WebElement ele) {
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementNotVisible(WebElement ele) {
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void waitForElementIsClickable(WebElement ele) {
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    @Step("Click on Element {0}")
    public void clickOnElement(WebElement ele) {
        waitForElementPresent(ele);
        ele.click();
    }

    @Step("Input text: {1}")
    public void inputText(WebElement ele, String text) {
        waitForElementPresent(ele);
        ele.sendKeys(text);
    }
}
