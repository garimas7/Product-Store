# Product-Store
# Selenium WebDriver Automation Testing for Product Store

This repository contains the automation testing suite for the website [Demoblaze](https://www.demoblaze.com/), built using Selenium WebDriver with Java. The project is designed to demonstrate automated testing for functional and UI workflows using a variety of testing frameworks.

## About the Website
[Demoblaze](https://www.demoblaze.com/) is a demo e-commerce website that offers a range of products including laptops, phones, and other gadgets. It allows users to browse, view product details, add items to the cart, and complete the checkout process.

## Purpose of Testing
The primary purpose of this testing project is to validate the functionality of key features on the Demoblaze website. The goals are to ensure that all critical flows work as expected and to catch potential bugs before the website is deployed in production.

Key testing objectives include:
- Validating user login and registration process
- Adding items to the cart and checking out
- Ensuring smooth navigation and UI components functionality
- Testing form submissions (e.g., adding product details to cart, purchasing)

## Tech Stack Used
- **Selenium WebDriver**: For automating browser interactions
- **Page Object Model (POM)**: A design pattern to separate the page-specific code from the test scripts, enhancing maintainability and reusability
- **Cucumber**: For behavior-driven development (BDD) testing with feature files and step definitions
- **TestNG**: For managing test execution and generating reports
- **ExtentReports**: For generating detailed, customizable HTML test reports
- **Maven**: For project build and dependency management
- **Java**: The programming language used to implement the automation scripts

## Features
- Automated browser interactions (search, view product details, add to cart, checkout)
- BDD-style test scenarios using Cucumber
- Test suite management with TestNG
- Detailed HTML reports with ExtentReports
- Page Object Model architecture for better code organization

## Documentation
1. **Setup Instructions**: To run this project locally, follow these steps:
   - Clone the repository to your local machine:
     ```
     git clone https://github.com/yourusername/selenium-demoblaze.git
     ```
   - Navigate to the project directory:
     ```
     cd selenium-demoblaze
     ```
   - Install dependencies (Maven):
     ```
     mvn install
     ```
   - Run the tests using Maven or your preferred IDE:
     ```
     mvn test
     ```

2. **Running Specific Tests**: If you want to run specific Cucumber scenarios, you can do so using:

3. **Extent Report**: After running the tests, the Extent report will be generated in the `test-output` directory. Open `index.html` in a browser to view the test results.

## Contributing
Feel free to fork this repository and submit pull requests. For bug reports, enhancements, or questions, please open an issue in the repository.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
