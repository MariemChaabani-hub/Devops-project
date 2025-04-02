FROM openjdk:17
EXPOSE 8089
COPY target/tp-foyer-5.0.0.jar tp-foyer-5.0.0.jar
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]
HEALTHCHECK --interval=30s --timeout=10s \
  CMD wget --quiet --tries=1 --spider http://localhost:8089/actuator/health || exit 1