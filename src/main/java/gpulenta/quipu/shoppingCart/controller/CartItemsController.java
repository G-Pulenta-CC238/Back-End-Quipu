package gpulenta.quipu.shoppingCart.controller;

import gpulenta.quipu.shoppingCart.model.CartItems;
import gpulenta.quipu.shoppingCart.service.CartItemsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart-item")
public class CartItemsController {
    private final CartItemsService cartItemsService;

    @Autowired
    public CartItemsController(CartItemsService cartItemsService) {
        this.cartItemsService = cartItemsService;
    }

    // Create Cart Item
    @Operation(summary = "Create Cart Item", description = "Create a new Cart Item")
    @PostMapping
    public ResponseEntity<CartItems> createCartItem(@RequestBody CartItems cartItems) {
        return new ResponseEntity<>(cartItemsService.save(cartItems), HttpStatus.CREATED);
    }

    // List cart all Cart item service
    @Operation(summary = "Get all Cart Items", description = "Get all Cart Items details")
    @GetMapping
    public ResponseEntity<Iterable<CartItems>> getAllCartItems() {
        if (cartItemsService.getAllCartItems().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(cartItemsService.getAllCartItems(), HttpStatus.OK);
    }

    // Find Cart Item by Shopping Cart ID
    @Operation(summary = "Get Cart Item by Shopping Cart ID", description = "Get a Cart Item details by Shopping Cart ID")
    @GetMapping("/by-shopping-cart/{id}")
    public ResponseEntity<Iterable<CartItems>> getCartItemsByShoppingCartId(@PathVariable Long id) {
        if (cartItemsService.findByShoppingCart_Id(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(cartItemsService.findByShoppingCart_Id(id), HttpStatus.OK);
    }

    // Delete Cart Item by Shopping Cart ID
    @Operation(summary = "Delete Cart Item by Shopping Cart ID", description = "Delete an existing Cart Item by Shopping Cart ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItemByShoppingCartId(@PathVariable Long id) {
        cartItemsService.findByShoppingCart_Id(id).forEach(cartItems -> cartItemsService.delete(cartItems.getId()));
        return ResponseEntity.noContent().build();
    }
    // Delete Cart Item by ID
    @Operation(summary = "Delete Cart Item by ID", description = "Delete an existing Cart Item by ID")
    @DeleteMapping("/by-id/{id}")
    public ResponseEntity<Void> deleteCartItemById(@PathVariable Long id) {
        cartItemsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
