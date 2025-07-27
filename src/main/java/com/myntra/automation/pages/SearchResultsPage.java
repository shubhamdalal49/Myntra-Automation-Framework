package com.myntra.automation.pages;

import com.myntra.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    // This XPath finds all product items on the results page
    @FindBy(xpath = "//li[@class='product-base']")
    private List<WebElement> productList;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage clickOnSecondProduct() {
        if (productList.size() >= 2) {
            // We click the second product (index 1)
            productList.get(1).click();
        } else {
            throw new RuntimeException("Less than 2 products found on the search results page.");
        }
        return new ProductPage(driver);
    }
}
