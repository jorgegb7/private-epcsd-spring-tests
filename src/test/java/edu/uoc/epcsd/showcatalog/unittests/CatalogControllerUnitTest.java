package edu.uoc.epcsd.showcatalog.unittests;

import config.Config;
import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@WebMvcTest(CatalogControllerUnitTest.class)
@ContextConfiguration(classes = { Config.class })
@ExtendWith(MockitoExtension.class)

class CatalogControllerUnitTest {

    @MockBean
    private CatalogService catalogService;
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        Category rap = new Category(7L, "rap", "rap");
        Category rock = new Category(6L, "rock", "rock");
        Category pop = new Category(5L, "pop", "pop");
        Category metal = new Category(4L, "metal", "metal");

        List<Category> categories = new ArrayList<>();
        categories.add(rap);
        categories.add(rock);
        categories.add(pop);
        categories.add(metal);

        when(catalogService.findAllCategories()).thenReturn(categories);
    }

    @Test
    void verifyCategoriesAreRight_givenAnEndPoint() throws Exception {
        mockMvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));

        assertThat(catalogService.findAllCategories().get(1)).isInstanceOf(Category.class);
    }
}