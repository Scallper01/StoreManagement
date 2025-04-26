package com.StoreManagement.web;

import com.StoreManagement.Service.IStoreService;
import com.StoreManagement.entities.*;
import com.StoreManagement.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class storeController {

    private IStoreService service;

    @Autowired
    public storeController(IStoreService service) {
        this.service = service;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        productDTO product = service.getProductDetails(id);
        if (product == null) {
            System.err.println("Web Layer : Error occurred while fetching product: Product not found");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorCode", "PRODUCT_NOT_FOUND");
            errorResponse.put("message", "Product with ID " + id + " not found.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse); // or you can return a custom error message instead of null
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<productDTO>> getAllproductsDetails(){
        return ResponseEntity.ok(service.getAllProductDetails());
    }

    @GetMapping("warehouse/{id}")
    public List<inventoryDTO> getwarehouseinventory(@PathVariable Long id){
        return service.getWarehouseInventory(id);
    }


    @PostMapping("/product")
    public Product addProd(@RequestBody productDTO newprod){
        return service.addProduct(newprod);
    }

    @PostMapping("/inventory/{id}")
    public Inventory addProductInventory(@RequestBody inventoryDTO newInventoryDTO, @PathVariable Long id){
        return service.addIventory(newInventoryDTO, id);
    }
}
