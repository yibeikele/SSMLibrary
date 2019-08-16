package com.southwind.controller;

import com.southwind.entity.Borrow;
import com.southwind.entity.BorrowVO;
import com.southwind.entity.Reader;
import com.southwind.service.BookService;
import com.southwind.service.ReaderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping()
public class ReaderHandler {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;

    @GetMapping("/reader_borrow/{bookId}")
    @ResponseBody
    public ModelAndView reader_borrow(HttpServletRequest httpServletRequest,@PathVariable("bookId") int bookId){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = httpServletRequest.getSession();
        Reader reader = (Reader) session.getAttribute("object");
        System.out.println(reader);
        int readerId = reader.getId();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
        String borrowTime = simpleDateFormat.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.DAY_OF_YEAR,calendar.get(calendar.DAY_OF_YEAR)+14);
        String returnTime = simpleDateFormat.format(calendar.getTime());
        modelAndView.setViewName("redirect:/reader_borrowed.jsp");
        bookService.Borrow(bookId,readerId,borrowTime,returnTime,0);
        return modelAndView;
    }

    @RequestMapping("/reader_borrow_FindAll")
    @ResponseBody//ResponseBody 返回特定格式的数据到前端界面。 这里是返回json格式的数据
    public BorrowVO reader_borrow_findAll(HttpServletRequest request, HttpServletResponse  response,Integer page,Integer limit){
        response.setContentType("text/json;charset=UTF-8");
        HttpSession session = request.getSession();
        Reader reader = (Reader) session.getAttribute("object");
        int readerId = reader.getId();
        BorrowVO borrowVO = new BorrowVO();
        borrowVO.setCode(0);
        borrowVO.setMsg("");
        borrowVO.setCount(readerService.reader_borrow_count(readerId));
        borrowVO.setData(readerService.reader_borrow_findAll(readerId,page,limit));
        return borrowVO;
    }
}
