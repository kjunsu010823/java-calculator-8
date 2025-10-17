package calculator.view;

/**
 * 계산 결과 또는 오류 메시지를 출력하는 역할을 담당합니다.
 */
public class ResultView {

    private static final String RESULT_PREFIX = "결과 : ";
    private static final String ERROR_PREFIX = "오류: ";

    /**
     * 계산 결과를 형식에 맞게 출력합니다.
     * @param result 계산된 최종 값
     */
    public static void printResult(int result) {
        System.out.println(RESULT_PREFIX + result);
    }

    /**
     * 발생한 오류 메시지를 형식에 맞게 출력합니다.
     * @param message 발생한 예외의 메시지
     */
    public static void printError(String message) {
        System.err.println(ERROR_PREFIX + message);
    }
}