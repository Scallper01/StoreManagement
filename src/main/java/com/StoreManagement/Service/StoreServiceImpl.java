package com.StoreManagement.Service;

import com.StoreManagement.Repositories.*;
import com.StoreManagement.entities.*;
import com.StoreManagement.mappers.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Getter @Setter @NoArgsConstructor @Builder
public class StoreServiceImpl implements IStoreService {

    private productRepository productRepository;
    private supplierRepository supplierRepository;
    private inventoryRepository inventoryRepository;
    private warehouseRepository warehouseRepository;
    private customerRepository customerRepository;

    @Autowired
    public StoreServiceImpl(productRepository productRepository,
                            supplierRepository supplierRepository,
                            inventoryRepository inventoryRepository,
                            warehouseRepository warehouseRepository,
                            customerRepository customerRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public productDetailsDTO getProduct(Long productId) {
        try {
            Product product = productRepository.findById(productId).get();
            return productDetailsDTO.builder()
                    .productName(product.getName())
                    .productId(product.getId())
                    .productPrice(product.getPrice())
                    .supplierName(product.getSupplier().getName())
                    .supplierId(product.getSupplier().getId())
                    .build();
        } catch (RuntimeException e) {
            System.out.println("Service Layer : Error Catched during :" + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List<productDetailsDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<productDetailsDTO> productDetailsDTOS = new ArrayList<>();
        for (Product pr : products){
            productDetailsDTO dto = productDetailsDTO.builder()
                    .productName(pr.getName())
                    .productId(pr.getId())
                    .productPrice(pr.getPrice())
                    .supplierName(pr.getSupplier().getName())
                    .supplierId(pr.getSupplier().getId())
                    .build();
            productDetailsDTOS.add(dto);
        }
        return productDetailsDTOS;
    }

    @Override
    public productDetailsDTO addProduct(productDetailsDTO newProduct) {
        Supplier sup = supplierRepository.findById(newProduct.getSupplierId()).get();
        Product product = Product.builder()
                                .name(newProduct.getProductName())
                                .price(newProduct.getProductPrice())
                                .supplier(sup)
                                .build();
        Product addedPr = productRepository.save(product);
        return productDetailsDTO.builder()
                .productId(addedPr.getId())
                .productName(addedPr.getName())
                .productPrice(addedPr.getPrice())
                .supplierName(addedPr.getSupplier().getName())
                .supplierId(addedPr.getSupplier().getId())
                .build();
    }

    @Override
    public inventoryDTO addIventory(inventoryDTO inventoryDTO, Long warehouseId) {
        Inventory suchInventory = inventoryRepository.findByProduct_IdAndWarehouse_Id(inventoryDTO.getProductId(),warehouseId);
        if (suchInventory!=null){
            Integer newQuantity = suchInventory.getQuantity()+inventoryDTO.getProductQuantity();
            suchInventory.setQuantity(newQuantity);
            inventoryRepository.save(suchInventory);
            return inventoryDTO.builder()
                    .productId(suchInventory.getProduct().getId())
                    .productName(suchInventory.getProduct().getName())
                    .productQuantity(suchInventory.getQuantity())
                    .supplierName(suchInventory.getProduct().getSupplier().getName())
                    .build();
        }
        else {
            Product product = productRepository.findById(inventoryDTO.getProductId()).get();
            Warehouse warehouse = warehouseRepository.findById(warehouseId).get();
            Inventory inventory = Inventory.builder()
                    .product(product)
                    .quantity(inventoryDTO.getProductQuantity())
                    .warehouse(warehouse)
                    .build();
            inventoryRepository.save(inventory);
            return inventoryDTO.builder()
                    .productId(inventory.getProduct().getId())
                    .productName(inventory.getProduct().getName())
                    .productQuantity(inventory.getQuantity())
                    .supplierName(inventory.getProduct().getSupplier().getName())
                    .build();
        }
    }

    @Override
    public warehouseDTO getWarehouseContent(Long warehouseId) throws NoSuchElementException {
        List<Inventory> inventories = inventoryRepository.findAll();
        Warehouse wh = warehouseRepository.findById(warehouseId).get();
        List<inventoryDTO> dtos = new ArrayList<>();
        for (Inventory inv : inventories){
            Long id = inv.getWarehouse().getId();
            if (id==warehouseId){
                inventoryDTO dto = inventoryDTO.builder()
                        .productId(inv.getProduct().getId())
                        .productName(inv.getProduct().getName())
                        .productQuantity(inv.getQuantity())
                        .supplierName(inv.getProduct().getSupplier().getName()).build();
                dtos.add(dto);
            }
        }
        return warehouseDTO.builder().warehouseInventory(dtos).warehouseName(wh.getName()).build();
    }

    @Override
    public productDetailsDTO editProduct(productDetailsDTO productDetailsDTO) throws NoSuchElementException {
        Product product = productRepository.findById(productDetailsDTO.getProductId()).get();
        product.setName(productDetailsDTO.getProductName());
        product.setPrice(productDetailsDTO.getProductPrice());
        Product editedProduct = productRepository.save(product);
        return productDetailsDTO.builder()
                .productId(editedProduct.getId())
                .productName(editedProduct.getName())
                .supplierId(editedProduct.getSupplier().getId())
                .supplierName(editedProduct.getSupplier().getName())
                .productPrice(editedProduct.getPrice())
                .build();
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<supplierDTO> getAllSuppliers() {
        List<supplierDTO> dtos = new ArrayList<>();
        List<Supplier> Sup = supplierRepository.findAll();
        for (Supplier s: Sup){
            supplierDTO dto = supplierDTO.builder()
                    .supplierId(s.getId())
                    .supplierName(s.getName())
                    .supplierAddress(s.getAddress())
                    .supplierProducts(new ArrayList<>())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public supplierDTO getSupplierById(Long id) throws NoSuchElementException {
        Supplier sup = supplierRepository.findById(id).get();
        List<Product> prds = productRepository.findBySupplier_Id(id);
        supplierDTO dto = supplierDTO.builder()
                .supplierId(sup.getId())
                .supplierName(sup.getName())
                .supplierAddress(sup.getAddress())
                .supplierProducts(new ArrayList<>()).build();
        for (Product p : prds){
            productInfo PI = productInfo.builder()
                    .productId(p.getId())
                    .productName(p.getName())
                    .build();
            dto.getSupplierProducts().add(PI);
        }
        return dto;
    }

    @Override
    public supplierDTO editSupplier(supplierDTO supplierDTO) throws NoSuchElementException {
        Supplier sup = supplierRepository.findById(supplierDTO.getSupplierId()).get();
        sup.setName(supplierDTO.getSupplierName());
        sup.setAddress(supplierDTO.getSupplierAddress());
        Supplier editedsup = supplierRepository.save(sup);
        return supplierDTO.builder()
                .supplierId(editedsup.getId())
                .supplierName(editedsup.getName())
                .supplierAddress(editedsup.getAddress())
                .supplierProducts(new ArrayList<>()).build();
    }

    @Override
    public customerDTO addCustomer(customerDTO customerDTO) {
        Customer customer = Customer.builder()
                .name(customerDTO.getCustomerName())
                .address(customerDTO.getCustomerAddress()).build();
        Customer addedCustomer = customerRepository.save(customer);
        return customerDTO.builder()
                .customerId(addedCustomer.getId())
                .customerName(addedCustomer.getName())
                .CustomerAddress(addedCustomer.getAddress())
                .build();
    }

    @Override
    public List<customerDTO> getAllCustomers() {
        List<customerDTO> dto = new ArrayList<>();
        List<Customer> cus = customerRepository.findAll();
        for (Customer c: cus){
            customerDTO dto1 = customerDTO.builder()
                    .customerId(c.getId())
                    .customerName(c.getName())
                    .CustomerAddress(c.getAddress())
                    .build();
            dto.add(dto1);
        }
        return dto;
    }

    @Override
    public customerDTO getCustomerById(Long id) throws NoSuchElementException {
        Customer c = customerRepository.findById(id).get();
        return customerDTO.builder()
                .customerId(c.getId())
                .customerName(c.getName())
                .CustomerAddress(c.getAddress())
                .build();
    }

    @Override
    public String deleteCustomerById(Long id) throws NoSuchElementException {

        try {
            Customer c = customerRepository.findById(id).get();
            customerRepository.deleteById(id);
            return "INFO : Customer identified by Id : "+id+" is succeffully deleted";
        } catch (NoSuchElementException e){
            return "WARN : No Customer found by Id : "+id;
        }
    }

    @Override
    public warehouseDTO addWarehouse(warehouseDTO warehouseDTO) {
        Warehouse wh = warehouseRepository.save(Warehouse.builder().name(warehouseDTO.getWarehouseName()).build());
        return warehouseDTO.builder()
                .warehouseName(wh.getName())
                .warehouseInventory(new ArrayList<>())
                .build();
    }

    @Override
    public List<warehouseDTO> getAllWarehouses(String field) {
        List<Warehouse> whs = warehouseRepository.findAll();
        List<warehouseDTO> dtos = new ArrayList<>();
        for(Warehouse ws : whs){
            warehouseDTO dto = warehouseDTO.builder()
                    .warehouseName(ws.getName())
                    .warehouseInventory(new ArrayList<>())
                    .build();
            dtos.add(dto);
            if (field.equals("Yes")){
                dto.setWarehouseInventory(getWarehouseContent(ws.getId()).getWarehouseInventory());
            }
        }
        return dtos;
    }
}

