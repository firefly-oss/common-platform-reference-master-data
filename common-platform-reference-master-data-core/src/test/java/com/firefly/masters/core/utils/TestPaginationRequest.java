package com.firefly.masters.core.utils;

import com.firefly.common.core.queries.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * A test utility class that extends PaginationRequest for testing purposes.
 * This class provides methods to get and set page and size values for tests.
 */
public class TestPaginationRequest extends PaginationRequest {
    
    private int page;
    private int size;
    
    public TestPaginationRequest() {
        this(0, 10);
    }
    
    public TestPaginationRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
