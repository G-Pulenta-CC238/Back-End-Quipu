package gpulenta.quipu.shoppingCart.controller;


import gpulenta.quipu.shoppingCart.model.ShoppingCart;
import gpulenta.quipu.shoppingCart.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // Create Shopping Cart
    @Operation(summary = "Create Shopping Cart", description = "Create a new Shopping Cart")
    @PostMapping
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return new ResponseEntity<>(shoppingCartService.save(shoppingCart), HttpStatus.CREATED);
    }

    // Find Shopping Cart all
    @Operation(summary = "Get all Shopping Carts", description = "Get all Shopping Carts details")
    @GetMapping
    public ResponseEntity<Iterable<ShoppingCart>> getAllShoppingCarts() {
        if (shoppingCartService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(shoppingCartService.findAll(), HttpStatus.OK);
    }

    // Find Shopping Cart by ID
    @Operation(summary = "Get Shopping Cart by ID", description = "Get a Shopping Cart details by ID")
    @GetMapping("/by-id/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Long id) {
        ShoppingCart shoppingCart = shoppingCartService.findById(id);
        if (shoppingCart == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    // Update Shopping Cart
    @Operation(summary = "Update Shopping Cart by ID", description = "Update an existing Shopping Cart's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable Long id, @RequestBody ShoppingCart shoppingCart) {
        return new ResponseEntity<>(shoppingCartService.update(shoppingCart), HttpStatus.OK);
    }
    // Find by User ID and show user details
    @Operation(summary = "Get Shopping Cart by User ID", description = "Get a Shopping Cart details by User ID")
    @GetMapping("/by-user/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartByUserId(@PathVariable Long id) {
        ShoppingCart shoppingCart = shoppingCartService.findByUserId(id);
        if (shoppingCart == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

}
