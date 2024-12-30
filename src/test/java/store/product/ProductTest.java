package store.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.promotion.Promotion;

class ProductTest {

    @ParameterizedTest
    @MethodSource("productProvider")
    @DisplayName("구매 개수 따라 제품을 추가로 증정 받을 수 있는 지 확인")
    void getReceiveFreeCountTest(String today, Product product, int buyCount, int expectedCount) {
        LocalDate now = LocalDate.parse(today);

        assertThat(product.getReceiveFreeCount(buyCount, now)).isEqualTo(expectedCount);
    }

    static Stream<Arguments> productProvider() {
        return Stream.of(
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10,
                                new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01"),
                                        LocalDate.parse("2024-12-31"))),
                        8, 1),
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10, null),
                        8, 0)
        );
    }
}