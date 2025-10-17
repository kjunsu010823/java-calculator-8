package calculator.domain;

/**
 * 문자열 덧셈 계산기의 메인 로직을 담당합니다.
 * 문자열 파싱과 숫자 합산의 흐름을 제어합니다.
 */
public class StringCalculator {

    /**
     * 입력된 문자열을 기반으로 숫자들의 합을 계산하여 반환합니다.
     *
     * @param text 사용자가 입력한 원본 문자열
     * @return 계산된 숫자들의 합
     */
    public int add(String text) {
        // 1. StringParser에 문자열 파싱을 위임한다.
        String[] numberStrings = StringParser.parse(text);

        // 2. 파싱 결과가 비어있으면 0을 반환한다.
        if (numberStrings.length == 0) {
            return 0;
        }

        // 3. PositiveNumbers에 숫자 목록의 생성과 합산을 위임한다.
        PositiveNumbers positiveNumbers = new PositiveNumbers(numberStrings);
        return positiveNumbers.sum();
    }
}