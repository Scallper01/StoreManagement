package com.StoreManagement.dataAccess;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class order {
    private Long id;
    private Date date;
    private Collection<orderContent> products;
    private customer customer;
}
