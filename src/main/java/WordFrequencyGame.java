import java.util.*;

public class WordFrequencyGame {
    public String getWordFrequency(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencies.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencies = getWordFrequencyMap(wordFrequencies);

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequencies.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordFrequencyList.add(wordFrequency);
                }
                wordFrequencies = wordFrequencyList;

                wordFrequencies.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencies) {
                    String wordFrequencyString = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(wordFrequencyString);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequencies = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencies) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordToWordFrequencies.containsKey(wordFrequency.getWord())) {
                ArrayList wordFrquencies = new ArrayList<>();
                wordFrquencies.add(wordFrequency);
                wordToWordFrequencies.put(wordFrequency.getWord(), wordFrquencies);
            } else {
                wordToWordFrequencies.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordToWordFrequencies;
    }
}
