# Tests-for-Calkoo


My task was to document test cases for a VAT Calculator API (https://www.calkoo.com/en/vat-calculator), 
and document bugs if I encounter any. Also I had to choose at least two cases to be automatized.

I chose googlesheets for the manual test case writing
and I created the test scenarios following the requirements. Each scenario is being presented on a different sheet (color blue).
On these sheets you will find the function relevant testcases.
There is one sheet for the Bug Reports and one for for the Questions and Remarks whenever I had any.

https://docs.google.com/spreadsheets/d/1yySYawrOHOJCmVYZYN-6y5LNCLjiRvpPE_WYHzff1UQ/edit#gid=2122601764

For test automation I created a Maven POM Java project. I used JUnit for testcase writing and Selenium Webdriver for browser automation.
For excecuting the tests run the "mvn test" command from the terminal. 
