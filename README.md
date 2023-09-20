# Backend Quipu

## Team
Somos el Startup **GPulenta** , creadores de **Quipu**.

- Lucas Benedetti Rivas          **Project Manager**
- Nicolas Haro Dávila             **Developer**
- Aldo Medina Paredes          **Developer**
- Wilver Arana Ramos            **Developer**
- Salomon Zegarra Moreno   **Developer**
	> GPulenta tiene como misión desarrollar aplicaciones de alto nivel para el uso masivo.

## Requirements

| Dependencia                                                | Versión    |
|------------------------------------------------------------|------------|
| `org.springframework.boot:spring-boot-starter-data-jpa`    | 3.1.3      |
| `org.springframework.boot:spring-boot-starter-validation`  | 3.1.3      |
| `org.springdoc:springdoc-openapi-starter-webmvc-ui`         | 2.0.4      |
| `org.springframework.boot:spring-boot-starter-web`         | 3.1.3      |
| `com.mysql:mysql-connector-j`                               | (La versión específica depende de tu configuración) |
| `org.projectlombok:lombok`                       | (La versión específica depende de tu configuración) |
| `org.springframework.boot:spring-boot-starter-test` (alcance de prueba) | 3.1.3      |
| `IDE de preferencia`  | N/A     |


##   Instructions
### Compilar y Ejecutar un Proyecto Spring Boot desde GitHub

1. **Clonar el Repositorio**

   Primero, clona el repositorio desde GitHub a tu máquina local utilizando el siguiente comando en tu terminal:
   ```shell
   git clone <URL_DEL_REPOSITORIO>
2.  **Navegar al Directorio del Proyecto** 
Accede al directorio del proyecto clonado usando el siguiente comando:
    ```shell 
    cd <NOMBRE_DEL_DIRECTORIO>
    
3. **Compilar el Proyecto**
Utiliza el siguiente comando para compilar el proyecto utilizando Maven:
   ```shell
   mvn clean install
4. **Ejecutar el Proyecto**
Una vez que la compilación sea exitosa, puedes ejecutar el proyecto Spring Boot con el siguiente comando:
   ```shell
   mvn spring-boot:run  

