FROM java:11
WORKDIR /
ADD exercise.jar exercise.jar
EXPOSE 8080
CMD java - jar exercise.jar