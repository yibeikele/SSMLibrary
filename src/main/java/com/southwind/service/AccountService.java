package com.southwind.service;

import com.southwind.entity.Book;
import com.southwind.entity.Reader;

import java.util.List;

public interface AccountService {
    public Object save(String type,String username,String password);
}
