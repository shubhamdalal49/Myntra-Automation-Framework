package com.myntra.automation.pages;

import com.myntra.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage {

    private By availableSizeButtonsLocator = By.xpath(
        "//div[contains(@class, 'size-buttons-size-buttons')]//button[not(@disabled)]"
    );

    @FindBy(xpath = "//div[contains(@class, 'pdp-add-to-bag')]")
    private WebElement addToBagButton;
    
    // The Ultimate Confirmation: "GO TO BAG" button
    private By goToBagConfirmationLocator = By.xpath("//span[text()='GO TO BAG']/..");

    @FindBy(xpath = "//span[text()='Bag']")
    private WebElement goToBagIcon;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectFirstAvailableSize() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            System.out.println("Searching for ONLY CLICKABLE/ENABLED size buttons...");
            List<WebElement> availableSizes = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(availableSizeButtonsLocator)
            );
            if (!availableSizes.isEmpty()) {
                System.out.println("Found " + availableSizes.size() + " available sizes. Clicking the first one.");
                availableSizes.get(0).click();
                System.out.println("SUCCESS: A size was selected successfully.");
            } else {
                throw new RuntimeException("No clickable/enabled size buttons were found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not find or select a size. Error: " + e.getMessage());
        }
    }

    // Is method ko humne ultimate, bulletproof bana diya hai
    public void clickAddToBagAndConfirm() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            
            // Step 1: "Add to Bag" par click karein
            WebElement addToBagBtn = wait.until(ExpectedConditions.elementToBeClickable(addToBagButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToBagBtn);
            System.out.println("Clicked on 'Add to Bag' using JavaScript.");

            // Step 2 (Sabse Zaroori): Intezaar karein ki button "GO TO BAG" mein badal jaaye
            wait.until(ExpectedConditions.presenceOfElementLocated(goToBagConfirmationLocator));
            System.out.println("CONFIRMATION SUCCESS: 'Add to Bag' button has changed to 'GO TO BAG'.");

        } catch (Exception e) {
            System.out.println("CONFIRMATION FAILED: Product was NOT added to the bag successfully.");
            throw new RuntimeException("Error after clicking 'Add to Bag'. Confirmation ('GO TO BAG' button) did not appear. Error: " + e.getMessage());
        }
    }
    
    public CartPage goToBag() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(goToBagIcon)).click();
        System.out.println("Navigating to Bag.");
        return new CartPage(driver);
    }
}
