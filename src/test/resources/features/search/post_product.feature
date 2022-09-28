Feature: Search for the product

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/test/{product} for getting the products.
### Available products: "apple", "mango", "tofu", "water"
### Prepare Positive and negative scenarios

  Background:
    Given I set the endpoint url to "/api/v1/search/test/"

  Scenario Outline: User can see the results for existing products
    When I execute an HTTP request with method "GET" and parameter "<product>"
    Then the response has HTTP status code 200
    And the response body has a "title" field containing the word "<product>"

    Examples: Valid
      | product |
      | apple   |
      | mango   |
      | tofu    |
      | water   |

  Scenario: User cannot see results for non-existent product
    When I execute an HTTP request with method "GET" and parameter "car"
    Then the response has HTTP status code 404
    And the response body contains error value "true" and message "Not found"

  Scenario: User cannot POST to endpoint
    When I execute an HTTP request with method "POST" and parameter "mango"
    Then the response has HTTP status code 405
    And the response body has a field "detail" with text "Method Not Allowed"
