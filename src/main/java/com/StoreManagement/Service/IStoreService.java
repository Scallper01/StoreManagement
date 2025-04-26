package com.StoreManagement.Service;

import com.StoreManagement.entities.Inventory;
import com.StoreManagement.entities.Product;
import com.StoreManagement.mappers.*;

import java.util.List;

public interface IStoreService {

    public productDTO getProductDetails(Long productId);
    public List<productDTO> getAllProductDetails();
    public Product addProduct(productDTO newProduct);
    public Inventory addIventory(inventoryDTO inventoryDTO, Long warehouseId);
    public List<inventoryDTO> getWarehouseInventory(Long warehouseId);

}
