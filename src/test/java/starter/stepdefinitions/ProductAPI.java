package starter.stepdefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;

public class ProductAPI {
    private final String baseUrl = "https://waarkoop-server.herokuapp.com/api/v1/search/test/";

    public ProductAPI() {}

    public String getBaseUrl() {
        return this.baseUrl;
    }

    @Step("Create request specification")
    public RequestSpecification defaultSpec() {
        return new RequestSpecBuilder().setBaseUri(baseUrl)
                .setContentType("application/json")
                .build();
    }
}
