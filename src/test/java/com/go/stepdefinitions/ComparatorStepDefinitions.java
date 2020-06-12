package com.go.stepdefinitions;
import com.go.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ComparatorStepDefinitions {

    private final Task task;

    public ComparatorStepDefinitions(Task task){
        this.task = task;
    }

    @Given("there are two files that contain millions of request URLs separated by new line")
    public void thereAreFilesThatContainMillionsOfRequestURLsSeparatedByNewLine() {
        task.getTestFiles();

    }

    @When("James read the HTTP requests from the two files and compare the two responses")
    public void jamesReadTheHTTPRequestsFromTheTwoFilesAndCompareTheTwoResponses() {

      // task.parallelProcess();
        try {
            task.readTheHTTPRequestsFromTheTwoFilesAndCompareTheTwoResponses();
        } catch (Exception e)
        {
            System.out.println("Exception caught at readTheHTTPRequestsFromTheTwoFilesAndCompareTheTwoResponses");
        }

    }

    @Then("James is able to get the result of the comparison")
    public void jamesIsAbleToGetTheResultOfTheComparison() {
        task.displayFinalResultList();
    }
}
