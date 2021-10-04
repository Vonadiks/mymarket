package ru.geekbrains.my.market;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.my.market.model.Order;
import ru.geekbrains.my.market.repositories.OrderRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @ParameterizedTest
    @CsvSource({
            "user, 2",
            "admin, 1"

    })
    public void findAllByUserName(String userName, int expectedSize) {
        List<Order> orderList = orderRepository.findAllByUsername(userName);
        assertEquals(expectedSize, orderList.size());
    }
}
