# RTAPlatform
The backend for a Real-Time Analytics platform.<br/>
The platform collects user interactions from various sources, processes the data, and provides real-time analytics for users.</br>

# Requirements:
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
• Metrics could include things like user engagement, popular products, or event
frequencies.</br>
4. Database Integration:</br>
• Set up a PostgreSQL database to store additional metadata, such as user profiles
and product information.</br>
• Implement CRUD operations for managing user profiles and product data.</br>
5. User Flow and APIs:</br>
• Design and implement APIs to expose the real-time analytics to users.</br>
• Users should be able to query for specific metrics or receive real-time updates.</br>

# Documentations:
[Database ER diagram](https://lucid.app/lucidchart/68c820c6-1d15-41d1-8175-470b36cdefe0/edit?viewport_loc=-324%2C-22%2C3304%2C1559%2C0_0&invitationId=inv_a20b2be9-e6ee-4ddb-ac7f-4a5d4b671769)</br>
