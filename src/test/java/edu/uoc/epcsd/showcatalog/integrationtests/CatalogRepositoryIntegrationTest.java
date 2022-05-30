package edu.uoc.epcsd.showcatalog.integrationtests;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import javax.persistence.EntityManager;
import java.util.HashSet;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CatalogRepositoryIntegrationTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void whenStoredACategory_WeCanFindCategoryById() {
        Category rap = new Category(7L, "rap", "rap");
        CategoryEntity entity = new CategoryEntity(null, rap.getName(), rap.getDescription(), new HashSet<>());
        entityManager.persist(entity);
        Category result = categoryRepository.findCategoryById(entity.getId()).get();
        assertThat(result.getName()).isEqualTo(rap.getName());
    }
}