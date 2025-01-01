package store.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.product.Product;
import store.promotion.Promotion;

class OrderTest {

    @Test
    @DisplayName("검색된 제품이 없다면 에러를 발생시킨다.")
    void noExistProductTest() {
        assertThatThrownBy(() -> new Order(List.of(), 21))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @MethodSource("outOfStockTestProvider")
    @DisplayName("재고가 없다면 에러를 발생시킨다.")
    void outOfStockTest(List<Product> products) {
        assertThatThrownBy(() -> new Order(products, 21))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> outOfStockTestProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Product("콜라", 1000, 10,
                                        new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01"),
                                                LocalDate.parse("2024-12-31"))),
                                new Product("콜라", 1000, 10,
                                        new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01"),
                                                LocalDate.parse("2024-12-31")))
                        ))
        );
    }
}