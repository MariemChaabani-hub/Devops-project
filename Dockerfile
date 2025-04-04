# Utilisation de l'image de base OpenJDK
FROM openjdk:11-jdk-slim

# Copie du fichier JAR généré dans l'image
ADD ./target/tp-foyer-5.0.0.jar app.jar

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
