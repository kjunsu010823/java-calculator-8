package calculator.domain;

import java.util.regex.Pattern;

/**
 * 입력된 문자열에서 구분자를 기준으로 숫자 문자열들을 분리하는 역할을 담당합니다.
 */
public class StringParser {

    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";

    // Console.readLine() 조건에 맞춰 parse() 메서드 수정
    public static String[] parse(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }

        // 정규식 대신 문자열 함수(indexOf, startsWith)를 사용하여 커스텀 구분자 형식인지 확인
        // Console.readLine()은 \n을 포함한 전체 라인을 읽어온다는 가정을 활용
        if (text.startsWith("//") && text.contains("\n")) {
            return parseWithCustomDelimiter(text);
        }

        // 커스텀 구분자가 없는 경우: 기본 구분자 외에 \n 줄 바꿈 문자도 분리 기준으로 추가
        String defaultDelimiterAndNewline = DEFAULT_DELIMITER_REGEX + "|\n";
        return text.split(defaultDelimiterAndNewline);
    }

    // 커스텀 구분자 파싱 로직 (정규식 대신 문자열 함수 사용)
    private static String[] parseWithCustomDelimiter(String text) {
        // \n의 위치를 찾아 문자열 함수로 안전하게 추출
        int newLineIndex = text.indexOf('\n');

        // 1. 커스텀 구분자 추출 (예: ";")
        // "//" (2글자)와 "\n" 사이의 문자를 추출합니다.
        String customDelimiter = text.substring(2, newLineIndex);

        // 2. 숫자 문자열 추출 (커스텀 구분자 형식 이후)
        // \n 뒤의 문자열부터 끝까지를 추출하고 trim()
        String numbersText = text.substring(newLineIndex + 1).trim();

        // 3. 구분자 통합: 기본 구분자, 커스텀 구분자, 줄 바꿈 문자를 합칩니다.
        String customRegex = Pattern.quote(customDelimiter);
        String combinedDelimiterRegex = DEFAULT_DELIMITER_REGEX + "|" + customRegex + "|\n";

        return numbersText.split(combinedDelimiterRegex);
    }
}