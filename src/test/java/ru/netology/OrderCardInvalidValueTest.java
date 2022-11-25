package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrderCardInvalidValueTest {

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
        driver.get("http://localhost:9999");
    }

    @Test
    void oderCardTest1() {

        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Ivanov Ivan");
        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+79163131737");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement'] span")).click();
        driver.findElement(By.className("button__content")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'name'].input_invalid .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualText.strip());
    }

    @Test
    void oderCardTest2() {

        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+7916313173");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement'] span")).click();
        driver.findElement(By.className("button__content")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'phone'].input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualText.strip());
    }

    @Test
    void oderCardTest3() {

        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+79163131737");
        driver.findElement(By.className("button__content")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'agreement'].input_invalid .checkbox__text")).getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", actualText.strip());
    }

    @Test
    void oderCardTest4() {

        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+79163131737");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement'] span")).click();
        driver.findElement(By.className("button__content")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'name'].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", actualText.strip());
    }

    @Test
    void oderCardTest5() {

        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement'] span")).click();
        driver.findElement(By.className("button__content")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'phone'].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", actualText.strip());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
}
