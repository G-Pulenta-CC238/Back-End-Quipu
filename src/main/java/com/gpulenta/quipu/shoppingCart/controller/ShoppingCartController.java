package com.gpulenta.quipu.shoppingCart.controller;

import com.gpulenta.quipu.shared.exceptions.ResourceNotFoundException;
import com.gpulenta.quipu.shared.exceptions.ValidationException;
import com.gpulenta.quipu.shoppingCart.model.ShoppingCart;
import com.gpulenta.quipu.shoppingCart.service.ShoppingCartService;
import com.gpulenta.quipu.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tripstore/v1")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @Operation(summary = "Get all shopping carts", description = "Get a list of all shopping carts")
    @GetMapping("/shopping-carts")
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
        return new ResponseEntity<>(shoppingCartService.getAllShoppingCarts(), HttpStatus.OK);
    }

    @Operation(summary = "Get shopping cart by ID", description = "Get details of a shopping cart by its ID")
    @ApiResponse(responseCode = "200", description = "Shopping cart details retrieved successfully")
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable int id) {
        return new ResponseEntity<>(shoppingCartService.getShoppingCartById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get shopping cart by user ID", description = "Get the shopping cart of a user by their ID")
    @ApiResponse(responseCode = "200", description = "Shopping cart details retrieved successfully")
    @ApiResponse(responseCode = "404", description = "User not found or shopping cart not found")
    @GetMapping("/users/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartByUserId(@PathVariable int id) {
        notFoundUser(id);
        notFoundShoppingCartByUserId(id);
        return new ResponseEntity<>(shoppingCartService.getShoppingCartByUserId(id), HttpStatus.OK);
    }

    @Operation(summary = "Create a new shopping cart", description = "Create a new shopping cart")
    @ApiResponse(responseCode = "201", description = "Shopping cart created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body or shopping cart with the same user ID already exists")
    @PostMapping("/shopping-carts")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        validateShoppingCart(shoppingCart);
        notFoundUser(shoppingCart.getUser().getId());
        existsShoppingCartByUserId(shoppingCart.getUser().getId());
        return new ResponseEntity<>(shoppingCartService.saveShoppingCart(shoppingCart), HttpStatus.CREATED);
    }


    @Operation(summary = "Delete shopping cart by ID", description = "Delete a shopping cart by its ID")
    @ApiResponse(responseCode = "200", description = "Shopping cart deleted successfully")
    @ApiResponse(responseCode = "404", description = "Shopping cart not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable int id) {
        shoppingCartService.deleteShoppingCart(id);
        return new ResponseEntity<>("ShoppingCart with id: " + id + " was deleted", HttpStatus.OK);
    }

    public void validateShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart.getUser() == null) {
            throw new ValidationException("User is required");
        }
    }

    private void existsShoppingCartByUserId(int id) {
        if (shoppingCartService.existsByUser_Id(id)) {
            throw new ValidationException("ShoppingCart with user id: " + id + " already exists");
        }
    }

    private void notFoundUser(int id) {
        if (userService.getUserById(id) == null) {
            throw new ResourceNotFoundException("User with id: " + id + " not found");
        }
    }

    private void notFoundShoppingCartByUserId(int id) {
        if (shoppingCartService.getShoppingCartByUserId(id) == null) {
            throw new ResourceNotFoundException("ShoppingCart with user id: " + id + " not found");
        }
    }

}
