# Usa una imagen base de Java
FROM openjdk:17-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/quipu-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecuta tu aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicia el contenedor
CMD ["java", "-jar", "app.jar"]
