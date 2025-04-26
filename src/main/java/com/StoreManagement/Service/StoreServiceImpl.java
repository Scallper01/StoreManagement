package com.StoreManagement.Service;

import com.StoreManagement.Repositories.*;
import com.StoreManagement.entities.*;
import com.StoreManagement.mappers.*;
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

    @Autowired
    public StoreServiceImpl(productRepository productRepository,
                            supplierRepository supplierRepository,
                            inventoryRepository inventoryRepository,
                            warehouseRepository warehouseRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public productDTO getProduct(Long productId) {
        try {
            Product product = productRepository.findById(productId).get();
            return productDTO.builder()
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
    public List<productDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<productDTO> productDTOS = new ArrayList<>();
        for (Product pr : products){
            productDTO dto = productDTO.builder()
                    .productName(pr.getName())
                    .productId(pr.getId())
                    .productPrice(pr.getPrice())
                    .supplierName(pr.getSupplier().getName())
                    .supplierId(pr.getSupplier().getId())
                    .build();
            productDTOS.add(dto);
        }
        return productDTOS;
    }

    @Override
    public Product addProduct(productDTO newProduct) {
        Supplier sup = supplierRepository.findById(newProduct.getSupplierId()).get();
        Product product = Product.builder()
                                .name(newProduct.getProductName())
                                .price(newProduct.getProductPrice())
                                .supplier(sup)
                                .build();
        return productRepository.save(product);
    }

    @Override
    public Inventory addIventory(inventoryDTO inventoryDTO, Long warehouseId) {
        Inventory suchInventory = inventoryRepository.findByProduct_IdAndWarehouse_Id(inventoryDTO.getProductId(),warehouseId);
        if (suchInventory!=null){
            Integer newQuantity = suchInventory.getQuantity()+inventoryDTO.getProductQuantity();
            suchInventory.setQuantity(newQuantity);
            return inventoryRepository.save(suchInventory);
        }
        else {
            Product product = Product.builder().id(inventoryDTO.getProductId()).build();
            Warehouse warehouse = Warehouse.builder().id(warehouseId).build();
            Inventory inventory = Inventory.builder()
                    .product(product)
                    .quantity(inventoryDTO.getProductQuantity())
                    .warehouse(warehouse)
                    .build();
            return inventoryRepository.save(inventory);
        }
    }

    @Override
    public warehouseDTO getWarehouseInventory(Long warehouseId) throws NoSuchElementException {
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
    public productDTO editProduct(productDTO productDTO) throws NoSuchElementException {
        Product product = productRepository.findById(productDTO.getProductId()).get();
        product.setName(productDTO.getProductName());
        product.setPrice(productDTO.getProductPrice());
        Product editedProduct = productRepository.save(product);
        return productDTO.builder()
                .productId(editedProduct.getId())
                .productName(editedProduct.getName())
                .supplierId(editedProduct.getSupplier().getId())
                .supplierName(editedProduct.getSupplier().getName())
                .productPrice(editedProduct.getPrice())
                .build();
    }

    @Override
    public List<supplierDTO> getAllSuppliers() {
        List<supplierDTO> dtos = new ArrayList<>();
        List<Supplier> Sup = supplierRepository.findAll();
        for (Supplier s: Sup){
            supplierDTO dto = supplierDTO.builder()
                    .supplierId(s.getId())
                    .supplierName(s.getName())
                    .supplierAddress(s.getAddress()).build();
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public supplierDTO getSupplierById(Long id) throws NoSuchElementException {
        Supplier sup = supplierRepository.findById(id).get();
        supplierDTO dto = supplierDTO.builder()
                .supplierId(sup.getId())
                .supplierName(sup.getName())
                .supplierAddress(sup.getAddress()).build();
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
                .supplierAddress(editedsup.getAddress()).build();
    }
}

