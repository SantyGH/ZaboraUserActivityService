# Stage 1: Build
FROM maven:3.9.3-eclipse-temurin-21 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los archivos de configuración de Maven primero (para cachear dependencias)
COPY pom.xml .

# Descargamos las dependencias (cacheable)
RUN mvn dependency:go-offline -B

# Copiamos el código fuente
COPY src ./src

# Construimos el JAR ejecutable
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre

# Directorio de trabajo
WORKDIR /app

# Copiamos el JAR desde el stage de build
COPY --from=build /app/target/user-activity-service-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto configurado en application.yml
EXPOSE 8003

# Comando para ejecutar el microservicio
ENTRYPOINT ["java","-jar","app.jar"]
