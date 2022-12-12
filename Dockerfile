FROM openjdk:8
EXPOSE 8090
ADD target/Assurance-0.0.1-SNAPSHOT.jar assurance-mysql.jar
ENTRYPOINT ["java","-jar" , "assurance-mysql.jar" ]