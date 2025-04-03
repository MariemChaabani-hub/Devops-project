# Utilisation de l'image OpenJDK 11 depuis Docker Hub
FROM openjdk:11-jdk-slim

# Exposer le port de l'application (modifie si nécessaire)
EXPOSE 8080

# Ajouter le fichier JAR généré dans le conteneur
ADD target/tp-foyez.jar tp-foyez.jar

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/tp-foyez.jar"]
