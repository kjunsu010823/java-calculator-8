package calculator.domain;

import java.util.regex.Pattern;

/**
 * 입력된 문자열에서 구분자를 기준으로 숫자 문자열들을 분리하는 역할을 담당합니다.
 */
public class StringParser {

    // 기본 구분자 (쉼표 또는 콜론)
    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";

    /**
     * 입력 문자열을 파싱하여 숫자 문자열 배열로 반환합니다.
     * 커스텀 구분자 형식("//;\\n1;2;3" 등)을 처리합니다.
     *
     * @param text 사용자가 입력한 문자열
     * @return 분리된 숫자 문자열 배열
     */
    public static String[] parse(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }

        // 커스텀 구분자 형식("//"로 시작하고 "\n"을 포함)인지 확인
        if (text.startsWith("//") && text.contains("\n")) {
            return parseWithCustomDelimiter(text);
        }

        // 커스텀 구분자가 없는 경우: 기본 구분자 외에 '\n' 줄 바꿈 문자를 분리 기준으로 사용
        // 정규식 뒤에 '+'를 붙여 구분자가 연속될 경우 빈 문자열이 발생하는 것을 방지
        String defaultDelimiterAndNewline = DEFAULT_DELIMITER_REGEX + "|\n";
        return text.split(defaultDelimiterAndNewline + "+");
    }

    /**
     * 커스텀 구분자 형식을 파싱합니다.
     * @param text 커스텀 구분자 형식의 문자열 (예: "//;\\n1;2;3")
     * @return 분리된 숫자 문자열 배열
     */
    private static String[] parseWithCustomDelimiter(String text) {
        int newLineIndex = text.indexOf('\n');

        // \n이 "//" 뒤에 있어야 유효한 형식입니다.
        if (newLineIndex <= 2) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다.");
        }

        // 1. 커스텀 구분자 추출 (예: ";")
        String customDelimiter = text.substring(2, newLineIndex);

        // 2. 숫자 문자열 추출 (커스텀 구분자 형식 이후)
        String numbersText = text.substring(newLineIndex + 1).trim();

        // 3. 구분자 통합: 기본 구분자, 커스텀 구분자를 합칩니다. (여기서 '\n'은 이미 분리됨)
        // Pattern.quote를 사용하여 특수문자가 포함된 구분자도 안전하게 처리
        String customRegex = Pattern.quote(customDelimiter);

        // 커스텀 구분자 형식에서는 이미 줄바꿈으로 정의부가 끝났으므로,
        // 숫자 텍스트(numbersText)를 기본 구분자(`[,:]`)와 커스텀 구분자로만 분리합니다.
        String combinedDelimiterRegex = DEFAULT_DELIMITER_REGEX + "|" + customRegex;

        // 정규식 뒤에 '+'를 붙여 구분자가 연속될 경우 빈 문자열이 발생하는 것을 방지
        return numbersText.split(combinedDelimiterRegex + "+");
    }
}