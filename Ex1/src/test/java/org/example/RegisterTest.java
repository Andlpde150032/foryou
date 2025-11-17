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

public class RegisterTest {

    private WebDriver driver;
    private Properties properties;
    private RegisterPage registerPage;

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
    public void testRegistration() throws InterruptedException {
        // Navigate to the registration page
        driver.get(properties.getProperty("base.url") + "/Account/Register.cshtml");

        // Create an instance of the RegisterPage and perform registration
        registerPage = new RegisterPage(driver);
        registerPage.performRegistration(
                properties.getProperty("tester.email"),
                properties.getProperty("tester.password"),
                properties.getProperty("tester.pid")
        );

        // Wait to observe the result
        Thread.sleep(5000);

        // TODO: Add assertions here to verify the outcome of the registration.
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
