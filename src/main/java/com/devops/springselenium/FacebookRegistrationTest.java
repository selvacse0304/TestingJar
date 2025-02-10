package com.devops.springselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FacebookRegistrationTest {
    public static void main(String[] args) {
        // Set the path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        // Configure Chrome options for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode
        options.addArguments("--disable-gpu"); // Needed for headless mode
        options.addArguments("--no-sandbox"); // Bypass OS security model (important for AWS EC2)
        options.addArguments("--disable-dev-shm-usage"); // Avoid issues with limited resources
        options.addArguments("--remote-allow-origins=*"); // Required for ChromeDriver 111+

        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);

        // Open Facebook Registration page
        driver.get("https://www.facebook.com/r.php");

        // Create WebDriverWait instance for waiting until elements are available
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Fill in the registration form (name, email, password, etc.)
            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
            firstName.sendKeys("John");

            WebElement lastName = driver.findElement(By.name("lastname"));
            lastName.sendKeys("Doe");

            WebElement email = driver.findElement(By.name("reg_email__"));
            email.sendKeys("johndoe12345@example.com");

            WebElement reEnterEmail = driver.findElement(By.name("reg_email_confirmation__"));
            reEnterEmail.sendKeys("johndoe12345@example.com");

            WebElement password = driver.findElement(By.name("reg_passwd__"));
            password.sendKeys("SecurePassword123");

            WebElement day = driver.findElement(By.name("birthday_day"));
            day.sendKeys("15");

            WebElement month = driver.findElement(By.name("birthday_month"));
            month.sendKeys("May");

            WebElement year = driver.findElement(By.name("birthday_year"));
            year.sendKeys("1995");

            // Select gender (male)
            WebElement genderMale = driver.findElement(By.xpath("//input[@value='2']"));
            genderMale.click();

            // Click the "Sign Up" button
            WebElement signUpButton = driver.findElement(By.name("websubmit"));
            signUpButton.click();

            // Wait for the next page to load or for the confirmation message to appear
            wait.until(ExpectedConditions.urlContains("facebook.com"));

            // Print the current page URL to confirm successful registration
            System.out.println("Page URL after registration: " + driver.getCurrentUrl());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser after the test
            driver.quit();
        }
    }
}
