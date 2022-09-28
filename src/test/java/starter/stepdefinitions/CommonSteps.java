package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;

public class CommonSteps {

    private static EnvironmentVariables environmentVariables;

    public static String getProperty(String propertyName) {
        return EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty(propertyName);
    }

    @Given("I set the endpoint url to {string}")
    public void setEndpoint(String endpointUrl) {
        RestAssured.requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("baseUrl") + endpointUrl)
                .setContentType("application/json")
                .build();
    }


}
