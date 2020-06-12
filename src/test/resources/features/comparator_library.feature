Feature:  Create a comparator library that can be used to compare 2 API responses (HTTP/HTTPS)
  In order to compare two API responses
  As a tester
  I want to be able to create a comparator library to compare two API responses

  Background:
    Given there are two files that contain millions of request URLs separated by new line


  Scenario: Create a comparator library to compare response of two URLs requests
  In order to compare two API responses of the URLs read from two files
  As a tester James
  James wants to able to read the HTTP request from the two files to compare the response
    When James read the HTTP requests from the two files and compare the two responses
    Then James is able to get the result of the comparison
