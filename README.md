The Registration class provides methods to register and drop courses. It checks the student's eligibility before registration. If the student has already taken this course, it asks whether he/she wants to drop it. Registration cannot complete if the class conflicts with his/her current schedule or if the class is full.

To run the application, follow these steps:

1, Install MySQL and start the service.

2, Download JDBC driver (a .jar file) and add it to your classpath. 
http://dev.mysql.com/downloads/connector/j/
Follow this guide to modify classpath:
https://docs.oracle.com/javase/tutorial/essential/environment/paths.html

3, Run scripts in registration.sql in MySQL. This will create three tables in the test database: students, courses, registration.
1) Table students contains the student id and name.
2) Table courses contains the course id, name, number of seats, day and time, etc.
3) Table registration contains the student id and the courses they are taking.

4, Run java Registration in console.
1) The default MySQL address is 127.0.0.1:3306. The prompt will ask if you have it installed somewhere else. You can type in to change it to your own address and socket.
2) The prompt will then ask for MySQL user name and password. Since we're working on the test database here you can just press enter.
3) Next enter student id and the class id that you want to register or drop.
4) The system will check eligibility and ask for your confirmation.
