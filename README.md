This repository houses an end-to-end test automation framework built with **Java**, **Playwright**, **Maven**, **TestNG**,
**Allure Reporting**, and **GitHub Actions CI/CD**.

## Overview

This framework enables UI automated tests with the following features:

- Java + Playwright for browser automation
- Maven for dependency management
- TestNG as the test runner
- Allure for rich, interactive reports
- Automated CI via GitHub Actions

## Setup & Installation

1. Clone the repo:
   ```bash
   git clone https://github.com/vidtv/swaglabs-ui-tests.git
   cd swaglabs-ui-tests
    ```

2. Install dependencies via Maven:

   ```bash
   mvn clean install
    ```

3. Ensure Playwright dependencies are installed (e.g., npx playwright install or Maven plugin).

## Allure Reports

To generate and view an Allure report locally:

   ```bash
   mvn allure:serve
   ```

## CI/CD via GitHub Actions

A GitHub workflow (e.g., `.github/workflows/playwright-tests.yml`) is configured to:

- Run tests automatically on each pull request  
- Collect Allure results and serve or publish them (e.g., to GitHub Pages or as artifacts)  

## Project Structure

Typical layout:

```text
.
├── .github/workflows/playwright-tests.yml
├── pom.xml
├── README.md
└── src/
├── main/
│   └── java/            # Page Objects, utilities
└── test/
    ├── java/            # Test classes
    └── resources/       # testng.xml, allure.properties, etc.
```

## Live Test Report
For the latest test results, visit the [Allure Report](https://vidtv.github.io/swaglabs-ui-tests/).