Feature: Validate Place APIs
@AddPlace @Regression
Scenario Outline: Verify add place is added
	Given Add Place Payload with "<name>"  "<language>" "<address>"
	When User calls "AddPlaceAPI" with "Post" http request
	Then "Status Code" result is "200"
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify place_id created maps to "<name>" using "GetPlaceAPI"
	
Examples:
	|name 	 | language |address		   |
	|AAhouse |  English |World cross center|
#	|BBhouse |  French 	|Int cross center	|

@DeletePlace @Regression
Scenario: Verify delete place is deleted
	Given Delete Place Payload with "<name>"  "<language>" "<address>"
	When User calls "DeletePlaceAPI" with "Post" http request
	Then "Status Code" result is "200"
	And "status" in response body is "OK"
