package com.gpulenta.quipu.behavior.user;

import com.gpulenta.quipu.behavior.CucumberTestConfig;
import com.gpulenta.quipu.users.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserCreationSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<User> response;

    @Given("la aplicación está en ejecución")
    public void laAplicaciónEstáEnEjecución() {
        // No es necesario realizar ninguna acción aquí para este ejemplo
    }

    @When("el cliente envía una solicitud POST para crear un usuario con los siguientes datos")
    public void elClienteEnvíaUnaSolicitudPOSTParaCrearUnUsuarioConLosSiguientesDatos(User user) {
        String baseUrl = "http://localhost:8080"; // Ajusta la URL base según tu configuración
        String endpoint = "/api/v1/user";
        String url = baseUrl + endpoint;

        // Enviar la solicitud POST para crear un usuario
        HttpEntity<User> request = new HttpEntity<>(user);
        response = restTemplate.exchange(url, HttpMethod.POST, request, User.class);
    }

    @Then("la respuesta debe contener el usuario creado")
    public void laRespuestaDebeContenerElUsuarioCreado(User expectedUser) {
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());

        User createdUser = response.getBody();
        assertNotNull(createdUser);
        // Verificar que los datos del usuario creado coincidan con los esperados
        assertEquals(expectedUser.getUsername(), createdUser.getUsername());
        assertEquals(expectedUser.getName(), createdUser.getName());
        assertEquals(expectedUser.getLastname(), createdUser.getLastname());
        assertEquals(expectedUser.getAddress(), createdUser.getAddress());
        assertEquals(expectedUser.getEmail(), createdUser.getEmail());
        assertEquals(expectedUser.getPhone(), createdUser.getPhone());
    }
}
