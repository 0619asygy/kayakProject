package com.kayakProject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightsPage  extends  BasePage{

    @FindBy(xpath = "//*[contains(@class,'js-remove-selection')]")
    public WebElement xIcon;

    @FindBy(xpath = "//input[@aria-label='Flight origin input']")
    public WebElement originBox;

    @FindBy(xpath = "//input[@aria-label='Flight destination input']")
    public WebElement destinationBox;

    @FindBy(xpath = "//div[@id='cP4i-dateRangeInput-display-start']")
    public WebElement startDateButton;

}
