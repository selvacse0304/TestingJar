package com.devops.springselenium;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class TestWithMain {

    public static void main(String[] args) throws InterruptedException, IOException {

        System.out.println("Testing started..");



      //  WebDriver driver = null;

        /*
        ChromeOptions options = new ChromeOptions();
      //  options.addArguments("--headless=new");
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
      // Create a temporary user-data directory
        Path tempProfile = Files.createTempDirectory("chrome-profile");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "new");
        options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath().toString());
        options.addArguments("--remote-allow-origins=*");  // Required for newer Chrome versions


       // System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
        options.addArguments("--headless");  // Run in headless mode
        options.addArguments("--disable-gpu"); // Needed for headless mode
        options.addArguments("--no-sandbox"); // Bypass OS security model (important for AWS)
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource issues in AWS
        options.addArguments("--remote-allow-origins=*"); // Required for ChromeDriver 111+
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");


        driver = new ChromeDriver(options);
*/

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/google-chrome");  // Set correct binary location
        options.addArguments("--headless");           // Run in headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

      //  driver = new ChromeDriver();
      //  driver = new ChromeDriver(options);

        driver.get("https://www.facebook.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        System.out.println("Title : " + driver.getTitle() );
        WebElement registrationButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/div/div/div[2]/div/div[1]/form/div[5]/a"));
        registrationButton.click();

        WebElement firstname = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/form/div[1]/div[1]/div[1]/div[1]/div/div[1]/input"));
        firstname.sendKeys("dummy");

        WebElement lastname = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/form/div[1]/div[1]/div[1]/div[2]/div/div[1]/input"));
        lastname.sendKeys("dummy");


        WebElement monthList = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/form/div[1]/div[2]/div[2]/span/span/select[1]"));
        Select monthListSelect = new Select(monthList);
        monthListSelect.selectByVisibleText("Jan");

        WebElement dayList = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/form/div[1]/div[2]/div[2]/span/span/select[2]"));
        Select dayListSelect = new Select(dayList);
        dayListSelect.selectByVisibleText("10");

        WebElement yearList = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/form/div[1]/div[2]/div[2]/span/span/select[3]"));
        Select yearListSelect = new Select(yearList);
        yearListSelect.selectByVisibleText("1975");

        WebElement email = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/form/div[1]/div[6]/div/div[1]/input"));
        email.sendKeys("dummy@email.com");

        WebElement password = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/form/div[1]/div[8]/div/div[1]/input"));
        password.sendKeys("dummy");

        WebElement radioButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/form/div[1]/div[4]/span/span[2]/label/input"));
        if (!radioButton.isSelected()) {
            System.out.println("Male selected...");
            radioButton.click(); // Select the radio button
        }


        WebElement signUp = driver.findElement(By.name("websubmit"));
        signUp.click();

        // driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Thread.sleep(35000);

        WebElement signUpError = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/div[1]/div"));
        System.out.println("Error message in facebook page during registration :" + signUpError.getText());

        //assertEquals("Your password must be at least 6 characters long. Please try another.",signUpError.getText(),"Signup failure due to invalid password length");

        System.out.println("Testing Completed");

        driver.quit();
    }



}
