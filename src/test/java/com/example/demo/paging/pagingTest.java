package com.example.demo.paging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pagingTest {

    @Test
    public void test(){
        paging paging = new paging(250,1,10);
        paging.print();
        assertTrue(paging.getBeginPage() == 1);
        assertTrue(paging.getEndPage()==10);
    }

    @Test
    public void test1(){
        paging paging = new paging(250,15,10);
        paging.print();
        assertTrue(paging.getBeginPage() == 11);
        assertTrue(paging.getEndPage()==20);
    }

    @Test
    public void test2(){
        paging paging = new paging(157,13,10);
        paging.print();
        assertTrue(paging.getBeginPage() == 11);
        assertTrue(paging.getEndPage()==16);
    }



}