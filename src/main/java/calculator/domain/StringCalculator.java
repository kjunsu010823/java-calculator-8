package calculator.domain;

import java.util.Arrays;

/**
 * 문자열을 파싱하고, 숫자들을 검증하며, 합산하는 핵심 로직을 담당합니다.
 */
public class StringCalculator {

    /**
     * 입력된 문자열을 파싱하고, 유효성을 검증한 후 모든 숫자를 더한 값을 반환합니다.
     *
     * @param text 입력 문자열
     * @return 숫자들의 합
     * @throws IllegalArgumentException 음수가 포함되거나 숫자가 아닌 값이 포함된 경우
     */
    public int add(String text) {
        // StringParser를 사용하여 숫자 문자열 배열을 얻습니다. (빈 문자열 입력 시 빈 배열 반환)
        String[] numbersArray = StringParser.parse(text);

        // 배열이 비어있으면 0을 반환합니다. (빈 문자열 입력 처리)
        if (numbersArray.length == 0) {
            return 0;
        }

        return Arrays.stream(numbersArray)
                .mapToInt(this::toIntAndValidate) // 숫자 변환 및 검증
                .sum();
    }

    /**
     * 문자열을 정수로 변환하고 음수 여부를 검증합니다.
     *
     * @param s 숫자 문자열
     * @return 변환된 정수
     * @throws IllegalArgumentException 음수이거나 숫자가 아닌 경우
     */
    private int toIntAndValidate(String s) {
        try {
            // trim()으로 앞뒤 공백 제거 후 숫자로 변환
            int number = Integer.parseInt(s.trim());

            // 1. 음수 검증 (예외_테스트 통과 조건)
            if (number < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
            }

            return number;
        } catch (NumberFormatException e) {
            // 2. 숫자가 아닌 값이 포함된 경우 (예: "a", "" 등)
            // StringParser에서 빈 문자열이 필터링되지 않았거나 잘못된 값이 넘어온 경우
            throw new IllegalArgumentException("유효하지 않은 숫자 형식 또는 값이 포함되어 있습니다: " + s);
        }
    }
}