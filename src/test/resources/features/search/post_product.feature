Feature: Search for the product

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/test/{product} for getting the products.
### Available products: "apple", "mango", "tofu", "water"
### Prepare Positive and negative scenarios

  Scenario Outline: User can see the results for every product
    Given a product API
    When user calls endpoint "<product>"
    Then user sees the results displayed for "<product>"

    Examples: Valid
            |product|
            |apple|
            |mango|
            |tofu|
            |water|
