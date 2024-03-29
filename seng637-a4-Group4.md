**SENG 637 - Dependability and Reliability of Software Systems**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group 4:      |    |
| -------------- | --- |
| Student 1 Names:Arpita Chowdhury | |
| Student 2 Fadila Abdulai Hamid             |   
| Student 3 Kumkum Akter             |   
| Student 4 Niloofar Sharifisadr              |
| Student 5 Pratishtha Pratishtha      |     |
|                |     |
|                |     |

# Introduction
In this assignment, we will explore mutation testing with the help of the Pitest eclipse plugin to see how good our test suite is at catching bugs. Then, we will try to improve our test suite by adding more test cases which would increase our mutation score by at least 10%.
This assignment comprises two parts, in the first phase, we aim to increase the mutation coverage score by writing new test cases and using the test cases from the project's last phase. For this purpose, we used the Mutation Testing approach. This method is a type of software testing in which certain statements of the source code are changed to check if the test cases can find errors in the source code or not. We used the Pitest tool, an automated mutation testing system, which could produce different mutated programs, to run our test cases, and report the mutation coverage report. It also provides a log about which mutation is detected by our test cases (killed) and which survived. The system under test (SUT) is called JFreeChart, a library for creating professional charts. It is an open-source Java framework for chart calculation, creation, and display. JFreeChart makes it easy for developers to display professional quality charts in their applications.
First, we analyze ten mutations by reading the mutation report log, and we explain which of them were killed by which original test case and how it is killed. Then, we report some equivalence mutations and try to detect them automatically. After that, we design the new test cases to increase the mutation coverage report. Then, we highlight some of the advantages and disadvantages of mutation testing.
In the second phase of this assignment, we use Selenium IDE, a framework for testing web applications, to understand automated Graphical User Interface (GUI) testing and some of the features that record and replay test cases. GUI testing is the process of ensuring the proper functionality of the GUI for a specific application. We chose the Shop Smart Canada page as our SUT after familiarizing ourselves with the selenium tool extension in Chrome, we implemented test cases for ten different functionalities.

