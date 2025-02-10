package com.devops.springselenium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.Duration;

public class FirstSeleniumScript {



    static WebDriver driver = null;

    @BeforeAll
    public static void setUp()
    {
        driver = new ChromeDriver();
    }

    @Test
    public void testSelenumTestReg() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        System.out.println("Title: " + driver.getTitle());

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("Selenium");

        WebElement passwordBox = driver.findElement(By.name("my-password"));
        passwordBox.sendKeys("Password");

        WebElement textArea = driver.findElement(By.name("my-textarea"));
        textArea.sendKeys("Here is my text area. \n Do not try to change it.");

        /*
        WebElement disabledText = driver.findElement(By.name("my-disabled"));
        System.out.println("Disabled text: " + disabledText.getText());


        WebElement readOnlyText = driver.findElement(By.name("my-readonly"));
        System.out.println("ReadOnly text: " + readOnlyText.getText());
*/
        WebElement selectList = driver.findElement(By.name("my-select"));
        Select select = new Select(selectList);
        select.selectByVisibleText("Three");

        WebElement dataList = driver.findElement(By.name("my-datalist"));
        dataList.sendKeys("Seattle");
        //dataList.sendKeys(Keys.ARROW_DOWN); // Navigate options (if necessary)
        //dataList.sendKeys(Keys.ENTER);

        WebElement fileInput = driver.findElement(By.name("my-file"));
        //String filePath = "Test.txt";
        String filePath = new File("src/test/resources/Test.txt").getAbsolutePath();
        fileInput.sendKeys(filePath);

        WebElement checkbox = driver.findElement(By.id("my-check-2"));
        if (!checkbox.isSelected()) {
            checkbox.click(); // Select the checkbox
        }

        WebElement radioButton = driver.findElement(By.id("my-radio-2"));
        if (!radioButton.isSelected()) {
            radioButton.click(); // Select the radio button
        }

        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        colorPicker.sendKeys("#ff5733");

        WebElement dateField = driver.findElement(By.name("my-date"));
        dateField.sendKeys("02/13/2025");

        WebElement slider = driver.findElement(By.name("my-range"));
        slider.sendKeys("9");

        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(50000));
        Thread.sleep(2000);

        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        System.out.println("Message :"+ message.getText());


        driver.quit();

    }



}
