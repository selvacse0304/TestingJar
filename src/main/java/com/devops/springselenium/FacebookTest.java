package com.devops.springselenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacebookTest {



    static WebDriver driver = null;

    @BeforeAll
    public static void setUp()
    {
        driver = new ChromeDriver();
    }

    @Test
    public void loginTestMandatoryFields()
    {
        driver.get("https://www.facebook.com");
        System.out.println("Title: " + driver.getTitle());

        WebElement submitButton = driver.findElement(By.name("login"));
        submitButton.click();

        WebElement loginError = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/form/div/div[1]/div[2]"));
        System.out.println("Error received: " + loginError.getText());
        String actualText = loginError.getText();
        Assertions.assertTrue(actualText.contains("The email or mobile number you entered isn’t connected to an account"), loginError.getText());

    }

    @Test
    public void createAccount()
    {
        driver.get("https://www.facebook.com");
        WebElement registrationButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/div/div/div[2]/div/div[1]/form/div[5]/a"));
        registrationButton.click();


        WebElement signUp = driver.findElement(By.name("websubmit"));
        signUp.click();


        WebElement firstName = driver.findElement(By.name("firstname"));
        String ariaInvalidValue = firstName.getAttribute("aria-invalid");
        System.out.println("aria-invalid: " + ariaInvalidValue);
        Assertions.assertEquals("true", ariaInvalidValue, "aria-invalid should be 'true' for an invalid first name input.");

    }

    @Test
    public void signUpNewAccount() throws InterruptedException {
        driver.get("https://www.facebook.com");
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
        System.out.println("Error:" + signUpError.getText());

        //assertEquals("Your password must be at least 6 characters long. Please try another.",signUpError.getText(),"Signup failure due to invalid password length");
        Assertions.assertEquals("An error occurred. Please try again.",signUpError.getText(),"Signup failure due to invalid password length");


    }

    @AfterAll
    public static void cleanUp()
    {
        driver.quit();
    }


}
