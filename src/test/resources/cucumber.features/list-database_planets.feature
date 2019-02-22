Feature: API to list all planets within local database

  Background:
    Given the following planets exist within local database
      | id | name     | weather   | terrain   | views |
      | 1  | Alderaan | temperate | mountains | 2     |
      | 2  | Naboo    | temperate | forests   | 4     |

  Scenario: List all planets that exist within local database
    When call the list API with GET method "/api/planets"
    Then the list API should return HTTP status code: 200
    And the list of all planets that exist within local database should look like:
      | id | name     | weather   | terrain   | views | created             | updated |
      | 1  | Alderaan | temperate | mountains | 2     | 2019-02-20T09:00:00 | null    |
      | 2  | Naboo    | temperate | forests   | 4     | 2019-02-20T09:00:00 | null    |

  Scenario: List an existing planet by id from local database
    When call the get by id API with GET method "/api/planets/%s"
    Then the list API should return HTTP status code: 200
    And the returned planet from local database should look like:
      | id | name     | weather   | terrain   | views | created             | updated |
      | 1  | Alderaan | temperate | mountains | 2     | 2019-02-20T09:00:00 | null    |

  Scenario: List an existing planet by name from local database
    When call the get by name API with GET method "/api/planets/planet?name=Alderaan"
    Then the list API should return HTTP status code: 200
    And the returned planet from local database should look like:
      | id | name     | weather   | terrain   | views | created             | updated |
      | 1  | Alderaan | temperate | mountains | 2     | 2019-02-20T09:00:00 | null    |

  Scenario: List a non existing planet by name from local database
    When call the get by name API with GET method "/api/planets/planet?name=Duff"
    Then the list API should return HTTP status code: 404

  Scenario: List a non existing planet by id from local database
    When call the get by name API with GET method "/api/planets/9999"
    Then the list API should return HTTP status code: 404
