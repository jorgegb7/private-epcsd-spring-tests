package edu.uoc.epcsd.showcatalog.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    @Mock
    private Show show;

    @BeforeEach
    void setUp() {
        show = Show.builder().build();
    }

    @Test
    void whenCreatedShowAndCancelled_statusIsCancelled() throws Exception{
        assertEquals(show.getStatus(), Status.CREATED);
        show.cancel();
        assertEquals(show.getStatus(),Status.CANCELLED);
    }
}