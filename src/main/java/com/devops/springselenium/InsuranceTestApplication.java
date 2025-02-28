package com.devops.springselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InsuranceTestApplication {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);

        driver.get("http://54.162.54.251:8081/contact.html");

        System.out.println("Title : " +driver.getTitle());

        WebElement yourName = driver.findElement(By.id("inputName"));
        yourName.sendKeys("Selvakumar Murugesan");

        WebElement yourNumber = driver.findElement(By.id("inputNumber"));
        yourNumber.sendKeys("+1222222222");

        WebElement yourEmail = driver.findElement(By.id("inputMail"));
        yourEmail.sendKeys("selva@email.com");

        WebElement yourMessage = driver.findElement(By.id("inputMessage"));
        yourMessage.sendKeys("Please call me back");

        WebElement sendButton = driver.findElement(By.id("my-button"));
        sendButton.click();

        WebElement serverResponse = driver.findElement(By.id("response"));
        String response = serverResponse.getText().trim();

        System.out.println("Response received from server on send: " + response);

        if(response.equals("Message Sent"))
            System.out.println("PASS: Get in touch test case passed");
        else
            System.out.println("FAIL: Test case failed");
    }

}
