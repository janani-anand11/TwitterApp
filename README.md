# TwitterApp
How to start the TwitterApp application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/TwitterApp-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`
4. To retrieve Tweets from home timeline enter url `http://localhost:8080/api/1.0/twitter/timeline`
5. To retrieve filtered Tweets from home timeline using a keyword enter url `http://localhost:8080/api/1.0/twitter/timeline/filter`
6. To post a Tweet enter url `http://localhost:8080/api/1.0/twitter/timeline` on postman and pass the tweet to be posted in the body. 

# To view the UI for TwitterApp:
Enter url `http://localhost:8080` on browser
