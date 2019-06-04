FROM maven:3.6.1-jdk-11-slim AS maven-builder
WORKDIR /crypto-wallet
COPY pom.xml .
RUN mvn verify -DskipTests --fail-never # dependency caching
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:11.0.3-jre-slim
WORKDIR /crypto-wallet
COPY --from=maven-builder /crypto-wallet/target/virtual-crypto-wallet.jar .
ENTRYPOINT java -Xmx128m -jar ./virtual-crypto-wallet.jar
EXPOSE 8080
