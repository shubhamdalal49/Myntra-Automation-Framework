package com.myntra.automation.pages;

import com.myntra.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    // Login page par "Login or Signup" header ko dhoondhne ke liye locator
    private By loginHeaderLocator = By.xpath("//div[contains(text(), 'Login or Signup')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeaderLocator));
            System.out.println("Verification SUCCESS: Login page is visible.");
            return true;
        } catch (Exception e) {
            System.out.println("Verification FAILED: Login page is not visible.");
            return false;
        }
    }
}
