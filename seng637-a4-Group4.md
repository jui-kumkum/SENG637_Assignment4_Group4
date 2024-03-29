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

# Explain your SELENUIM test case design process
We chose Shop Canada website for our test. And we tested main functionalities listed below: 
1. Signup: The website asks for Email, Password, confirm password, first name, last name, phone number, city, country and postal code. After entering these valuesby clicking on create account, sign up will be successful. We recorded all of these steps by Selenium under test case name ""SignUp". 
2. Login: The system asks f user or Email and password and can login to the website.
3. Login-Invalidusername: User entering wrong Email for login and get the invalid Username or Password error. 
4. Login-InvalidPassword: User enters correct username but wrong password and system showed error.
5. SearchBar: User clicks on the search box and searched for product "HAT". The system shows show 27 results for hats. 
6. User opens Appliance menu and should go to a page with appliance products and search bar. 
Once the recording is finished we then ran the test cases, and make sure there are no errors. Also, we added assertions and checkpoints between the commands to ensure that test runs correctly with the correct results.
# Explain the use of assertions and checkpoints

For login, the user have to enter the email and password then press Sign in. If if the username or password is wrong an error should show up. Otherwise, can successfully Sign-In. 

For the invalid username (email) or password we used one assertion: assert text. This checks if the proper error message has been displayed.

For the Searchbar we used assertion to voew the correct number of products as the result of searching Hat.So we used assert text to see 27 results for hat. 
For the Menu, user should click on a category and go to a new page with ability to search products. We used assert element present to see if the search bar shows up. 


# How did you test each functionality with different test data

# How the team work/effort was divided and managed
Division of mutation analysis and additional test cases:
The PIT mutation test and additional test case for increasing mutation coverage are divided by each member are summarized in the below table.
| Mutation test      |   Tester |
| -------------- | --- |  
| Niloofar Sharifisadr |     |
| Pratishtha      |     |
| Kumkum Akter         |    | 
| Arpita Chowdhury | |
| Fadila Abdulai Hamid |   | 

Division of Selenium IDE test cases:

The functionalities tested using Selenium IDE by each member are summarized in the below table.
| Tester     |  Functionality  |
| -------------- | --- |  
| Niloofar Sharifisadr |     |
|  |     |
| Pratishtha      |     |
|      |     |
| Kumkum Akter         |    | 
|          |    | 
| Arpita Chowdhury | Add cart|
| | Select category |
| Fadila Abdulai Hamid |   | 
|  |   | 

# Difficulties encountered, challenges overcome, and lessons learned

# Comments/feedback on the assignment itself
