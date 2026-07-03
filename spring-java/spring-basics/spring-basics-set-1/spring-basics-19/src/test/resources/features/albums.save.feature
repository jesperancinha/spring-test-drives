Feature: Album Filter

  Scenario: REM albums
    Given a lists of artists
    And with album: Fables of The reconstruction and artist: REM published by: I.R.S in 1985
    And with album: Broadcast and artist: Cutting Crew published by: Virgin in 1986
    When I search for artist: REM
    Then the result is 1 records!