Feature: Search for the product

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/test/{product} for getting the products.
### Available products: "apple", "mango", "tofu", "water"
### Prepare Positive and negative scenarios

  Background:
    Given I set the endpoint url to "/api/v1/search/test/"

  Scenario Outline: User can see the results for existing products
    When user sends out a "GET" request with parameter "<product>"
    Then the response returns HTTP status code 200
    And the response body has a "title" field containing the word "<product>"

    Examples: Valid
            |product|
            |apple|
            |mango|
            |tofu|
            |water|

  Scenario: User cannot see results for non-existent product
    When user sends out a "GET" request with parameter "car"
    Then the response returns HTTP status code 404
    And the response body contains error value "true" and message "Not found"

  Scenario: User cannot POST to endpoint
    When user sends out a "POST" request with parameter "mango"
    Then the response returns HTTP status code 405
    And the response body has a field "detail" with text "Method Not Allowed"