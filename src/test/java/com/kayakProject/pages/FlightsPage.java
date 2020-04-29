package com.kayakProject.pages;

import com.kayakProject.utilities.utilities.BrowserUtils;
import com.kayakProject.utilities.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightsPage  extends  BasePage{

    @FindBy(xpath = "//*[contains(@class,'js-remove-selection')]")
    private WebElement xIcon;

    @FindBy(xpath = "//input[@aria-label='Flight origin input']")
    private WebElement originBox;

    @FindBy(xpath = "//input[@aria-label='Flight destination input']")
    private WebElement destinationBox;

    @FindBy(xpath = "//div[contains(@id,'dateRangeInput-display-start')]/div")
    private WebElement startDateButton;

    @FindBy(xpath = "//div[text()='May 2020']/parent::div/div[3]/div[2]/div[5]")
    public WebElement startDateInput;


    public void clickXIcon(){
        BrowserUtils.waitForClickablility(xIcon,5);
        xIcon.click();
    }
    public void sendDepartureInput(String cityName){
        originBox.sendKeys(cityName, Keys.ENTER);
    }
    public void sendDestinationInput(String cityName){
        BrowserUtils.wait(3);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value','"+cityName+"')",destinationBox);
        js.executeScript("arguments[0].click()",destinationBox);
    }

    public void clickStartDateButton(){
        startDateButton.click();
    }

    public void pickStartDate(String startDate){
        startDateInput.sendKeys(startDate);
    }
}
