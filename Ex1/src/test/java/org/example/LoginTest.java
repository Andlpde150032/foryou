package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginTest {

    private WebDriver driver;
    private Properties properties;
    private Login loginPage;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() throws InterruptedException {
        // Navigate to the login page
        driver.get(properties.getProperty("base.url") + "/Account/Login.cshtml");

        // Create an instance of the Login page object
        loginPage = new Login(driver);

        // Perform login
        loginPage.performLogin(properties.getProperty("tester.email"), properties.getProperty("tester.password"));

        // Add a delay to observe result
        Thread.sleep(5000);

        // TODO: Add assertion to verify successful login.
        // For example, check for a welcome message or a logout button.
        // Assert.assertTrue(driver.findElement(By.id("logoutLink")).isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
