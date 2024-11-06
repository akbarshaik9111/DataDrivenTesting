package com.akbar.sdet;

import com.akbar.sdet.util.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.time.Duration;

public class _04_SimpleCalculator {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        String filePath = System.getProperty("user.dir")+"\\data\\caldata.xlsx";

        int rows = ExcelUtils.getRowCount(filePath, "Sheet1");

        for(int r = 1; r <= rows; r++) {
            //1. Read data from excel
            String prnc = ExcelUtils.getCellData(filePath, "Sheet1", r, 0);
            String rateofinterest = ExcelUtils.getCellData(filePath, "Sheet1", r, 1);
            String per1 = ExcelUtils.getCellData(filePath, "Sheet1", r, 2);
            String per2 = ExcelUtils.getCellData(filePath, "Sheet1", r, 3);
            String fre = ExcelUtils.getCellData(filePath, "Sheet1", r, 4);
            String exp_mvalue = ExcelUtils.getCellData(filePath, "Sheet1", r, 5);

            //2. Pass above data into application
            driver.findElement(By.xpath("//input[@id='principal']")).sendKeys(prnc);
            driver.findElement(By.xpath("//input[@id='interest']")).sendKeys(rateofinterest);
            driver.findElement(By.xpath("//input[@id='tenure']")).sendKeys(per1);

            WebElement tenuredrpdwn = driver.findElement(By.xpath("//select[@id='tenurePeriod']"));
            Select se = new Select(tenuredrpdwn);
            se.selectByVisibleText(per2);

            WebElement feqdrpdwn = driver.findElement(By.xpath("//select[@id='frequency']"));
            Select se2 = new Select(feqdrpdwn);
            se2.selectByVisibleText(fre);

            // Clicking on Calculate button using java script to avoid Element Intercepted Exception
            WebElement calCulateBtn = driver.findElement(By.cssSelector("div[class='CTR PT15'] a[onclick*='return']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", calCulateBtn);
            Thread.sleep(1000);

            //3. Validation
            String act_mvalue = driver.findElement(By.xpath("//span[@id='resp_matval']/strong")).getText();


            if(Double.parseDouble(exp_mvalue) == Double.parseDouble(act_mvalue)) {
                System.out.println("Test Passed");
                ExcelUtils.setCellData(filePath, "Sheet1", r, 7, "Pass");
                ExcelUtils.fillGreenColor(filePath, "Sheet1", r, 7);
            } else {
                System.out.println("Test Failed");
                ExcelUtils.setCellData(filePath, "Sheet1", r, 7, "Fail");
                ExcelUtils.fillRedColor(filePath, "Sheet1", r, 7);
            }

            // Clicking on Clear button using java script to avoid Element Intercepted Exception
            WebElement clearBtn = driver.findElement(By.cssSelector("div[class='CTR PT15'] a[onclick*='reset']"));
            js.executeScript("arguments[0].click();", clearBtn);
        }
    }
}
