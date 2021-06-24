package linkedin.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ResultSearchPage extends BasePage {

    public ResultSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".search-reusables__filters-bar-grouping")
    WebElement filterSearch;

    @FindBy(css = ".search-results-container")
    WebElement searchResultContainer;

    final String returnPeopleLocator = "//a[@class='app-aware-link']/span/span";
    @FindBy(xpath = returnPeopleLocator)
    WebElement returnedPeople;

    final String btnMessageLocator = "//span[text()='Message']";
    @FindBy(xpath = btnMessageLocator)
    WebElement buttonMessage;

    final String resultItemLocator = "//li[@class='reusable-search__result-container ']";
    @FindBy(xpath = resultItemLocator)
    WebElement resultItem;

    @FindBy(css = "div[class*='msg-overlay-conversation-bubble']")
    WebElement messageOverlay;

    final String btnSendMessageLocator = "//button[text()='Send']";
    @FindBy(xpath = btnSendMessageLocator)
    WebElement buttonSendMessage;

    final String txtAreaMessageLocator = "//div[@aria-label='Write a message…']/p";
    @FindBy(xpath = txtAreaMessageLocator)
    WebElement textAreaMessage;

    @FindBy(css = "li[class='msg-s-message-list__event clearfix']:last-of-type p[class*='msg-s-event-listitem__body']")
    WebElement textSentMessage;

    @FindBy(css = "button[data-control-name='overlay.close_conversation_window']")
    WebElement buttonCloseMessageOverlay;

    @FindBy(xpath = "//header[contains(@aria-label,'Hide your conversation')]//img")
    WebElement closeCoversation;

    @FindBy(xpath = "//button/span[text()='Me']")
    WebElement buttonMe;

    @FindBy(xpath = "//a[contains(@href, '/logout/')]")
    WebElement btnSignOut;

    @Step("Change Filter: {0} to view the Results")
    public ResultSearchPage changeFilter(String option) {
        waitForElementPresent(filterSearch);
        String optionLocator = "button[aria-label='" + option + "']";
        waitForElementIsClickable(driver.findElement(By.cssSelector(optionLocator)));
        driver.findElement(By.cssSelector(optionLocator)).click();
        return this;
    }

    @Step("Verify the search result returned")
    public boolean verifyPeopleSearchReturned() {
        waitForElementPresent(searchResultContainer);
        waitForElementPresent(resultItem);
        if (driver.findElements(By.xpath(resultItemLocator)).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Step("Open Message Popup with {0}")
    public ResultSearchPage starSendMessageWith(String people) {
        waitForElementPresent(resultItem);
        List<WebElement> resultList = driver.findElements(By.xpath(resultItemLocator));
        for (WebElement item : resultList) {
            if (item.findElement(By.xpath(returnPeopleLocator)).getText().contains(people)) {
                item.findElement(By.xpath(btnMessageLocator)).click();
                break;
            }
        }
        return this;
    }

    @Step("Send a message")
    public ResultSearchPage sendMessage(String content) {
        waitForElementPresent(messageOverlay);
        String defaultMsg = "Hello, Tim. This is the auto message from " + content;
        if (!driver.findElements(By.xpath(btnSendMessageLocator)).isEmpty()) {
            textAreaMessage.sendKeys(defaultMsg);
            waitForElementIsClickable(buttonSendMessage);
            clickOnElement(buttonSendMessage);

        } else {
            textAreaMessage.sendKeys(defaultMsg + Keys.ENTER);
        }

        return this;
    }

    @Step("Verify the message sent with content: {0}")
    public boolean verifyMessageSent(String msg) {
        waitForElementPresent(textSentMessage);
        return textSentMessage.getText().contains(msg);
    }

    @Step("Close the Message Overlay Popup")
    public ResultSearchPage closeMessageOverlayPopUp() {
        waitForElementIsClickable(buttonCloseMessageOverlay);
        clickOnElement(buttonCloseMessageOverlay);
        return this;
    }

    @Step("User logs out")
    public LoginPage clickSignOutLink() {
        waitForElementPresent(buttonMe);
        buttonMe.click();
        waitForElementPresent(btnSignOut);
        btnSignOut.click();
        return new LoginPage(driver);
    }

}
