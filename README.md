**PROJECT BUILD COMMAND:**

run command in root project folder (marker can be file pom.xml)

`mvn clean install`


**PROJECT RUN COMMAND:**

`java -jar  -Ddatasource.url={jdbc_url} {file.jar}`

example:

`java -jar  -Ddatasource.url=jdbc:sqlite:ds18b20.db target/diblom-0.0.1-SNAPSHOT.jar`