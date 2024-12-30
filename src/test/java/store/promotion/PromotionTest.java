package store.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PromotionTest {

    @ParameterizedTest
    @DisplayName("이벤트 기간 내 프로모션 제품을 추가로 받을 수 있는 개수가 올바른 지 테스트")
    @MethodSource("promotionProvider")
    void getReceiveFreeCountTest(String today, Promotion promotion, int buyCount, int expectedCount) {
        LocalDate now = LocalDate.parse(today);

        assertThat(promotion.getReceiveFreeCount(buyCount, now)).isEqualTo(expectedCount);
    }

    static Stream<Arguments> promotionProvider() {
        return Stream.of(
                Arguments.of("2024-12-11",
                        new Promotion("탄산2+1", 2, 1, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")), 8,
                        1),
                Arguments.of("2024-12-30",
                        new Promotion("반짝할인", 1, 1, LocalDate.parse("2024-11-01"), LocalDate.parse("2024-11-30")), 8,
                        0),
                Arguments.of("2024-11-11",
                        new Promotion("반짝할인", 1, 1, LocalDate.parse("2024-11-01"), LocalDate.parse("2024-11-30")), 9,
                        1)
        );
    }
}