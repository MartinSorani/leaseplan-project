Feature: Search for the product

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/test/{product} for getting the products.
### Available products: "apple", "mango", "tofu", "water"
### Prepare Positive and negative scenarios

  Scenario Outline: User can see the results for every product
    Given a product API
    When user GETS endpoint "<product>"
    Then user receives status code 200
    And user sees the results displayed for "<product>"

    Examples: Valid
            |product|
            |apple|
            |mango|
            |tofu|
            |water|

  Scenario: User cannot see results for non existent product
    Given a product API
    When user GETS endpoint "<car>"
    Then user receives status code 404
    And user cannot see any results

  Scenario: User cannot POST to endpoint
    Given a product API
    When user POSTS endpoint "mango"
    Then user receives status code 405
    And user receives method not allowed message
