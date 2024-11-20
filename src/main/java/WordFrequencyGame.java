import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        }
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(SPACE_REGEX);

        Map<String, Long> wordToWordFrequency = getWordToWordFrequency(words);
        List<WordFrequency> wordFrequencies = getWordFrequencies(wordToWordFrequency);

        return getJoinedWordFrequencies(wordFrequencies);
    }

    private static String getJoinedWordFrequencies(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private static List<WordFrequency> getWordFrequencies(Map<String, Long> wordToWordFrequency) {
        return wordToWordFrequency.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))
                .sorted((word1, word2) -> word2.getWordCount() - word1.getWordCount())
                .collect(Collectors.toList());
    }

    private Map<String, Long> getWordToWordFrequency(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
