README for Student Record Application
ENSF 409 Final Project

Authors:
Dylan Rae
Email: dylan.rae@ucalgary.ca
UCID: 30020151

Tyler Sawatzky
Email: tyler.sawatzky@ucalgary.ca
UCID: 30020880


Instructions
1. Run the package databaseSetup.
	- Only do this once
	- Modifications may be required to the "IDBCredentials" interface to create functionality with your MySQL database
	- This sets up the tables required for the server to load students and courses

2. Run the package server.
	- Modifications may be required to the "IDBCredentials" interface to create functionality with your MySQL database

3. Run the package client
	- Usernames are the student IDs
	- All passwords and access levels (admin or student) are visible in the "users" table
	- Example admin credentials: Username: 2, Password: javamaster69
	- Example student credentials: Username: 4, Password: WingBats11

4. After logging in you can now explore the functionality of the application.

5. Running multiple clients will open new log in windows. All clients connected to the same server will be working in the same database.


Bonus Features
1. Maintain a list of users and develop login/out feature (5 marks).
2. Created a separate GUI for an Admin user with the functionality of creating new courses (10 marks).
3. Deployed the project; ran the server and client on separate machines (5 marks).
	- To run client and server on different machines, change the the string host in ClientController to the IP address of the machine running the server.
