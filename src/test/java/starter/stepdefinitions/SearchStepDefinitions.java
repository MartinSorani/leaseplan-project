package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class SearchStepDefinitions {

    final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @When("user sends out a {string} request with parameter {string}")
    public void userSendsRequest(String method, String parameter) {
        if (Boolean.parseBoolean(CommonSteps.getProperty("logRequestToConsole")))
            RestAssured.requestSpecification.log().all();

        switch (method.toUpperCase()) {
            case "GET":
                SerenityRest
                        .given()
                        .spec(RestAssured.requestSpecification)
                        .get(parameter);
                break;
            case "POST":
                SerenityRest
                        .given()
                        .spec(RestAssured.requestSpecification)
                        .post(parameter);
                break;
            default:
                logger.error("ERROR: unsupported HTTP method");
        }

        if (Boolean.parseBoolean(CommonSteps.getProperty("logResponseToConsole")))
            SerenityRest.lastResponse().then().log().all();
    }

    @Then("the response returns HTTP status code {int}")
    public void statusCodeIs(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    @And("the response body contains the word {string}")
    public void userSeesResultsForProduct(String expectedText) {
        restAssuredThat(response -> response.body("title", hasItems(containsString(expectedText))));
    }

    @And("the response body contains error value {string} and message {string}")
    public void userCannotSeeResults(String errorValue, String message) {
        restAssuredThat(response -> response.body("detail.error", is(Boolean.parseBoolean(errorValue))));
        fieldContainsText("detail.message", message);
    }

    @And("the response body has a {string} field containing the word {string}")
    public void fieldContainsText(String fieldName, String text) {
        restAssuredThat(response -> response.body(fieldName, containsStringIgnoringCase(text)));
    }

}
