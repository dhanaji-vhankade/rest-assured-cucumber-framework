Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using addPlace API

Given Add place payload with "<name>" "<language>" "<address>"
When User call "AddPlaceAPI" with "POST" http request
Then The API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "GetPlaceAPI"

Examples:
|name    | language  | address |
|Sachin  | Marathi   | Mumbai  |


@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
Given DeletePlace payload
When User call "DeleteAPI" with "POST" http request
Then The API call got success with status code 200
And "status" in response body is "OK"

