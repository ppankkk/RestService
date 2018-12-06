This is Hello World "REST" app
===========================
[![Build Status](https://travis-ci.org/ppankkk/RestService.svg?branch=master)](https://travis-ci.org/ppankkk/RestService)

That app prints hello messages to console regarding to current day time.

It can return contact by id and get filtered contacts by regular expression.

To run this app you need to:
1) Install&run mySql server.
2) Create empty database and user according to DAO/resources/jdbc.properties or edit this file with yours config.
3) Config database datasource according to picture below and yours DB config (if needed).
![datasource](https://user-images.githubusercontent.com/6603859/49575230-de766f80-f94a-11e8-9108-58993513ffb5.png)
4) Execute SQL script DAO/resources/CREATE_TEST_DATA/0_Create_schema.sql.
5) Execute SQL script DAO/resources/CREATE_TEST_DATA/1_Insert_test_data.sql.
6) Install&run Apache Tomcat7 or Tomcat8 on http://localhost:8080 
   with added user "tomcat7" with password "tomcat" and role "manager-script"
7) Execute maven goal "tomcat7:deploy"

Now application can answer to GET requests on URLs:
http://localhost:8080/hello/contact?id=id
http://localhost:8080/hello/contacts?nameFilter=val
