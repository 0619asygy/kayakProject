package com.kayakProject.pages;

import com.kayakProject.utilities.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {


    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
}

    public String getPageTitle(){
        return Driver.getDriver().getTitle();

    }
}
