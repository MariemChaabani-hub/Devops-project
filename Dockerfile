FROM openjdk:17

# Exposer le port 8089
EXPOSE 8089

# Copier le fichier JAR généré dans le conteneur
COPY tp-foyer/target/tp-foyer-5.0.0.jar /tp-foyer-5.0.0.jar

# Commande d'entrée pour démarrer l'application
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]

























































