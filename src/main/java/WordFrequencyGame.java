import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(SPACE_REGEX);

                List<WordFrequency> wordFrequencies = getInitialWordFrequencies(words);

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencies = getWordFrequencyMap(wordFrequencies);

                wordFrequencies = getFinalWordFrequencies(wordToWordFrequencies);

                return getJoinedWordFrequencies(wordFrequencies);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private static String getJoinedWordFrequencies(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private static List<WordFrequency> getFinalWordFrequencies(Map<String, List<WordFrequency>> wordToWordFrequencies) {
        return wordToWordFrequencies.entrySet()
                .stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((word1, word2) -> word2.getWordCount() - word1.getWordCount())
                .collect(Collectors.toList());
    }

    private static List<WordFrequency> getInitialWordFrequencies(String[] words) {
        return Arrays.stream(words).map(word -> new WordFrequency(word, 1)).toList();
    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream().collect(Collectors.groupingBy(WordFrequency::getWord));
    }
}
