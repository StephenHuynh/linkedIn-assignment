package linkedin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class FeedPage extends BasePage {

    public FeedPage(WebDriver driver) {
        super(driver);
    }

    final String textStartPostLocator = "//span[text()='Start a post']";
    @FindBy(xpath = textStartPostLocator)
    WebElement textStartPost;

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement textSearch;

    @FindBy(id = "session_password")
    WebElement textPassword;
    
    @FindBy(css = "button[class=‘sign-in-form__submit-button’]")
    WebElement buttonSignIn;

    @Step("Enter query: {0} to search")
    public FeedPage enterSearchQuery(String name) {
        waitForElementPresent(textSearch);
        clickOnElement(textSearch);
        textSearch.sendKeys(name + Keys.ENTER);
        return this;
    }

    public boolean verifyUserIsOnFeedPage() {
        waitForElementPresent(textStartPost);
        if (driver.findElements(By.xpath(textStartPostLocator)).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
