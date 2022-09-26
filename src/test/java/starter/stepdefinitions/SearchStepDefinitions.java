package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @When("user GETS endpoint {string}")
    public void userGetsEndpoint(String product) {
        SerenityRest
                .given()
                .spec(productAPI.defaultSpec())
                .get(product);
    }

    @When("user POSTS endpoint {string}")
    public void userPostsEndpoint(String product) {
        SerenityRest
                .given()
                .spec(productAPI.defaultSpec())
                .post(product);
    }

    @Then("user receives status code {int}")
    public void statusCodeIs(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    @And("user sees the results displayed for {string}")
    public void userSeesResultsForProduct(String expected) {
        restAssuredThat(response -> response.body("title", hasItems(containsString(expected))));
    }

    @And("user cannot see any results")
    public void userCannotSeeResults() {
        restAssuredThat(response -> response.body("detail.error", is(true)));
        restAssuredThat(response -> response.body("detail.message", containsStringIgnoringCase("Not found")));
    }

    @And("user receives method not allowed message")
    public void userReceivesMethodNotAllowed() {
        restAssuredThat(response -> response.body("detail", containsStringIgnoringCase("Method Not Allowed")));
    }
}
