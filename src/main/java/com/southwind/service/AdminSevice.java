package com.southwind.service;

import com.southwind.entity.BorrowVO;
import com.southwind.entity.ReaderVO;

public interface AdminSevice {
    public void agreeBorrow(int borrowId ,int adminId );
    public ReaderVO reader_findAll(int page,int limit);
    public void disagreeBorrow(int borrowId,int adminId,int readerId);
    public BorrowVO returnBook(int page, int limit);
}
