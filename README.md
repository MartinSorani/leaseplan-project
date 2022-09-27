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
* [Configuration](#configuration)
* [Output](#output)
* [Reporting](#reporting)
* [Test suite documentation](#test-suite-documentation)
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

This project requires Java 8+ and Apache Maven to run.
Download and install java for your OS from [https://www.java.com/en/](https://www.java.com/en/).
Verify your Java installation by running the command

`java -version`
~~~
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
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

### Running tests from Gitlab

Go to the [pipeline](https://gitlab.com/MartinSorani/leaseplan-project/-/pipelines) section

1. Click the 'Run Pipeline' button 

<img src=images/pipeline_step_1.png>

2. Select the **master** branch and click 'Run pipeline'

<img src=images/pipeline_step_2.png>

2. Access the report at

## The sample scenario
Both variations of the sample project uses the sample Cucumber scenario. In this scenario, Sergey (who likes to search for stuff) is performing a search on the internet:

```Gherkin
Feature: Search by keyword

  Scenario: Searching for a term
    Given Sergey is researching things on the internet
    When he looks up "Cucumber"
    Then he should see information about "Cucumber"
```

### The Screenplay implementation
The sample code in the master branch uses the Screenplay pattern. The Screenplay pattern describes tests in terms of actors and the tasks they perform. Tasks are represented as objects performed by an actor, rather than methods. This makes them more flexible and composable, at the cost of being a bit more wordy. Here is an example:
```java
    @Given("{actor} is researching things on the internet")
    public void researchingThings(Actor actor) {
        actor.wasAbleTo(NavigateTo.theWikipediaHomePage());
    }

    @When("{actor} looks up {string}")
    public void searchesFor(Actor actor, String term) {
        actor.attemptsTo(
                LookForInformation.about(term)
        );
    }

    @Then("{actor} should see information about {string}")
    public void should_see_information_about(Actor actor, String term) {
        actor.attemptsTo(
                Ensure.that(WikipediaArticle.HEADING).hasText(term)
        );
    }
```

Screenplay classes emphasise reusable components and a very readable declarative style, whereas Lean Page Objects and Action Classes (that you can see further down) opt for a more imperative style.

The `NavigateTo` class is responsible for opening the Wikipedia home page:
```java
public class NavigateTo {
    public static Performable theWikipediaHomePage() {
        return Task.where("{0} opens the Wikipedia home page",
                Open.browserOn().the(WikipediaHomePage.class));
    }
}
```

The `LookForInformation` class does the actual search:
```java
public class LookForInformation {
    public static Performable about(String searchTerm) {
        return Task.where("{0} searches for '" + searchTerm + "'",
                Enter.theValue(searchTerm)
                        .into(SearchForm.SEARCH_FIELD)
                        .thenHit(Keys.ENTER)
        );
    }
}
```

In Screenplay, we keep track of locators in light weight page or component objects, like this one:
```java
class SearchForm {
    static Target SEARCH_FIELD = Target.the("search field")
                                       .locatedBy("#searchInput");

}
```

The Screenplay DSL is rich and flexible, and well suited to teams working on large test automation projects with many team members, and who are reasonably comfortable with Java and design patterns. 

### The Action Classes implementation.

A more imperative-style implementation using the Action Classes pattern can be found in the `action-classes` branch. The glue code in this version looks this this:

```java
    @Given("^(?:.*) is researching things on the internet")
    public void i_am_on_the_Wikipedia_home_page() {
        navigateTo.theHomePage();
    }

    @When("she/he looks up {string}")
    public void i_search_for(String term) {
        searchFor.term(term);
    }

    @Then("she/he should see information about {string}")
    public void all_the_result_titles_should_contain_the_word(String term) {
        assertThat(searchResult.displayed()).contains(term);
    }
```

These classes are declared using the Serenity `@Steps` annotation, shown below:
```java
    @Steps
    NavigateTo navigateTo;

    @Steps
    SearchFor searchFor;

    @Steps
    SearchResult searchResult;
```

The `@Steps`annotation tells Serenity to create a new instance of the class, and inject any other steps or page objects that this instance might need.

Each action class models a particular facet of user behaviour: navigating to a particular page, performing a search, or retrieving the results of a search. These classes are designed to be small and self-contained, which makes them more stable and easier to maintain.

The `NavigateTo` class is an example of a very simple action class. In a larger application, it might have some other methods related to high level navigation, but in our sample project, it just needs to open the DuckDuckGo home page:
```java
public class NavigateTo {

    WikipediaHomePage homePage;

    @Step("Open the Wikipedia home page")
    public void theHomePage() {
        homePage.open();
    }
}
```

It does this using a standard Serenity Page Object. Page Objects are often very minimal, storing just the URL of the page itself:
```java
@DefaultUrl("https://wikipedia.org")
public class WikipediaHomePage extends PageObject {}
```

The second class, `SearchFor`, is an interaction class. It needs to interact with the web page, and to enable this, we make the class extend the Serenity `UIInteractionSteps`. This gives the class full access to the powerful Serenity WebDriver API, including the `$()` method used below, which locates a web element using a `By` locator or an XPath or CSS expression:
```java
public class SearchFor extends UIInteractionSteps {

    @Step("Search for term {0}")
    public void term(String term) {
        $(SearchForm.SEARCH_FIELD).clear();
        $(SearchForm.SEARCH_FIELD).sendKeys(term, Keys.ENTER);
    }
}
```

The `SearchForm` class is typical of a light-weight Page Object: it is responsible uniquely for locating elements on the page, and it does this by defining locators or occasionally by resolving web elements dynamically.
```java
class SearchForm {
    static By SEARCH_FIELD = By.cssSelector("#searchInput");
}
```

The last step library class used in the step definition code is the `SearchResult` class. The job of this class is to query the web page, and retrieve a list of search results that we can use in the AssertJ assertion at the end of the test. This class also extends `UIInteractionSteps` and
```java
public class SearchResult extends UIInteractionSteps {
    public String displayed() {
        return find(WikipediaArticle.HEADING).getText();
    }
}
```

The `WikipediaArticle` class is a lean Page Object that locates the article titles on the results page:
```java
public class WikipediaArticle {
    public static final By HEADING =  By.id("firstHeading");
}
```

The main advantage of the approach used in this example is not in the lines of code written, although Serenity does reduce a lot of the boilerplate code that you would normally need to write in a web test. The real advantage is in the use of many small, stable classes, each of which focuses on a single job. This application of the _Single Responsibility Principle_ goes a long way to making the test code more stable, easier to understand, and easier to maintain.

## Executing the tests
To run the sample project, you can either just run the `CucumberTestSuite` test runner class, or run either `mvn verify` or `gradle test` from the command line.

By default, the tests will run using Chrome. You can run them in Firefox by overriding the `driver` system property, e.g.
```json
$ mvn clean verify -Ddriver=firefox
```
Or
```json
$ gradle clean test -Pdriver=firefox
```

The test results will be recorded in the `target/site/serenity` directory.

## Generating the reports
Since the Serenity reports contain aggregate information about all of the tests, they are not generated after each individual test (as this would be extremenly inefficient). Rather, The Full Serenity reports are generated by the `serenity-maven-plugin`. You can trigger this by running `mvn serenity:aggregate` from the command line or from your IDE.

They reports are also integrated into the Maven build process: the following code in the `pom.xml` file causes the reports to be generated automatically once all the tests have completed when you run `mvn verify`?

```
             <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.maven.version}</version>
                <configuration>
                    <tags>${tags}</tags>
                </configuration>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

## Simplified WebDriver configuration and other Serenity extras
The sample projects both use some Serenity features which make configuring the tests easier. In particular, Serenity uses the `serenity.conf` file in the `src/test/resources` directory to configure test execution options.  
### Webdriver configuration
The WebDriver configuration is managed entirely from this file, as illustrated below:
```java
webdriver {
    driver = chrome
}
headless.mode = true

chrome.switches="""--start-maximized;--test-type;--no-sandbox;--ignore-certificate-errors;
                   --disable-popup-blocking;--disable-default-apps;--disable-extensions-file-access-check;
                   --incognito;--disable-infobars,--disable-gpu"""

```

Serenity uses WebDriverManager to download the WebDriver binaries automatically before the tests are executed.

### Environment-specific configurations
We can also configure environment-specific properties and options, so that the tests can be run in different environments. Here, we configure three environments, __dev__, _staging_ and _prod_, with different starting URLs for each:
```json
environments {
  default {
    webdriver.base.url = "https://duckduckgo.com"
  }
  dev {
    webdriver.base.url = "https://duckduckgo.com/dev"
  }
  staging {
    webdriver.base.url = "https://duckduckgo.com/staging"
  }
  prod {
    webdriver.base.url = "https://duckduckgo.com/prod"
  }
}
```

You use the `environment` system property to determine which environment to run against. For example to run the tests in the staging environment, you could run:
```json
$ mvn clean verify -Denvironment=staging
```

See [**this article**](https://johnfergusonsmart.com/environment-specific-configuration-in-serenity-bdd/) for more details about this feature.

## Want to learn more?
For more information about Serenity BDD, you can read the [**Serenity BDD Book**](https://serenity-bdd.github.io/theserenitybook/latest/index.html), the official online Serenity documentation source. Other sources include:
* **[Byte-sized Serenity BDD](https://www.youtube.com/channel/UCav6-dPEUiLbnu-rgpy7_bw/featured)** - tips and tricks about Serenity BDD
* For regular posts on agile test automation best practices, join the **[Agile Test Automation Secrets](https://www.linkedin.com/groups/8961597/)** groups on [LinkedIn](https://www.linkedin.com/groups/8961597/) and [Facebook](https://www.facebook.com/groups/agiletestautomation/)
* [**Serenity BDD Blog**](https://johnfergusonsmart.com/category/serenity-bdd/) - regular articles about Serenity BDD
