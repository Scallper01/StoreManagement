package com.StoreManagement.Service;

import com.StoreManagement.entities.Inventory;
import com.StoreManagement.entities.Product;
import com.StoreManagement.mappers.*;

import java.util.List;

public interface IStoreService {

    //product Management
    public productDetailsDTO getProduct(Long productId);
    public List<productDetailsDTO> getAllProduct();
    public productDetailsDTO addProduct(productDetailsDTO newProduct);
    public inventoryDTO addIventory(inventoryDTO inventoryDTO, Long warehouseId);
    public productDetailsDTO editProduct(productDetailsDTO productDetailsDTO);
    public void deleteProduct(Long productId);
    //Supplier Management
    public List<supplierDTO> getAllSuppliers();
    public supplierDTO getSupplierById(Long id);
    public supplierDTO editSupplier(supplierDTO supplierDTO);

    //Customer Management
    public customerDTO addCustomer(customerDTO customerDTO);
    public List<customerDTO> getAllCustomers();
    public customerDTO getCustomerById(Long id);
    public String deleteCustomerById(Long id);

    //Warehouse Management
    public warehouseDTO addWarehouse(warehouseDTO warehouseDTO);
    public List<warehouseDTO> getAllWarehouses(String field);
    public warehouseDTO getWarehouseContent(Long warehouseId);

    //to do : Ordering Management
    //to do : Delivery Management
}
