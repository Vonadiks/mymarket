package ru.geekbrains.my.market;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.my.market.dto.CategoryDto;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ServerRunTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Test
    public void fullRestTest() {
        List<CategoryDto> categoryDtoList = testRestTemplate.getForObject("/api/v1/categories", List.class );
        Assertions.assertThat(categoryDtoList).isNotNull();
        Assertions.assertThat(categoryDtoList).isNotEmpty();
    }
}
