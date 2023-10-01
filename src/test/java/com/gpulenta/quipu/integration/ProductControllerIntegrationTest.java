package com.gpulenta.quipu.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpulenta.quipu.products.model.Product;
import com.gpulenta.quipu.products.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Product> productList;

    @BeforeEach
    void setUp() {
        productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", "Description 1", 10.0, "image1.jpg", 4.5, "Category 1"));
        productList.add(new Product(2, "Product 2", "Description 2", 20.0, "image2.jpg", 3.8, "Category 2"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(productList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        List<Product> responseProducts = objectMapper.readValue(content, new TypeReference<List<Product>>() {});

        assertEquals(productList, responseProducts);
    }
}
