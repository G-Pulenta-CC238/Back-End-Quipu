package com.gpulenta.quipu.cartItem.controller;

import com.gpulenta.quipu.cartItem.model.CartItems;
import com.gpulenta.quipu.cartItem.service.CartItemsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-items")
public class CartItemsController {
    private final CartItemsService cartItemsService;

    @Autowired
    public CartItemsController(CartItemsService cartItemsService) {
        this.cartItemsService = cartItemsService;
    }

    @Operation(summary = "Get all cart items", description = "Get a list of all cart items")
    @GetMapping("/cart-items")
    public ResponseEntity<List<CartItems>> getAllCartItems() {
        return new ResponseEntity<>(cartItemsService.getAllCartItems(), HttpStatus.OK);
    }

    @Operation(summary = "Get cart item by ID", description = "Get details of a cart item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart item details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    @GetMapping("/cart-items/{id}")
    public ResponseEntity<CartItems> getCartItemsById(@PathVariable int id) {
        return new ResponseEntity<>(cartItemsService.getCartItemsById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get cart items by shopping cart ID", description = "Get a list of cart items by shopping cart ID")
    @GetMapping("/cart-items/shopping-carts/{id}")
    public ResponseEntity<List<CartItems>> getCartItemsByShoppingCartId(@PathVariable int id) {
        return new ResponseEntity<>(cartItemsService.getCartItemsByShoppingCartId(id), HttpStatus.OK);
    }

    @Operation(summary = "Create a new cart item", description = "Create a new cart item and add it to the shopping cart")
    @PostMapping("/cart-items")
    public ResponseEntity<CartItems> createCartItems(@RequestBody CartItems cartItems) {
        return new ResponseEntity<>(cartItemsService.saveCartItems(cartItems), HttpStatus.CREATED);
    }

    @Operation(summary = "Update cart item by ID", description = "Update the details of a cart item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart item details updated successfully"),
            @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    @PutMapping("/cart-items/{id}")
    public ResponseEntity<CartItems> updateCartItems(@PathVariable int id, @RequestBody CartItems cartItems) {
        return new ResponseEntity<>(cartItemsService.updateCartItems(id, cartItems), HttpStatus.OK);
    }

    @Operation(summary = "Delete cart item by ID", description = "Delete a cart item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    @DeleteMapping("/cart-items/{id}")
    public ResponseEntity<String> deleteCartItems(@PathVariable int id) {
        cartItemsService.deleteCartItems(id);
        return new ResponseEntity<>("CartItems deleted successfully", HttpStatus.OK);
    }
}