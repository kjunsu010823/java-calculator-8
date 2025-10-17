package calculator;

import calculator.domain.StringCalculator;
import calculator.view.InputView;
import calculator.view.ResultView;

public class Application {
    public static void main(String[] args) {
        try {
            // 1. [View] 사용자로부터 입력을 받는다.
            String input = InputView.getInput();

            // 2. [Domain] 핵심 로직을 처리한다.
            StringCalculator calculator = new StringCalculator();
            int result = calculator.add(input);

            // 3. [View] 결과를 출력한다.
            ResultView.printResult(result);

        } catch (IllegalArgumentException e) {
            // 4. [Domain] 로직 처리 중 발생하는 예외를 잡아서 출력한다.
            ResultView.printError(e.getMessage());
        }
    }
}