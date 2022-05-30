package edu.uoc.epcsd.showcatalog.unitTests;

import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ShowUnitTest {

    @Mock
    private Show show;

    @BeforeEach
    void setUp() {
        show = Show.builder().build();
    }

    @Test
    void whenCreatedShowAndCancelled_statusIsCancelled() throws Exception{
        assertThat(show.getStatus()).isSameAs(Status.CREATED);
        show.cancel();
        assertThat(show.getStatus()).isSameAs(Status.CANCELLED);
    }
}