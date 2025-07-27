#language: en
Feature: Myntra Product Purchase Flow

  Scenario: User searches for a product and successfully adds it to the bag
    Given I am on the Myntra homepage
    When I search for "shirts"
    And I click on the second product from the search results
    And I switch to the new tab
    And I select a size for the product
    And I click on "ADD TO BAG"
    And I go to the Shopping Bag
    Then I should be on the Shopping Bag page
