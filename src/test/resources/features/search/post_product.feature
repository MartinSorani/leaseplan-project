Feature: Search for the product

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/test/{product} for getting the products.
### Available products: "apple", "mango", "tofu", "water"
### Prepare Positive and negative scenarios

  Scenario:
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/test/apple"
    Then he sees the results displayed for apple
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/test/mango"
    Then he sees the results displayed for mango
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/test/car"
    Then he doesn not see the results
