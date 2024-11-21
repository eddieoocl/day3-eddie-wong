import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        String[] words = sentence.split(SPACE_REGEX);

        Map<String, Long> wordToWordFrequency = getWordToWordFrequency(words);

        return getJoinedWordFrequencies(wordToWordFrequency);
    }

    private static String getJoinedWordFrequencies(Map<String, Long> wordToWordFrequency) {
        return wordToWordFrequency.entrySet().stream()
                .sorted((current, next) ->  (int) (next.getValue() - current.getValue()))
                .map(wordFrequency -> wordFrequency.getKey() + " " + wordFrequency.getValue())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private Map<String, Long> getWordToWordFrequency(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
