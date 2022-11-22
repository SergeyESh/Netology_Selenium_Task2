package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderCardValidValueTest {

    private WebDriver driver;

    @BeforeEach
    void createBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void oderCardTest() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+79163131737");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement'] span")).click();
        driver.findElement(By.className("button__content")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'order-success']")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText.strip());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
}
