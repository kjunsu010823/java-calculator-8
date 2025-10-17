package calculator.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 입력된 문자열에서 구분자를 기준으로 숫자 문자열들을 분리하는 역할을 담당합니다.
 */
public class StringParser {

    // "//(.)\n(.*)" 정규 표현식: 커스텀 구분자를 찾기 위한 패턴
    // 1. "//" : "//" 문자와 일치
    // 2. (.)  : 첫 번째 캡처 그룹. 임의의 문자 하나 (커스텀 구분자)
    // 3. "\n" : 개행 문자와 일치
    // 4. (.*) : 두 번째 캡처 그룹. 개행을 제외한 모든 문자 0개 이상 (숫자 부분)
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\n(.*)");
    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";

    /**
     * 문자열을 파싱하여 숫자 부분만 문자열 배열로 반환합니다.
     *
     * @param text 파싱할 전체 문자열
     * @return 구분자로 분리된 숫자 문자열 배열
     */
    public static String[] parse(String text) {
        // null 또는 빈 문자열은 빈 배열로 처리
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }

        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(text);
        if (matcher.find()) {
            // 커스텀 구분자가 있는 경우
            String customDelimiter = matcher.group(1);
            String numbersText = matcher.group(2);
            // Pattern.quote()는 정규식의 특수문자를 일반 문자로 처리해줌 (e.g., "*", "+")
            return numbersText.split(Pattern.quote(customDelimiter));
        }

        // 커스텀 구분자가 없는 경우, 기본 구분자로 분리
        return text.split(DEFAULT_DELIMITER_REGEX);
    }
}