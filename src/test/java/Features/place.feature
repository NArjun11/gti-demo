Feature: Validating Add place Api
 
 @Addplace
 Scenario Outline: Verify whether the place is successfully added or not.
    Given Add place payload with "<name>" "<language>" "<address>"
    When  User calls "addPlaceApi" with "POST" http request
    Then  The api call get success with the status code 200
    And   "status" in the response body is "OK"
    And   "scope" in the response body is "APP"
    And   verify the place_id created maps to "<name>" using "getPlaceApi"
    
Examples:
     |name  | language | address|
     |suresh | English&Telugu   | India  |
     |Arjun  | Telugu           | Hyd  |
     
 
 @Deleteplace
  Scenario Outline: Verify whether the place is successfully Deleted or not.
    Given Delete the given api
    When  User calls "deletePlaceApi" with "Delete" http request
    Then  The api call get success with the status code 200
    And   "status" in the response body is "OK"
     
 