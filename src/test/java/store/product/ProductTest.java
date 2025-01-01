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
    @MethodSource("receiveFreeCountTestProductProvider")
    @DisplayName("구매 개수 따라 제품을 추가로 증정 받을 수 있는 지 확인")
    void calculateMoreBonusTest(String today, Product product, int buyCount, int expectedCount) {
        LocalDate now = LocalDate.parse(today);

        assertThat(product.calculateMoreBonus(now, buyCount)).isEqualTo(expectedCount);
    }

    static Stream<Arguments> receiveFreeCountTestProductProvider() {
        return Stream.of(
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10,
                                new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01"),
                                        LocalDate.parse("2024-12-31"))),
                        8, 1),
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10, null),
                        8, 0),
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10, null),
                        11, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("noPromotionApplyCountTestProductProvider")
    @DisplayName("구매 개수가 프로모션 재고를 초과 시 몇 개를 정가로 구매해야하는 지 확인")
    void calculateNoPromotionApplyTest(String today, Product product, int buyCount, int expectedCount) {
        LocalDate now = LocalDate.parse(today);

        assertThat(product.calculateNoPromotionApply(now, buyCount)).isEqualTo(expectedCount);
    }

    static Stream<Arguments> noPromotionApplyCountTestProductProvider() {
        return Stream.of(
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10,
                                new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01"),
                                        LocalDate.parse("2024-12-31"))),
                        12, 3),
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10, null),
                        12, 0),
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10, null),
                        11, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateTotalBonusTestProvider")
    @DisplayName("증정 받는 상품 개수 계산")
    void calculateTotalBonusTest(String today, Product product, int buyCount, int expectedCount) {
        LocalDate now = LocalDate.parse(today);

        assertThat(product.calculateTotalBonus(now, buyCount)).isEqualTo(expectedCount);
    }

    static Stream<Arguments> calculateTotalBonusTestProvider() {
        return Stream.of(
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10,
                                new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01"),
                                        LocalDate.parse("2024-12-31"))),
                        8, 2),
                Arguments.of("2024-12-11",
                        new Product("콜라", 1000, 10,
                                new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01"),
                                        LocalDate.parse("2024-12-31"))),
                        12, 3)
        );
    }
}