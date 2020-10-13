# Selenium-project-framework
#Selenium framework Data Driven Test DDT  using Excel sheet -manven -TestNG -Page Object Model POM
for website "https://www.phptravels.net/register" to test signup functionality.

component project 
 1-you can see all page in src/main/java
 2-in src/test/java 
      you can see all tests
      can can property file "configuration file"
      Data (execl sheet reader and user data)
 #After run testcases from testng.xml
you make refresh on all project to see images for failed testcases in file "errorScreenshot" each image with name of 
test and paramteres for test.
and also you can see response for API test singup in file "Response".
in file "test-output" you can see all report which generate from testng in "emailable-report.html"
you can find all result for test cases and number of pass and failed

#limitations
using thread sleep in srcipt but give me exception org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element
  I try to use implicit wait or explicat wait but didn't work
