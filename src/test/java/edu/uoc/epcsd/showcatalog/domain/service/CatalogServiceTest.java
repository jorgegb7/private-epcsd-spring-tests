package edu.uoc.epcsd.showcatalog.domain.service;

import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.repository.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CatalogServiceTest {

    private CatalogService catalogService;

    @BeforeEach
    void setUp() {
        ShowRepository showRepository = mock(ShowRepository.class);
        catalogService = new CatalogServiceImpl(showRepository, null, null);
        Show show = Show.builder().id(111L).build();
        when(catalogService.findShowById(show.getId())).thenReturn(Optional.of(show));
        when(catalogService.findShowById(-7L)).thenReturn(null);
    }

    @Test
    void findShowById() {
        Optional<Show> fromDb = catalogService.findShowById(111L);
        assert fromDb.isPresent();
        assertThat(fromDb.get().getId()).isEqualTo(111L);
    }

    @Test
    void findShowById2() {
        Optional<Show> fromDb = catalogService.findShowById(-7L);
        assertThat(fromDb).isNull();
    }
}