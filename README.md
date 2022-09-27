<br />
<p align="center">
  <a href="https://gitlab.com/MartinSorani/miro.testproject">
    <img src=images/leaseplan-logo-full.png alt="Logo">
  </a>

<h3 align="center">Leaseplan assessment project</h3>

  <p align="center">
    Short test assessment project
  </p>

## Table of Contents

* [Assignment requirements](#requirements-for-this-assignment)
    * [Built With](#built-with)
* [Prerequisites](#prerequisites)
    * [Running tests locally](#running-tests-locally)
    * [Running tests from Gitlab CI/CD](#running-tests-from-Gitlab)
* [Refactor](#refactor)
* [CI/CD](#cicd)
* [Test scenarios](#test-scenarios)
* [Contact](#contact)


## Requirements for this assignment
* Refactor the given project
* Make it run correctly on Gitlab CI
* Use BDD format: Cucumber/Gherkin
* Required framework: Java Serenity + Maven

You need to:
* Automate the endpoints inside a working CI/CD pipeline to test the service
* Cleanup the project and implement it in the proper way
* Cover at least 1 positive and 1 negative scenario
* Write instructions in Readme file on how to install, run and write new tests
* Briefly write in Readme what was refactored and why
* Upload your project to Gitlab and make sure that CI/CD is configured
* Set Html reporting tool with the test results of the pipeline test run
* IMPORTANT!!! Provide us with a Gitlab link to your repository

### The project directory structure
This project follows a classic Maven structure. Notice however that action classes are not defined in the main folder so as to not contradict Serenity's recommended pattern, and since this is an api test suite, no page objects are declared inside the main folder either.\
In the end, the final artifact is built from the classes defined inside the test section.

📦src\
┣ 📂main\
┃ ┗ 📂java\
┃ ┃ ┗ 📂stater\
┃ ┃ ┃ ┗ 📜README.md\
┗ 📂test\
┃ ┣ 📂java\
┃ ┃ ┗ 📂starter\
┃ ┃ ┃ ┣ 📂stepdefinitions\
┃ ┃ ┃ ┃ ┣ 📜ProductAPI.java\
┃ ┃ ┃ ┃ ┗ 📜SearchStepDefinitions.java            
┃ ┃ ┃ ┗ 📜TestRunner.java\
┃ ┗ 📂resources\
┃ ┃ ┣ 📂features\
┃ ┃ ┃ ┗ 📂search\
┃ ┃ ┃ ┃ ┗ 📜post_product.feature\
┃ ┃ ┣ 📜logback-test.xml

### Built With

* [Apache Maven](https://maven.apache.org)
* [Junit 5](https://junit.org/junit5/)
* [Java](https://www.java.com/en/)
* [Serenity](https://github.com/serenity-bdd)
* [Cucumber](https://cucumber.io/docs/installation/java/)

### Prerequisites

This project requires Java 8+ and Apache Maven 3+ to run.
Download and install java for your OS from [https://www.java.com/en/](https://www.java.com/en/).
Verify your Java installation by running the command

`java -version`
~~~
java version "19" 2022-09-20
Java(TM) SE Runtime Environment (build 19+36-2238)
Java HotSpot(TM) 64-Bit Server VM (build 19+36-2238, mixed mode, sharing)
~~~

Download Maven from [https://maven.apache.org](https://maven.apache.org) and follow the instructions for installation.
Verify your Maven installation by running

`mvn -v`
~~~
Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
Maven home: E:\Program Files\apache-maven-3.8.5
Java version: 1.8.0_181, vendor: Oracle Corporation, runtime: E:\Program Files\Java\jdk1.8.0_181\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
~~~

### Running tests locally

To get a local copy up and running follow these steps:

1. Clone the repo
```sh
git clone git@gitlab.com:MartinSorani/leaseplan-project.git
```
2. Run the tests
```sh
mvn clean verify
```

3. An .html version of the report is created inside the folder `\target\site\serenity` with the name of `index.html`

<img src=images/report_sample.png height="350" width="600">

### Running tests from Gitlab

Go to the [pipeline](https://gitlab.com/MartinSorani/leaseplan-project/-/pipelines) section

1. Click the 'Run Pipeline' button 

<img src=images/pipeline_step_1.png>

2. Select the **master** branch and click 'Run pipeline'

<img src=images/pipeline_step_2.png height="250">

3. Access the report generated inside the test step

<img src=images/pipeline_step_3.png height="250">

4. Access the artifacts

<img src=images/pipeline_step_4.png height="250">

5. Open the `public` folder

<img src=images/pipeline_step_5.png height="250">

6. Open the `index.html` file

<img src=images/pipeline_step_6.png height="250">

## Refactor

Refactoring done on the original project is described in the file [`src/main/java/starter/Refactor.md`](src/main/java/starter/Refactor.md)

## CI/CD

All CI/CD steps are automated in a [Gitlab pipeline](https://gitlab.com/MartinSorani/leaseplan-project/-/pipelines) and built with Maven.\
Deployment is done only when pushing against the `master` branch and the final artifact is published to a Maven repository hosted by Artifactory.

<img src=images/artifactory_screenshot.png>


## Test scenarios

**Scenario:** User can see the results for every product\
**Given** a product API\
**When** user GETS endpoint _mango_\
**Then** user receives status code 200\
**And** user sees the results displayed for _mango_

**Scenario:** User cannot see results for non-existent product\
**Given** a product API\
**When** user GETS endpoint _car_\
**Then** user receives status code 404\
**And** user cannot see any results\

**Scenario**: User cannot POST to endpoint\
**Given** a product API\
**When** user POSTS endpoint _mango_\
**Then** user receives status code 405\
**And** user receives method not allowed message

## Contact

Martin Sorani - [martin.sorani@gmail.com](mailto:martin.sorani@gmail.com)