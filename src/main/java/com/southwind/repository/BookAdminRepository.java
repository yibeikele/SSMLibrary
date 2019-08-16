package com.southwind.repository;

import com.southwind.entity.BookAdmin;
import com.southwind.entity.Borrow;
import com.southwind.entity.Reader;

import java.util.List;

public interface BookAdminRepository {
    public BookAdmin login(String username,String password);
    public void agreeBorrow(int borrowId,int adminId);
    public List<Reader> reader_findAll(int page, int limit);
    public int reader_findCount();
    public void disagreeBorrow(int borrowId,int adminId,int readerId);
    public List<Borrow> returnBook(int page, int limit);
    public int returnBookCount();
}
