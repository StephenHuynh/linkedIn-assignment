package linkedin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    String baseURL = "http://www.linkedin.com/";

    @FindBy(id = "session_key")
    WebElement textUsername;

    @FindBy(id = "session_password")
    WebElement textPassword;

    @FindBy(className = "sign-in-form__submit-button")
    WebElement buttonSignIn;

    @FindBy(css = ".hero > h1")
    WebElement welcomeText;

    @Step("Go To Home Page")
    public LoginPage goToHomePage() {
        driver.get(baseURL);
        return this;
    }

    @Step("Input Username:{0} and Password, then login")
    public LoginPage submitLogin(String username, String password) {
        waitForElementPresent(textUsername);
        waitForElementPresent(textPassword);
        inputText(textUsername, username);
        inputText(textPassword, password);
        clickOnElement(buttonSignIn);
        return this;
    }

    @Step("Verify the LinkedIn Home Page displayed!")
    public boolean isOnLinkedInHomePage() {
        waitForElementPresent(welcomeText);
        return welcomeText.getText().contains("Welcome to your professional community");
    }

}
