package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_XPath_Css {
    WebDriver driver;
    @BeforeClass
    // Arrange
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void TC_01_Register_Emty_Data(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Action - muốn test tc này thì phải nhấn button đăng ký để chefck mess nên phải làm action này
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Assert
        // Verify voiws duwx lieu mong doi
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

    }
    @Test
    public void TC_02_Register_InvalidEmail(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Action - muốn làm tc này thì phải nhập invalid email vào và nhấn đăng ký button
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Huong Huynh");
        driver.findElement(By.id("txtEmail")).sendKeys("A@1@3");
        driver.findElement(By.id("txtCEmail")).sendKeys("A@2@1");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
    }
    @Test
    public void TC_03_Register_Incorrect_Confirm_Email(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Huong Huynh");
        driver.findElement(By.id("txtEmail")).sendKeys("hhuynh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("A@gmai.com");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }
    @Test
    public void TC_04_Register_Incorrect_Password(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Action - muốn làm tc này thì phải nhập invalid email vào và nhấn đăng ký button
        driver.findElement(By.id("txtFirstname")).sendKeys("Huong Huynh");
        driver.findElement(By.id("txtEmail")).sendKeys("hhuynh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("hhuynh@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("1234");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

    }
    @Test
    public void TC_05_Register_Incorrect_Confirm_Password(){

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Action - muốn làm tc này thì phải nhập invalid pass vào và nhấn đăng ký button
        driver.findElement(By.id("txtFirstname")).sendKeys("Huong Huynh");
        driver.findElement(By.id("txtEmail")).sendKeys("hhuynh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("hhuynh@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234786");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }
    @Test
    public void TC_06_Register_Invalid_Phone_Number(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Action - muốn làm tc này thì phải nhập invalid pass vào và nhấn đăng ký button
        driver.findElement(By.id("txtFirstname")).sendKeys("Huong Huynh");
        driver.findElement(By.id("txtEmail")).sendKeys("hhuynh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("hhuynh@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("544666");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

    }
}
