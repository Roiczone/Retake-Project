# Welcome to the Learning Outcomes Evaluation

Dear student,

Welcome to this Learning Outcomes Evaluation session. The goal is to assess your understanding and mastery of the learning outcomes for this semester as evidenced by your work that was submitted on your personal git account. Remember to answer each question thoroughly by referencing **Java** code and provide clear explanations where necessary.

Best regards,
Kay Berkling

## Ethics Section regarding generative and other forms of AI

The student acknowledges and agrees that the use of AI is strictly prohibited during this evaluation. By submitting this report, the student affirms that they have completed the form independently and without the assistance of any AI technologies. This agreement serves to ensure the integrity and authenticity of the students work, as well as their understanding of the learning outcomes. The student is also not allowed to copy paste statements that were prepared using AI into this document. 

Type your name here to agree:

.......Zone Kenne Roic........................... *type your name to agree*


## Checklist before handing in your work

* [ ] Review the assignment requirements to ensure you have completed all the necessary tasks.
* [ ] Double-check your links and make sure that links lead to where you intended. Each answer should have links to work done by you in your own git repository. 
* [ ] Make sure you have at least 10 references to your project code (This is important evidence to prove that your project is substantial enough to support the learning outcome of object oriented design and coding within a larger piece of code.)
* [ ] Include comments to explain your referenced code and why it supports the learning outcome.
* [ ] Commit and push this markup file to your personal git repository and hand in the link and a soft-copy via email at the end of the designated time period.

Remember, this checklist is not exhaustive, but it should help you ensure that your work is complete, well-structured, and meets the required standards.

Good luck with your evaluation!

# Project (70%)

## Description (overall vision of the project)

*My project consist of implementing an inventory system for a retail shop, where the user/administrator can apply the CRUD operations, sale product, track sale transactions, revenues and also benefit. Additionally, the use could filter their product display using a dropdown menu.*

## Link to your GIT Project

*https://github.com/Roiczone/Retake-Project*

## TECH STACK

*Frontend/UI: JvaFx
Backend: Java
Database: Sqlite using JDBC
Architecture: Data Access Object*


## What did you achieve? (list each of the functionalities)

*in the cost of implementing this project, I was able to achieve the following:
A full functional inventory system, a good user interface, database creation, accessing, connection and also error handling and validation*


## Pick 3 of the functionalities
#### for each of the 3 functionalities link to the class that is responsible for it and explain the role of this and other classes in combination to fulfill the functionality

### 1
*CRUD operations
this are just the different methods implement which define the actions that could be applyable on our object, which I had to link to our database responsible for storing every methods applied on the objects..
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/Service/ProductService.java
*

### 2
*Database connection
I also created a database class which is very necessary for storing my data/object and which I had to couple with the main.java class responsible for launching the JavaFX program.
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/Database/Database.java*

### 3
*Data Object Accessing
In order to easilly access to the object stored in my database, I implement 2 classes, one was the ProductDAO which holds the ways in which I use to access the objects and the secont one ProductDAOImpl where all the accessing methos where implemented.
here is the link: https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/DataAccessObject/ProductDAOImpl.java*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           9            |                               |
|||

## Learning Outcomes

| Exam Question | Total Achievable Points | Points Reached During Grading |
|---------------|------------------------|-------------------------------|
| Q1.Algorithms    |           4            |                               |
| Q2.Data types    |           4            |                               |
| Q3.Complex Data Structures |  4            |                               |
| Q4.Concepts of OOP |          6            |                               |
| Q5.OO Design     |           6            |                               |
| Q6.Testing       |           3            |                               |
| Q7.Operator/Method Overloading | 4 |                               |
| Q8.Templates/Generics |       6            |                               |
| Q9.Class libraries |          4            |                               |


## Evaluation Questions

Please answer the following questions to the best of your ability to show your understanding of the learning outcomes. Please provide examples from your project code to support your answers.


## Evaluation Material
All questions require an explanation and a link to your project on git. 

### Q1. Algorithms

Algorithms are manifold and Java can be used to program these. Examples are sorting or search strategies but also mathematical calculations. Please refer to **two** areas within your project with a link, where you have coded an algorithm. Do not make reference to code written for other classes, like theoretical informatics. Explain the algorithm.



*During the implemention of my project, I had to write and alrorithm which calculates the revenues based ond sales and another one which compares two values from two variable and returns a result.
One can find those implementations here.
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/Application/Main.java*


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           4            |                               |
|||

### Q2. Data types

Please **explain** the concept of data types and provide examples with links of different data types in Java. Typical data types in java are int, double, float, char, boolean, long, short, byte, String, and arrays. Please provide one example with a link for each of the **four** following data types in your code by linking to it. 

