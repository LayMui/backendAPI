# Getting started with Backend API testing with Cucumber, Rest Assured BDD

### The project directory structure
The project use Maven as build tool
```
```

Steps taken: 
1. mkdir a project folder "backend_api"
2. run git init to create the .git to track changes
3. Use IntelliJIDEA to start a new Maven project
4. Add the relevant dependencies to the pom.xml 
5. Create IComparator Interface and IList interface, and implement IList at FileList at the application code main/java
6. Using BDD style, I create a feature file in Gherkin at resources/features
7. Create a cucumber test runner CucumberTestSuite that Cucumber will read from at test/java
8. Create a step definition file at test/java/com/go/stepdefinitions to glue the feature file to the code
9. Add the testdata files testfile1 and testfile2 at test/java/resources

Test Cases Approach:
1. Compare JsonObject: A JSONObject of {id: 1, name: 'B'} is equal to {name: 'B', id: 1}.
2. Compare JsonArray: Order of values is important
                      array of [1,'value'] is not the same as ['value',1]
3. Compare Embedded JsonObject: JsonArray can contains JsonObject  [1,{id: 1, name: 'B'}]             
4. Compare embedded JsonObject in value: {data: {id: 1, name: 'B'}} 
5. Compare embedded JsonArray within the JsonObject: {"page":123,"per_page":6,"total":12,"total_pages":2,"data":[]}
6. Compare JsonString: The order on serialization on strings are the same 

## Executing the tests

```
$  mvn verify 
```