# Analysis of 10 Mutants of the Range class 
1. Mutation: Removed conditional - replaced comparison check with true in contains method.
Effect: The mutation changes the method to always return true, disregarding the actual checks to determine if a value is within the range.
Outcome: Killed. The test suite contains cases that check values outside and within the range, ensuring the method's logic is verified. When the mutation forces a constant true return, it contradicts the expected outcomes for out-of-range values, leading to the mutation being effectively countered by the existing tests.
![image](https://github.com/jui-kumkum/SENG637_Assignment4_Group4/assets/17180836/b133ad28-d0bc-46e6-976d-19f07b19fc29)

2. Mutation: Decremented (--a) double local variable number one in contains method.
Effect: This mutation would decrement the first double local variable, which could alter the comparison logic if such a variable were used. However, in this method, local variables are not directly manipulated this way; the method checks if the input value is within the specified range.
Outcome: Survived. Since the method does not utilize mutable local double variables whose decrementation would impact the return value of contains, this mutation does not affect the method's correctness with respect to the test cases provided. Consequently, the tests do not cover a scenario where such a change would lead to a different outcome, allowing the mutation to survive.
![image](https://github.com/jui-kumkum/SENG637_Assignment4_Group4/assets/17180836/658d4c68-82e9-4d07-bbd0-19c4afa5eb61)

# Report all the statistics and the mutation score for each test class
## Range.ExpandToinclude PIT Summery before
![image](https://github.com/jui-kumkum/SENG637_Assignment4_Group4/blob/main/Images/ExpandtoIncludeBEFORE/PIT%20summery%20before(rangeExpandToinclude).png)



## Range.ExpandToinclude PIT Mutation before
![image](https://github.com/jui-kumkum/SENG637_Assignment4_Group4/blob/main/Images/ExpandtoIncludeBEFORE/PIT%20Mutation(rangeExpandToinclude).png)




## Range.ExpandToinclude PIT Summery after
![image](https://github.com/jui-kumkum/SENG637_Assignment4_Group4/blob/main/Images/ExpandtoIncludeAFTER/RangeExpandtoInclude%20PIT%20Summery.png)

## Range.ExpandToinclude PIT Mutation after
![image](https://github.com/jui-kumkum/SENG637_Assignment4_Group4/blob/main/Images/ExpandtoIncludeAFTER/RangeExpandtoInclude%20PIT%20Mutation.png)

## DataUtilitiesCreateNumberArray2D PIT Summery before
![image](https://github.com/jui-kumkum/SENG637_Assignment4_Group4/blob/main/Images/DataUtilitiesBefore/Numer2DArrayPIT%20Summery.png)

## DataUtilitiesCreateNumberArray2D PIT Mutation before
![image](https://github.com/jui-kumkum/SENG637_Assignment4_Group4/blob/main/Images/DataUtilitiesBefore/Numer2DArrayPITMutation.png)



# Analysis drawn on the effectiveness of each of the test classes

# A discussion on the effect of equivalent mutants on mutation score accuracy

# A discussion of what could have been done to improve the mutation score of the test suites

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

Mutation testing is a software testing method that modifies the software (mutated) with small changes to provide errors or mutants. These mutations cab behave like common programming errors or introduce small faults to assess the quality of the test suite. The goal is to evaluate whether the written tests are able to detect these small errors or not.

### Advantages of Mutation Testing:

  - Improving Test Quality: It helps identify weaknesses in the test suite, indicating where additional tests are needed to add coverage.
  - Detecting Faults: Mutation testing can reveal specific conditions or edge cases that are not covered by the test suite.
  - Increases Software qualities: By ensuring that the test suite can detect small changes, developers develop better softwares that are resilience against errors.
  - Drives Effective Test Design: Encourages the development of more effective and comprehensive test cases, as it focuses on the behavior of the code rather than just code coverage metrics.
    
### Disdvantages of Mutation Testing:

   - Slow Perfromance: Running tests on many software versions takes time.
   - Misleading testers: Sometimes tests can't catch a bug and only wastes time on non-issues leading to false positives.
   - Not applicable to Black box testing: It involves changing the source code so it's not suitable for black box testing.
   - Resource intensive: Big software may have many possible errors, and using mutation testing requires resources.
   - 
# Explain your SELENUIM test case design process
We chose Shop Canada website for our test. And we tested main functionalities listed below: 
1. Signup: The website asks for Email, Password, confirm password, first name, last name, phone number, city, country and postal code. After entering these valuesby clicking on create account, sign up will be successful. We recorded all of these steps by Selenium under test case name ""SignUp". 
2. Login: The system asks f user or Email and password and can login to the website.
3. Login-Invalidusername: User entering wrong Email for login and get the invalid Username or Password error. 
4. Login-InvalidPassword: User enters correct username but wrong password and system showed error.
5. SearchBar: User clicks on the search box and searched for product "HAT". The system shows show 27 results for hats. 
6. User opens Appliance menu and should go to a page with appliance products and search bar. 
Once the recording is finished we then ran the test cases, and make sure there are no errors. Also, we added assertions and checkpoints between the commands to ensure that test runs correctly with the correct results.
7. Add to cart:Test for the no of valid product in add cart section.
8. Category selection:Test for the appropriate category.
9. 
# Explain the use of assertions and checkpoints
Assertions and checkpoints are software testing mechanisms to ensure code integrity and correctness of the software under test. While both may seem similar, they differ in their implementation and context of usage.
In Selenium, a web testing tool, assertions are validations or checkpoints for an application. 
The primary purpose of an assertion is to verify assumptions about the program’s behavior and detect any unexpected errors during runtime. They are implemented as conditional statements to evaluate a boolean expression. They are often used in stages of debugging and development to capture logical errors and ensure the application works as expected.

On the other hand, checkpoints are verification points in the test scripts where the tester validates whether the application state matches the expected outcome. The primary goal of checkpoints is to ensure the application works correctly at every stage of execution, providing feedback on success or failure at any specific operation. They are implemented using specific commands provided by testing frameworks. They are extensively used in automated testing to validate software functionality and behavior.



# How did you test each functionality with different test data
To test the functionality of our test cases, we followed the following steps;
For instance, in the login, the user has to enter the email and password and then press Sign in. If the username or password is wrong an error should show up. Otherwise, successfully sign in. For the invalid username (email) or password, we used one assertion: assert text. This checks if the proper error message has been displayed.
For the Searchbar we used assertion to view the correct number of products as the result of searching Hat. So we used assert text to see 27 results for the hat. 
For the Menu, the user should click on a category and go to a new page with the ability to search for products. We used the assert element present to see if the search bar shows up. 

# How the team work/effort was divided and managed
The work was divided among the group members where each member was supposed to deploy the Pitest tool and get the mutation coverage report separately. 
To increase the mutation coverage, new test cases were developed based on the mutation log for the tested methods and new methods in the Range class.
For the Selenium part, each of us developed at least two test cases to become familiar with GUI testing.
For the report compilation, a GitHub link was shared where all of us edited in, and the various sections of the report were distributed among us.
However, some members had issues with the mutation testing and hence focused on some other aspects of the work to make the project successful.

Division of mutation analysis and additional test cases:
The PIT mutation test and additional test case for increasing mutation coverage are divided by each member are summarized in the below table.
| Mutation test      |   Tester |
| -------------- | --- |  
| Niloofar Sharifisadr | Range.Contains and DataUtilities.CalculateColumnTotal    |
| Pratishtha      |     |
| Kumkum Akter         |    | 
| Arpita Chowdhury | |
| Fadila Abdulai Hamid |   | 

Division of Selenium IDE test cases:

The functionalities tested using Selenium IDE by each member are summarized in the below table.
| Tester     |  Functionality  |
| -------------- | --- |  
| Niloofar Sharifisadr |  Login(Valid and Invalid)    |
|  |  Sign Up  |
| Pratishtha      | Menu    |
|      |   Search Bar  |
| Kumkum Akter         |    | 
|          |    | 
| Arpita Chowdhury | Add cart|
| | Select category |
| Fadila Abdulai Hamid |   | 
|  |   | 

# Difficulties encountered, challenges overcome, and lessons learned
Difficulties:
1. There are several difficulties we faced in the time of PIT mutation in eclipse. Whenever I tried to run a PIT test in a different project in Elipse I faced this error, that there was no mutation coverage like Skipping coverage and analysis as no mutations found.
![image](https://github.com/jui-kumkum/SENG637_Assignment4_Group4/blob/main/Images/prbarpita.PNG)

2. In selinium test I founded freezing conditon of the website, it consumed a lot of time for the testing.

Collaboration was a bit slow because we were almost reaching the end of the semester, and most of us were caught up with other projects from other courses. Despite all that, we gain insight into how to do mutation testing and why it is useful, measure the mutation score of test suites to analyze the results, and become familiar with the mutation testing tool, Pitest. 
Also, we familiarized ourselves with automated GUI testing and became comfortable with some of the features that many record and replay testing tools and learned the differences between automated and manual GUI testing. Although we worked virtually, we performed collaborative testing where each of us used our critical thinking to encounter edge cases.
We learned software engineer practices such as team effort, resolved conflicts, time management, and code commits through GIT and industrial defect tracking systems, processes, and practices.

# Comments/feedback on the assignment itself
we understood what the assignment was about and highlighted the summary as they depicted the assignment's outcome; Illustrated step-by-step instructions on how to set up the
artifacts and test cases from the last phase and add the Pitest tool to the Eclipse IDE. The expected output of the assignment was clearly explained. This project, especially the Graphical User Interface testing part, was a very interesting subject to work on and could enhance our knowledge about mutation testing and automated GUI testing.
