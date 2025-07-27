package com.myntra.automation.stepdefinitions;

import com.myntra.automation.base.BaseTest;
import com.myntra.automation.pages.CartPage;
import com.myntra.automation.pages.HomePage;
import com.myntra.automation.pages.ProductPage;
import com.myntra.automation.pages.SearchResultsPage;
import com.myntra.automation.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MyntraStepDefinitions extends BaseTest {

    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @Before
    public void setUp() {
        initialization();
        this.driver = BaseTest.driver;
    }

    @Given("I am on the Myntra homepage")
    public void i_am_on_the_myntra_homepage() {
        homePage = new HomePage(driver);
    }

    @When("I search for {string}")
    public void i_search_for(String product) {
        searchResultsPage = homePage.searchForProduct(product);
    }

    @When("I click on the second product from the search results")
    public void i_click_on_the_second_product_from_the_search_results() {
        productPage = searchResultsPage.clickOnSecondProduct();
    }

    @When("I switch to the new tab")
    public void i_switch_to_the_new_tab() {
        String parentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!parentWindow.equalsIgnoreCase(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @When("I select a size for the product")
    public void i_select_a_size_for_the_product() {
        productPage.selectFirstAvailableSize();
    }

    @When("I click on {string}")
    public void i_click_on(String buttonText) {
        if (buttonText.equalsIgnoreCase("ADD TO BAG")) {
            productPage.clickAddToBagAndConfirm();
        }
    }

    @When("I go to the Shopping Bag")
    public void i_go_to_the_shopping_bag() {
        cartPage = productPage.goToBag();
    }

    @Then("I should be on the Shopping Bag page")
    public void i_should_be_on_the_shopping_bag_page() {
        Assert.assertTrue(cartPage.isProductInCartDisplayed(), "Verification Failed: User is not on the Shopping Bag page.");
        System.out.println("TEST SCENARIO PASSED: Product successfully added to cart and verified.");
    }
    
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            if (driver != null) {
                final byte[] screenshotBytes = ScreenshotUtil.getScreenshotAsBytes(driver);
                scenario.attach(screenshotBytes, "image/png", scenario.getName());
                ScreenshotUtil.takeScreenshot(driver, scenario.getName());
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
