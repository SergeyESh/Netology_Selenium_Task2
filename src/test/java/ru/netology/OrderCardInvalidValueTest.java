package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCardInvalidValueTest {

    private WebDriver driver;

    @BeforeEach
    void createBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void oderCardTest() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Ivanov Ivan");
        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+79163131737");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement'] span")).click();
        driver.findElement(By.className("button__content")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'name'] span.input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualText.strip());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
}
