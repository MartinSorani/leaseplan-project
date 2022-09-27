### Refactoring done
 
* Removed all Gradle references and config files as the requirement indicated build was to be done with Maven.
* Removed Github workflow script for Gradle. Updated workflow script for Maven.
* Updated Cucumber, Serenity and JUnit dependencies
* Added surefire configuration and compiler dependencies
* Added Serenity html reporter support
* Added API action class and definitions
* Fixed step definitions
* Updated step definitions to allow for parameterization
* Fixed existing test scenario's assertions
* Added positive test cases
* Added negative test cases
* Updated documentation
* Updated Artifactory configuration to support artifact upload
* Created Gitlab CI/CD script
* Linked Gitlab CI/CD pipeline with Artifactory maven repository