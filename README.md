# antpool_monitor
Consumes Services located here --> https://www.antpool.com/user/apiGuild.htm

# Prerequisites
1. JDK 1.8 +. Tested on build 1.8.0_77-b03
2. Wildfly v. 10.0.0. Tested on v.10.1.0 as well
3. Mysql database. The following data sources must exist;
	- 
	-
4. Gradle v 2.13 
5. Maven v 3.2.1
6. Checkout the projet `antpool-monitor` from my public repo and install it in your local maven repo. It's a dependency on this project.



#Build instructions.

	```
	gradle clean ear
	```
	
6. Deploy to Wildfly version 10.0.0