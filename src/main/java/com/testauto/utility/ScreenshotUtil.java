package com.testauto.utility;


import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.ashot.*;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;



import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    private static final Logger log = LogManager.getLogger(ScreenshotUtil.class);
    private static String getScreenshotFilePath(Scenario scenario){
        return CommonPropertyManager.getScreenshotsDir()
                +TestRunUtil.getUniqueFileNameBasedOnScenario(scenario)
                +"."
                +CommonPropertyManager.getScreenshotsFormat();
    }

    public static void captureFullScreenshot(WebDriver driver, Scenario scenario){
        FileUtil.createFileDirectory(CommonPropertyManager.getScreenshotsDir());
        String screenshotFilePath = getScreenshotFilePath(scenario);

        Dimension currentDimension = driver.manage().window().getSize();
        int height = currentDimension.getHeight();
        int width = currentDimension.getWidth();
        Dimension screenshotDimension =  new Dimension(1920, 1080);
       driver.manage().window().setSize(screenshotDimension);
        try{
           Screenshot screenshot = new AShot().shootingStrategy(
                   ShootingStrategies.viewportPasting(
                           ShootingStrategies.scaling(
                                   0),1000)
                   ).takeScreenshot(driver);
           String formatName =  CommonPropertyManager.getScreenshotsFormat().toUpperCase();
           File screenshotFile =  new File(screenshotFilePath);
            ImageIO.write(screenshot.getImage(), formatName, screenshotFile);
            byte[] fileContent = FileUtils.readFileToByteArray(screenshotFile);
            scenario.attach(fileContent, "image/jpeg", "Screenshot");
            screenshotFile.delete();
            driver.manage().window().setSize(new Dimension(width,height));
        }catch (IOException e){
            log.error("Capture Screenshot failed for scenario: {}; Exception: {}", scenario.getName(), e.getMessage());
        }
    }

    public static void captureScreenshot(WebDriver driver, Scenario scenario){
        Dimension currentDimension = driver.manage().window().getSize();
        int height = currentDimension.getHeight();
        int width = currentDimension.getWidth();
        Dimension screenshotDimension =  new Dimension(1920, 1080);
        driver.manage().window().setSize(screenshotDimension);
        FileUtil.createFileDirectory(CommonPropertyManager.getScreenshotsDir());
        try{
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/jpeg", "screenshot");
        }catch (Exception e){
           log.error("capture screenshot failed for scenario: {}; Exception: {}", scenario.getName(), e.getMessage());
        }
        driver.manage().window().setSize(new Dimension(width,height));
    }

}
