package com.kayakProject.step_definitions;

import com.kayakProject.pages.FlightsPage;
import com.kayakProject.utilities.utilities.ConfigurationReader;
import com.kayakProject.utilities.utilities.Driver;
import io.cucumber.java.en.*;

public class FlightsStepDefinitions {
    FlightsPage flightsPage = new FlightsPage();


    @Given("user is on the flights page")
    public void user_is_on_the_flights_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

    }

    @Then("user enters {string} for departure")
    public void user_enters_for_departure(String cityName) {
        flightsPage.clickXIcon();
        flightsPage.sendDepartureInput(cityName);

    }

    @Then("user enters {string} for destination")
    public void user_enters_for_destination(String destination) {
        flightsPage.sendDestinationInput(destination);


    }

    @Then("user picks a date as {string} for departure")
    public void user_picks_a_date_as_for_departure(String date) {
        flightsPage.clickStartDateButton();
        flightsPage.pickStartDate(date);

    }

    @Then("user picks a date as {string} for return")
    public void user_picks_a_date_as_for_return(String string) {

    }

    @Then("user clicks on search button")
    public void user_clicks_on_search_button() {

    }


}
