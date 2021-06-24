package linkedin;

import linkedin.pages.FeedPage;
import linkedin.pages.LoginPage;
import linkedin.pages.ResultSearchPage;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.*;

public class LoginTests extends BaseTest {
    @DataProvider(name = "data-provider")
    public Object[][] profiles() {
        return new Object[][] { { "Stephen@gmail.com", "Rbvh", "Man Huynh", "Stephen" } };
    }

    @Test(dataProvider = "data-provider")
    @Description("Test Description: Send A message to Profile")
    public void login_then_search_people_and_send_message(String username, String password, String profileName,
            String loginName) {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.goToHomePage();
        Assert.assertTrue(loginPage.isOnLinkedInHomePage(), "You're on Home page");
        
        loginPage.submitLogin(username, password);

        FeedPage feedPage = PageFactory.initElements(driver, FeedPage.class);
        Assert.assertTrue(feedPage.verifyUserIsOnFeedPage(), "You're on Feed page");
        feedPage.enterSearchQuery(profileName);

        ResultSearchPage resultPage = PageFactory.initElements(driver, ResultSearchPage.class);
        Assert.assertTrue(resultPage.verifyPeopleSearchReturned(), "Search result are returned successfully!");
        
        resultPage.starSendMessageWith(profileName).sendMessage(loginName);

        Assert.assertTrue(resultPage.verifyMessageSent(loginName), "Message sent successfully!");
        resultPage.closeMessageOverlayPopUp();

        resultPage.clickSignOutLink();
        Assert.assertTrue(loginPage.isOnLinkedInHomePage(), "You logged out successfully!");

    }

}