package ru.ginatulin.tests;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.ginatulin.models.entity.OrderEntity;
import ru.ginatulin.repository.OrderRepository;
import ru.ginatulin.service.OrderRestService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class OrderRepositoryTest {

     @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testRepository() {
        List<OrderEntity> entities = orderRepository.findAll();
        assertEquals(1,entities.size());
    }
}
