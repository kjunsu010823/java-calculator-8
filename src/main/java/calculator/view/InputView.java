package calculator.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 담당합니다.
 */
public class InputView {

    private static final String INPUT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";

    /**
     * 사용자에게 메시지를 출력하고, 콘솔로부터 문자열을 입력받아 반환합니다.
     * @return 사용자가 입력한 문자열
     */
    public static String getInput() {
        System.out.println(INPUT_MESSAGE);
        return Console.readLine();
    }
}