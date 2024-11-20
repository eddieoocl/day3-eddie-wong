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

                List<WordFrequency> wordFrequencies = Arrays.stream(words).map(word -> new WordFrequency(word, 1)).toList();

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencies = getWordFrequencyMap(wordFrequencies);

                wordFrequencies = wordToWordFrequencies.entrySet()
                        .stream()
                        .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                        .collect(Collectors.toList());

                wordFrequencies.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                String joiningString = wordFrequencies.stream()
                                             .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                                             .collect(Collectors.joining(LINE_BREAK));
                return joiningString;
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream().collect(Collectors.groupingBy(WordFrequency::getWord));
    }
}
