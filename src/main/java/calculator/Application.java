package calculator;

import calculator.domain.StringCalculator;
import calculator.view.InputView;
import calculator.view.ResultView;

/**
 * 애플리케이션의 실행 흐름을 제어합니다.
 */
public class Application {
    public static void main(String[] args) {
        try {
            // 1. [View] 사용자로부터 입력을 받는다.
            String input = InputView.getInput();

            // 2. [Domain] 핵심 로직을 처리하고 결과를 얻는다.
            StringCalculator calculator = new StringCalculator();
            int result = calculator.add(input);

            // 3. [View] 결과를 출력한다.
            ResultView.printResult(result);

        } catch (IllegalArgumentException e) {
            // 4. [Domain] 로직 처리 중 발생하는 IllegalArgumentException을 잡아서 오류를 출력한다.
            ResultView.printError(e.getMessage());
        } catch (Exception e) {
            // 예상치 못한 다른 예외 처리
            ResultView.printError("예상치 못한 오류가 발생했습니다.");
        }
    }
}
