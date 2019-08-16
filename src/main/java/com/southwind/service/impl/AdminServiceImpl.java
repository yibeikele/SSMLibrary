package com.southwind.service.impl;

import com.southwind.entity.Borrow;
import com.southwind.entity.BorrowVO;
import com.southwind.entity.ReaderVO;
import com.southwind.repository.BookAdminRepository;
import com.southwind.service.AdminSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminSevice {

    @Autowired
    private BookAdminRepository bookAdminRepository;

    @Override
    public void agreeBorrow(int borrowId, int adminId) {
        bookAdminRepository.agreeBorrow(borrowId, adminId);
    }

    @Override
    public ReaderVO reader_findAll(int page, int limit){
        ReaderVO readerVO = new ReaderVO();
        readerVO.setCode(0);
        readerVO.setMsg("");
        readerVO.setCount(bookAdminRepository.reader_findCount());
        readerVO.setData(bookAdminRepository.reader_findAll((page-1)*limit,limit));
        return readerVO;
    }

    @Override
    public void disagreeBorrow(int borrowId,int adminId,int readerId){
        bookAdminRepository.disagreeBorrow(borrowId,adminId,readerId);
    }

    @Override
    public BorrowVO returnBook(int page, int limit) {
        List<Borrow> borrowList = null;
        borrowList = bookAdminRepository.returnBook((page-1)*limit,limit);
        for (Borrow borrow: borrowList) {
            borrow.setState("还书未审核");
        }
        BorrowVO borrowVO = new BorrowVO();
        borrowVO.setCode(0);
        borrowVO.setCount(bookAdminRepository.returnBookCount());
        borrowVO.setData(borrowList);
        borrowVO.setMsg("");
        return borrowVO;
    }
}
