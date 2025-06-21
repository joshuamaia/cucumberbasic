Feature: Category registration

  Scenario: Registering three categories at once
    Given there are no categories registered
    When I register the following categories
      | name        | description         |
      | Electronics | All about tech      |
      | Clothes     | General clothing    |
      | Books       | Various literature  |
    Then the category list should contain 3 categories

  Scenario: Test REST API
    Given there are no categories registered
    When I send the following categories via API
      | name   | description        |
      | Test1  | Test category      |
      | Test2  | Another category   |
    Then the API should return 2 categories

  Scenario: Invalid category registration
    When I try to register an invalid category
    Then the system should reject with status 400
