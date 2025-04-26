package com.StoreManagement.web;

import com.StoreManagement.Service.IStoreService;
import com.StoreManagement.entities.*;
import com.StoreManagement.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class storeController {

    private IStoreService service;

    @Autowired
    public storeController(IStoreService service) {
        this.service = service;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        productDTO product = service.getProduct(id);
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
    public ResponseEntity<List<productDTO>> getAllproducts(){
        return ResponseEntity.ok(service.getAllProduct());
    }

    @GetMapping("warehouse/{id}")
    public warehouseDTO getwarehouseinventory(@PathVariable Long id){
        return service.getWarehouseInventory(id);
    }


    @PostMapping("/product")
    public Product addProd(@RequestBody productDTO newprod){
        return service.addProduct(newprod);
    }

    @PutMapping("/product")
    public Object editProduct(@RequestBody productDTO productDTO){
        try {
            return service.editProduct(productDTO);

        } catch (NoSuchElementException e){
            return "{ Warning : No product found with id :"+productDTO.getProductId()+"}";
        }
    }

    @PostMapping("/inventory/{id}")
    public Inventory addProductInventory(@RequestBody inventoryDTO newInventoryDTO, @PathVariable Long id){
        return service.addIventory(newInventoryDTO, id);
    }

    @GetMapping("suppliers")
    public List<supplierDTO> getAllSuppliers(){
        return service.getAllSuppliers();
    }

    @GetMapping("supplier/{id}")
    public Object getSupplierById(@PathVariable Long id) {
        try{
            return service.getSupplierById(id);
        } catch (NoSuchElementException e){
            return "{}";
        }

    }

    @PutMapping("/supplier")
    public Object editSupplier(@RequestBody supplierDTO supplierDTO){
        try {
            return service.editSupplier(supplierDTO);
        } catch (NoSuchElementException e){
            return "{Warning : No Supplier found with"+supplierDTO.getSupplierId()+"}";
        }
    }

    @PostMapping("/customer")
    public customerDTO addCustomer(@RequestBody customerDTO newCustomer){
        return service.addCustomer(newCustomer);
    }

    @GetMapping("/customers")
    public List<customerDTO> getAllCustomers(){
        return service.getAllCustomers();
    }
}
