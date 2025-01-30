
# Web GUI Test Automation Project

This project automates the testing of web applications using Java, Selenium WebDriver, and TestNG. It follows best practices in test automation by implementing the Page Object Model (POM) pattern for better code maintainability and scalability.

## Features

- **Selenium WebDriver**: For automating web browsers and simulating user actions.
- **TestNG**: A testing framework to run tests and manage test configurations.
- **Page Object Model (POM)**: For better code organization and easier maintenance.
- **Allure Reports** for generating detailed and accessible test reports

## Prerequisites

- **Java 11** or later
- **Maven**
- **Selenium WebDriver**
- **TestNG**
- **IDE** (e.g., IntelliJ IDEA, Eclipse)

### Dependencies
The project uses the following libraries:

- **Selenium**: For web automation.
- **TestNG**: For test execution.
- **Allure**: For test reporting.

All dependencies are managed via Maven.

### Installing Allure on PC:
For Windows:
```sh
scoop install allure
```
For macOS:
```sh
brew install allure
```
For Linux:
```sh
sudo apt-add-repository ppa:qameta/allure
sudo apt update
sudo apt install allure
```

---

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/michaelwaheb/WebGUITestAutomationProject.git
   ```
2. Navigate to the project directory:
   ```bash
   cd WebGUITestAutomationProject
   ```
3. Install dependencies:
   ```bash
   mvn install
   ```

## Running the Tests

To run the tests using Maven:
```bash
mvn test
```

## Generating and Accessing Allure Reports

The project is configured to automatically generate Allure reports after running the tests. You don't need to manually run any additional commands for report generation. Once the tests are executed using Maven, the Allure report is available within the project directory.

To view the generated report, simply run the following command:

```bash
mvn allure:serve
