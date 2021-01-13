package StepDef;

import PageObjects.YoutubeChannelPage;
import PageObjects.YoutubeResultsPage;
import PageObjects.YoutubeSearchPage;
import Utility.BrowserUtility;
import Utility.PropertiesFileReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Properties;

public class YoutubeChannelValidations {

    PropertiesFileReader obj = new PropertiesFileReader();

    private WebDriver driver;

    @Given("^Open Chrome browser with URL$")
    public void open_Chrome_browser_with_URL() throws Throwable {
        Properties properties = obj.getProperty();

        driver = BrowserUtility.OpenBrowser(driver, properties.getProperty("browser.name"), properties.getProperty("browser.baseURL"));
    }

        @When("Search selenium tutorial \"([^\"]*)\"$")
    public void searchSeleniumTutorial(String searchString) throws Throwable {

        new YoutubeSearchPage(driver).NavigateToResultPage(searchString);
    }

    @When("^Click on channel name$")
    public void click_on_channel_name() throws Throwable {

        new YoutubeResultsPage(driver).NavigateToChannelName();
    }

    @Then("^Validate channel name$")
    public void validate_channel_name() throws Throwable {

        String expectedChannelName = "Selenium Java TestNG Tutorials - Bakkappa N - YouTube";
        String actualChannelName = new YoutubeChannelPage(driver).getTitle();
        Assert.assertEquals(actualChannelName, expectedChannelName, "Channel names are not matching");
        driver.quit();

    }
}