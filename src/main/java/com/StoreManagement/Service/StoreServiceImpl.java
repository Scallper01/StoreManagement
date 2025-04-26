package com.StoreManagement.Service;

import com.StoreManagement.Repositories.*;
import com.StoreManagement.entities.*;
import com.StoreManagement.mappers.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter @Setter @NoArgsConstructor @Builder
public class StoreServiceImpl implements IStoreService {

    private productRepository productRepository;
    private supplierRepository supplierRepository;
    private inventoryRepository inventoryRepository;

    @Autowired
    public StoreServiceImpl(productRepository productRepository,supplierRepository supplierRepository, inventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public productDTO getProductDetails(Long productId) {
        try {
            Product product;
            product = productRepository.findById(productId).get();
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
    public List<productDTO> getAllProductDetails() {
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
    public List<inventoryDTO> getWarehouseInventory(Long warehouseId) {
        List<Inventory> inventories = inventoryRepository.findAll();
        List<inventoryDTO> inventoryDTOS = new ArrayList<>();
        for (Inventory inv : inventories){
            Long id = inv.getWarehouse().getId();
            if (id==warehouseId){
                inventoryDTO dto = inventoryDTO.builder()
                        .productId(inv.getProduct().getId())
                        .productName(inv.getProduct().getName())
                        .productQuantity(inv.getQuantity())
                        .supplierName(inv.getProduct().getSupplier().getName()).build();
                inventoryDTOS.add(dto);
            }
        }
        return inventoryDTOS;
    }
}

