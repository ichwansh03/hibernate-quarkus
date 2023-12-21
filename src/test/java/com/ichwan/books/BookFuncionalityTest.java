package com.ichwan.books;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class BookFuncionalityTest {

    @BeforeEach
    void setUp() {
        PanacheMock.mock(Book.class);
    }

    @Test
    void testBookMocking() {
        Book b = new Book();
        Mockito.when(Book.findById(3L)).thenReturn(b);
        Assertions.assertSame(b, Book.findById(3L));
    }
}
