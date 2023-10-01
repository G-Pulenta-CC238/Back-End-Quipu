package com.gpulenta.quipu.behavior;

import com.gpulenta.quipu.products.model.Product;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
@CucumberContextConfiguration
@SpringBootTest(classes = CucumberTestConfig.class)
public class StepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<Product[]> response;

    @Given("la aplicación está en ejecución")
    public void laAplicaciónEstáEnEjecución() {
        // No es necesario realizar ninguna acción aquí para este ejemplo
    }

    @When("el cliente solicita todos los productos")
    public void elClienteSolicitaTodosLosProductos() {
        String baseUrl = "http://localhost:8080"; // Ajusta la URL base según tu configuración
        String endpoint = "/api/v1/products";
        String url = baseUrl + endpoint;

        response = restTemplate.exchange(url, HttpMethod.GET, null, Product[].class);
    }

    @Then("la respuesta debe contener una lista de productos")
    public void laRespuestaDebeContenerUnaListaDeProductos() {
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        Product[] products = response.getBody();
        assertNotNull(products);
        assertTrue(products.length > 0);
    }
}
