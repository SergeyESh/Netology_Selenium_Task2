package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OrderCardValidValueTest {

    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void createBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
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
