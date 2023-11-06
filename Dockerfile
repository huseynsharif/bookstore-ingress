FROM openjdk:18
WORKDIR /app
EXPOSE 8081
COPY target/book-store-docker.jar program.jar
CMD ["java","-jar","program.jar"]