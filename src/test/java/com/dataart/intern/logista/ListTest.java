package com.dataart.intern.logista;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class ListTest {

    @Test
    public void test() {
        MyList<String> myList = new MyList<>("x");
        Assertions.assertEquals(myList.first(), "x");
        myList.insert("r");
        Assertions.assertEquals(myList.previous(), "x");
        Assertions.assertEquals(myList.next(), "r");
        myList.insert("ü");
        Assertions.assertEquals(myList.previous(), "r");
        Assertions.assertEquals(myList.next(), "ü");
        myList.delete("r");
        Assertions.assertEquals(myList.previous(), "r");
        Assertions.assertEquals(myList.next(), "ü");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionNext() {
        MyList<String> myList = new MyList<>("x");
        myList.next();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionPrevious() {
        MyList<String> myList = new MyList<>("x");
        myList.previous();
    }

    @Test(expected = RuntimeException.class)
    public void testException() {
        MyList<String> myList = new MyList<>(null);
        myList.first();
    }
}
