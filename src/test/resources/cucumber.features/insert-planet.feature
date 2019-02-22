Feature: API to insert a StarWars planet

  Scenario Outline: Insert a new planet that does not exists within local database but exists within StarWars API
    Given the database is empty
    When call the insertion API with POST method "/api/planets" with given JSON body:
      """
      {
        "name": "<name>",
        "weather": "<weather>",
        "terrain": "<terrain>"
      }
      """
    Then the insertion API should return HTTP status code: <httpStatusCode>
    And the planet should be inserted into database
    And the planet inserted should look like:
      | name   | weather   | terrain   | views   |
      | <name> | <weather> | <terrain> | <views> |
    Examples:
      | name     | weather   | terrain   | views | httpStatusCode |
      | Alderaan | temperate | mountains | 2     | 200            |
      | Naboo    | temperate | forests   | 4     | 200            |

  Scenario: Insert a new planet that does not exists within local database and within StarWars API
    When call the insertion API with POST method "/api/planets" with given JSON body:
      """
      {
        "name": "Acme",
        "weather": "thunderstorm",
        "terrain": "mountains"
      }
      """
    Then the insertion API should return HTTP status code: 200
    And the planet inserted should look like:
      | name | weather      | terrain   | views |
      | Acme | thunderstorm | mountains | 0     |

  Scenario: Insert a planet that already exists within local database
    When call the insertion API with POST method "/api/planets" with given JSON body:
      """
      {
        "name": "Alderaan",
        "weather": "thunderstorm",
        "terrain": "mountains"
      }
      """
    Then the insertion API should return HTTP status code: 400
