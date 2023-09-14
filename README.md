# RTAPlatform
The backend for a Real-Time Analytics platform.<br/>
The platform collects user interactions from various sources, processes the data, and provides real-time analytics for users.</br>

# App Requirements:
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

# Documentations:
[Database ER diagram](https://lucid.app/lucidchart/68c820c6-1d15-41d1-8175-470b36cdefe0/edit?viewport_loc=-324%2C-22%2C3304%2C1559%2C0_0&invitationId=inv_a20b2be9-e6ee-4ddb-ac7f-4a5d4b671769)</br>

# Assumptions and explanations 
1. Redis will make snapshots of the dataset on disk. It will likely be an infrastructure question, and will not focus on it more. If it will be necessary, we can discuss how it can be implemented and why.</br>
2. I will use REST as a standard for API because it's one of the most popular standards, and I don't see a reason for other types. For now, we have a straightforward API.
3. I will not add any constraints for DELETE operations and will clear all existing data. It can be changed in the future if necessary.
4. Before the CREATE operation for a new User, I will check the existence of this User by First and Last name and create only a new one.
5. Before the CREATE operation for a new Product, I will check the existence of this Product by Name and create only a new one.
6. I will use the Maven automation tool because I like it. But it's no prob to change it to Gradle if necessary.
7. I will use Spring as a basic framework for the App because it is one of the most popular Java frameworks for now, and it's not a problem - to find developers with Spring experience. It's not a JEE :D
8. I don't think about any PostgreSQL optimization because good indexing or partitioning needs actual data and user experience.
9. I implemented a DB constraint on the Java level for more flexibility. But it can be duplicated or moved to the DB side in the future. 

# Test Run Requirements:
1. For some test will necessary pre-install docker. You can find [How to install Docker Desktop](https://docs.docker.com/desktop/install/mac-install/) on the official site.

# App Run Requirements:
For runing docker img of Postgre you can use next command <br>
<code>docker run --name RTAPlatformPostgresDb -p 5455:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=test_database -d postgres</code>