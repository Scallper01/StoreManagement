package com.StoreManagement.Service;

import com.StoreManagement.entities.Inventory;
import com.StoreManagement.entities.Product;
import com.StoreManagement.mappers.*;

import java.util.List;

public interface IStoreService {

    //product Management
    public productDTO getProduct(Long productId);
    public List<productDTO> getAllProduct();
    public Product addProduct(productDTO newProduct);
    public Inventory addIventory(inventoryDTO inventoryDTO, Long warehouseId);
    public warehouseDTO getWarehouseInventory(Long warehouseId);
    public productDTO editProduct(productDTO productDTO);

    //Supplier Management
    public List<supplierDTO> getAllSuppliers();
    public supplierDTO getSupplierById(Long id);
    public supplierDTO editSupplier(supplierDTO supplierDTO);

    //Customer Management
    public customerDTO addCustomer(customerDTO customerDTO);
    public List<customerDTO> getAllCustomers();
}
