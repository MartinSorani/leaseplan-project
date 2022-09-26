package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class SearchStepDefinitions {

    @Steps
    public ProductAPI productAPI = new ProductAPI();

    @Given("a product API")
    public void setEndpoint() {
        productAPI = new ProductAPI();
    }

    @When("user calls endpoint {string}")
    public void userCallsEndpoint(String product) {
        SerenityRest
                .given()
                .spec(productAPI.defaultSpec())
                .get(product);
    }

    @Then("user sees the results displayed for {string}")
    public void userSeesResultsForProduct(String expected) {
        restAssuredThat(response -> response.statusCode(200));
        restAssuredThat(response -> response.body("title", hasItems(containsString(expected))));
    }

    @Then("user cannot see any results")
    public void userCannotSeeResults() {
        restAssuredThat(response -> response.body("detail.error", contains("true")));
    }
}
