# RTAPlatform
The backend for a Real-Time Analytics platform.<br/>
The platform collects user interactions from various sources, processes the data, and provides real-time analytics for users.</br>

# App requirements
1. Data Collection:</br>
• Assume you have a Kafka cluster to ingest user interaction data from multiple
sources (e.g., website, mobile app).</br>
• Implement a Kafka producer that simulates user interactions and sends them to
the appropriate Kafka topic.</br>
2. Data Processing:</br>
• Implement Kafka consumers to process incoming user interaction data.</br>
• Processed data should be stored in a Redis database for real-time analytics.</br>
3. Real-time Analytics:</br>
• Design and implement a real-time analytics engine that aggregates and
computes various metrics based on the processed data stored in Redis.</br>
• Metrics could include user engagement, popular products, or event
frequencies.</br>
4. Database Integration:</br>
• Set up a PostgreSQL database to store additional metadata, such as user profiles
and product information.</br>
• Implement CRUD operations for managing user profiles and product data.</br>
5. User Flow and APIs:</br>
• Design and implement APIs to expose real-time analytics to users.</br>
• Users should be able to query for specific metrics or receive real-time updates.</br>

# Documentations
[Data Flow and ER diagrams](https://lucid.app/lucidchart/68c820c6-1d15-41d1-8175-470b36cdefe0/edit?viewport_loc=-324%2C-22%2C3304%2C1559%2C0_0&invitationId=inv_a20b2be9-e6ee-4ddb-ac7f-4a5d4b671769)</br>
![Data Flow and ER diagrams](Data_Flow_and_ER_diagrams.png?raw=true "Data_Flow_and_ER_diagrams")

# App design overview
It's a monolithic app created in the Clean Architecture philosophy.</br>
Inside the repo, you can find one module with several types of layers (pkg's):</br>
- use cases, like 'app_user' or 'user_interaction'</br>
- entities, like 'analytics'</br>
- interfaces, like 'postgresql', 'rest', etc</br>
- common, like 'utils'</br>

A structure of this type will allow us to add new entities and change interfaces less painfully. Also, if necessary, we can extract the Analytic entity to a separate module for scaling.</br>

The REST API standard was chosen to interact with the client. This is popular because it is well-standardized and easily integrated with other teams.</br>

# Assumptions
It's not a fully completed app and many parts of it are not finished. For example:
- All data flow realized on the basic level without any concentration in optimization data saving or searching
- For the app exist only unit tests and only for checking  the minimum of positive scenarios
- No any security сhecks or control of the access from the outside app or inside the layers
- Application fault tolerance tasks were also not considered 
- The app, 3rd party services, and client work in the one-time zone 

It's not all of the assumptions, but it is enough for understanding the coverage of the implementation.</br> 

# Used frameworks and libraries
As a basic framework was used Spring Boot. It has the possibility to use different technologies, like SQL and Key-Value database or Kafka like a message broker, and doesn't spend time checking compatibility between technologies.</br>

Libraries like Lombok or Flyway give the possibility to keep our code cleaner and easily manage the app.</br>

For testing, JUnit 5 was used as an out-of-the-box decision for the Spring and 'org.containers' to check work with 3rd party technologies like databases and Kafka.<br>

# Order of the implementation
This exercise has 6 modules, and I chose a strategic: from the simple to the interesting.</br>
For a start, I created a basic data flow and ER diagrams. I made an app_user model and service with simple tests. It gave me the possibility to check that I have a simple and starting application.</br>
After that, I added the possibility of creating a postgresql container for the tests and finished with the repository and controller.</br>

The next big step was the implementation of Kafka infrastructure and a test container for it. When I received a working message I added API for data collection and user_interaction.<br>
The last step of integration with 3rd party services was Redis.<br>

In the end, I created a simple version of the analytics service end API for it. Also, I rechecked all tests and how the app works in the local environment with communication from Postman.<br>

P.S.<br>
For now, you can look at the code, check the tests and if you want - to try run it locally.<br>
I used my home Windows PC, but I hope app will not have problems with a UNIX base environment.<br>

# Test run requirements
For some tests, it will be necessary to pre-install docker. You can find [How to install Docker Desktop](https://docs.docker.com/desktop/install/mac-install/) on the official site.  

# Local app run bits of advice
For running docker img of Postgre you can use next command <br>
<code>docker run --rm --name RTAPlatformPostgresDb -p 5455:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=test_database -d postgres</code><br>

For running docker img of Kafka without Zookeeper you can use next command <br>
<code>docker run --rm --name RTAPlatformKafkaWithoutZookeeper  -p 9092:9092 -d bashj79/kafka-kraft</code><br>

For running docker img of Redis you can use next command <br>
<code>docker run --rm --name RTAPlatformRedis -p 6379:6379 -d redis</code><br>

For working with API, you can use Postman and the existing [collection of requests](backend%2Fsrc%2Fmain%2Fresources%2Frest%2FRTAPlatform.postman_collection.json)</br>

# Possible next steps
I think deploying an app on the DEV environment is a good idea for the next step. We haven't many dependencies for now, and all issues with the environment can be fixed easily.<br>

After that, we need to decide about basic security rules and checks.<br>

As a third step, we can communicate with the Client team and discuss the API and how we will integrate it.<br> 

Of course, we have many other steps. But as usual, they depend on the decision on the earliest step.<br>
For example:
 - For any optimization of db, need to understand our loading and environment. Maybe we use an XS instance and we can up it. Or need to create load tests for reproducing problem analytic reports and tune the query or db engine
 - For exception handling will be better to communicate with the Client team and to hear what they want to see in the msg body

And many other steps which we can make together.