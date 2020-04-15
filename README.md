# CoGiv

![Java CI with Maven](https://github.com/phoenikx/community-help/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)
United we stand, let us not think about being divided right now.

To run this project:
1. Clone this repo
2. Run mongodb on your machine. You can either install it natively or use
docker to install it. To install it using Docker, run the following.
    >  docker run -p 27017:27017 -v ~/_mongodb_data:/data/db --name mongoContainer -d --rm mongo:3.6 
3. Run the following command.
    > mvn spring-boot:run
