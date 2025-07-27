package com.myntra.automation.pages;

import com.myntra.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    private By productInCartLocator = By.xpath("//a[@class='itemContainer-base-itemLink']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCartDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(productInCartLocator));
            System.out.println("Verification SUCCESS: Product is visible in the cart.");
            return true;
        } catch (Exception e) {
            System.out.println("Verification FAILED: Product was not visible in the cart after waiting.");
            return false;
        }
    }
}
