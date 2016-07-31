# log4j_search_and_db_con_mgr
Zentity software development test solution
------------------------------------------
The project is comprised of two parts
1 - The first part is database connection manager with connection pool and failover access at connection package -> ConnectionManager class.
	- ConnectionManager reads database parameters wit method getDBParams from the specified xml file, and returns an instance of DatabaseServers which has an Engines instance which contains a list of Engine instances with connection parameters like username, password ... etc. 
	- ConnectionManager also has a getConnection method which takes an Engine as argument and returns a logical pooled connection. Initially getConnection is designed to work with two database types (Oracle, MySql).
	
	 
2 - The second part is Log4j utility with custom appender and full text search capability at logging, search, and util packages.
 	- The custom Appender class DataAppender is designed to append logs to file at "logMe/default.log".
 	- The DemoLogger class is designed to demonstrate the functionality of the custom appender.
 	- There's is a full text search functionality, located at search package, two classes implement the search functionality; Match and Matcher. The Matcher returns a list of Matches with line and colomn occurrences. 
 	
------------------------------------------

** USED TECHNOLOGIES **
Log4J --> for logging and creating custom appender
Maven --> dependency and project management
Junit --> unit testing
JAXB --> converting xml schema to class and reading xml file
JDBC --> database connectivity
GIT --> project version control and change tracking

------------------------------------------

** PROS AND CONS **
- Searching :
	pros : search for all text occurrences and returns list of occurrence with line and colomn.
	cons : not optimized, its complexity is O(n^2)
	
	
------------------------------------------	
	
** UNDER DEVELOPMENT **
- Implementing failover access to database 
– If one database dies the ConnectionManager should automatically switch to 
second database server 
– During failover mode checks if master DB server is again ready to use and 
establish all new connections to master DB server afterwards 

	

 	
