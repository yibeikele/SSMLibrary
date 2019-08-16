package com.southwind.service.impl;


import com.southwind.entity.Borrow;
import com.southwind.repository.ReaderRepository;
import com.southwind.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public List<Borrow> reader_borrow_findAll(int readerId, int page, int limit) {
        List<Borrow> borrowList = null;
        borrowList =  readerRepository.reader_borrow_findAll(readerId,(page-1)*limit,limit);
        for (Borrow borrow: borrowList) {
            String str = borrow.getState();
            switch (str){
                case "0":
                    borrow.setState("未审核");
                    break;
                case "1":
                    borrow.setState("审核通过");
                    break;
                case "2":
                    borrow.setState("审核未通过");
                    break;
                case "3":
                    borrow.setState("已归还");
                    break;
            }
        }
        return borrowList;
    }

    @Override
    public int reader_borrow_count(int readerId) {
        return readerRepository.reader_borrow_count(readerId);
    }

    @Override
    public int Reader_borrow_all() {
        return readerRepository.reader_borrow_all();
    }

    @Override
    public List<Borrow> admin_borrow_findAll(int page, int limit) {
        List<Borrow> borrowList = null;
        borrowList = readerRepository.admin_borrow_finaAll((page-1)*limit,limit);
        for (Borrow borrow: borrowList) {
            borrow.setState("借书未审核");
        }
        return borrowList;
    }
}
