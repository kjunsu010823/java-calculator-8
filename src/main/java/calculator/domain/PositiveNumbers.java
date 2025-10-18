package calculator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 숫자 목록을 감싸는 일급 컬렉션입니다.
 * 숫자 변환, 유효성 검증(음수 불가), 합계 계산의 책임을 가집니다.
 */
public class PositiveNumbers {

    private final List<Integer> numbers;

    /**
     * 문자열 배열을 받아 PositiveNumbers 객체를 생성합니다.
     * 이 과정에서 각 문자열을 정수로 변환하고 유효성을 검사합니다.
     * @param numberStrings 숫자 형태의 문자열 배열
     */
    public PositiveNumbers(String[] numberStrings) {
        this.numbers = new ArrayList<>();
        List<Integer> negativeNumbers = new ArrayList<>(); // 음수를 모을 리스트

        for (String numberStr : numberStrings) {
            int number = convertToInt(numberStr);

            if (number < 0) {
                negativeNumbers.add(number); // 음수는 리스트에 추가
            }
            this.numbers.add(number);
        }

        // 모든 반복 후, 음수 리스트가 비어있지 않으면 예외를 던집니다.
        if (!negativeNumbers.isEmpty()) {
            throwNegativeException(negativeNumbers);
        }
    }

    /**
     * 문자열을 정수로 변환합니다. 변환할 수 없으면 예외를 발생시킵니다.
     * @param numberStr 변환할 문자열
     * @return 변환된 정수
     */
    private int convertToInt(String numberStr) {
        try {
            // 공백을 제거하고 변환을 시도
            return Integer.parseInt(numberStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("문자열에 숫자가 아닌 값이 포함되어 있습니다: " + numberStr, e);
        }
    }

    /**
     * 발견된 음수 목록을 기반으로 IllegalArgumentException을 발생시킵니다.
     * @param negativeNumbers 발견된 음수 목록
     */
    private void throwNegativeException(List<Integer> negativeNumbers) {
        String negativeList = negativeNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        // 테스트가 기대하는 명확한 예외 메시지를 제공합니다.
        throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + negativeList);
    }

    /**
     * 숫자 목록의 총합을 반환합니다.
     * @return 모든 숫자의 합
     */
    public int sum() {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}