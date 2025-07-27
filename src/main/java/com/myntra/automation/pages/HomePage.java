package com.myntra.automation.pages;

import com.myntra.automation.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(className = "desktop-searchBar")
    private WebElement searchBar;

    public HomePage(WebDriver driver) {
        super(driver); // Calls the BasePage constructor
    }

    public SearchResultsPage searchForProduct(String productName) {
        searchBar.sendKeys(productName);
        searchBar.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
