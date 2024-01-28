FROM openjdk:17
WORKDIR /app
COPY ./target/DonationManagement-0.0.1-SNAPSHOT.jar /app
EXPOSE 8181
CMD ["java", "-jar", "DonationManagement-0.0.1-SNAPSHOT.jar"]