*In my product class, I had to use some of the following listed datatypes such as int, long, String and double for the declaration of my attributes, which can be seen on this code
long id;
Strink SKU;
int reorderAmont;
double:salePrice
link:
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/model/Product.java*



| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           4             |                               |
|||


### Q3. Complex Data Structures

Examples of complex data structures in java are ArrayList, HashMap, HashSet, LinkedList, and TreeMap. Please provide **two** examples with a link of how you have used these complex data structures in your code and **explain** why you have chosen these data structures.


*For my project I had to use the Arraylist in other to store every instance of my object product in the database. See the method :findAll in ProductDAOImpl.java. Here is the link:
.https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/DataAccessObject/ProductDAOImpl.java
*

  

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           4             |                               |
|||

### Q4. Concepts of OOP
Concepts of OOP are the basic building blocks of object-oriented programming, such as classes, objects, methods, and attributes. 
**Explain** HOW and WHY your **project** demonstrates the use of OOP by using all of the following concepts:
* Classes/Objects
* Methods
* Attributes 
**Link** to the code in your project that demonstrates what you have explained above.

*Classes and its attributes are used to portrait a real-world usage of objects, where the method are the there to define the differents actions one can apply on this object.
for classes and attributes follow this link https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/model/Product.java
for method applicable on this object see the following link: https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/Service/ProductService.java*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|             6           |                               |
|||

### Q5. OO Design
Please showcase **two** areas by linking to them where you have used object orientation and **explain** the advantage that object oriented code brings to the application or the problem that your code is addressing.
Examples in java of good oo design are encapsulation, inheritance, polymorphism, and abstraction. 


*I used Inheritance for instance in my main.java class, so I can used the already implemented methods or attibutes of that library and we also have encapsulation in our product.class which help us protect the data and the can aonly be accessed through getters and setters, see links:
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/Application/Main.java
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/model/Product.java*


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|              6          |                               |
|||


### Q6. Testing
Java code is tested by using JUnit. Please **explain** how you have used JUnit in your project and provide a link to the code where you have used JUnit. If you tested without JUnit, please explain how you tested your code. Why did you test that particular part. How did you test that part.

*For my project, I didn't use Junit, rather I did a manual testeing. For instance after i created a new products, I always had to make sure it is saved in my database*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|         3               |                               |
|||

### Q7. Operator/Method Overloading
An example of operator overloading is the "+" operator that can be used to add two numbers or concatenate two strings. An example of method overloading is having two methods with the same name but different parameters. Please provide **one** example with a link of how you have used operator or method overloading in your code and **explain** why you have chosen this method of coding.
Do not use "+" for your answer. 


*in the main.java class, we used overloading to automatically uptade the product analytics 
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/Application/Main.java*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|          4              |                               |
|||


### Q8. Templates/Generics
Generics in java are used to create classes, interfaces, and methods that operate on objects of specified types. Please provide **two** example with a link of how you have used generics in your code and **explain** why you have chosen to use generics. 


*I actually created two seperated generics for my products and sales classes in order to be able tostore them in the database and which also avoid conflicts.
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/DataAccessObject/SaleDAO.java
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/DataAccessObject/ProductDAO.java*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           6             |                               |
|||

### Q9. Class Libraries
Examples of class libraries in java are the Java Standard Library, JavaFX, Apache Commons, JUnit, Log4j, Jackson, Guava, Joda-Time, Hibernate, Spring, Maven, and many more. Please provide **one** example with a link of how you have used a class library in your **project** code and **explain** why you have chosen to use this class library. 

*For my project, I used the JavaFx class library because it offers predefined commands for GUI(Tablecreations, columcreations and more) which make the implementation easy
see link below:
https://github.com/Roiczone/Retake-Project/blob/main/Inventory/src/main/java/inventory/Application/Main.java*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            4            |                               |
|||


# Creativity (10%)
Which one did you choose: 

* [ ] Web Interface with Design
* [ ] Database Connected
* [ ] Multithreading
* [ ] File I/O
* [ ] API
* [ ] Deployment

Please **explain** which one of the above you worked with, link to the area in your code and why you chose to use that approach. 

*Web Interface with Design
Database Connected
Multithreading*



| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            10          |                               |
|||


# Speed Coding (20%)
Please enter **three** Links to your pair programming session GITs and name your partner. 

Topic: *Calculator*

1. *[LINK](https://github.com/Roiczone/Pair-programming-1)Daniel*

Topic: *Product Management System*

2. *https://github.com/Roiczone/Pairprogramming_2*

Topic: *CityHall Implementation*

3. *https://github.com/Roiczone/Pairprogramming_3 Daniel*



| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            15            |                               |
|||




