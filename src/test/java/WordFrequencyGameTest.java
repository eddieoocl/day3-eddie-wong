import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WordFrequencyGameTest {

    @Test
    public void should_get_the_1_when_input_the() throws Exception {
        //Given
        String word = "the";
        String expectResult = "the 1";
        validate_WordFrequency_words_process_to_expected_word(word, expectResult);
    }

    @Test
    public void should_process_two_words() throws Exception {
        //Given
        String word = "the is";
        String expectResult = "the 1\nis 1";
        validate_WordFrequency_words_process_to_expected_word(word, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_spaces() throws Exception {
        //Given
        String word = "the      is";
        String expectResult = "the 1\nis 1";
        validate_WordFrequency_words_process_to_expected_word(word, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_enter() throws Exception {
        //Given
        String word = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_WordFrequency_words_process_to_expected_word(word, expectResult);
    }

    @Test
    public void should_process_two_same_words_with_sorted() throws Exception {
        //Given
        String word = "the the is";
        String expectResult = "the 2\nis 1";
        validate_WordFrequency_words_process_to_expected_word(word, expectResult);
    }

    @Test
    public void should_process_sorted_with_count_descending() throws Exception {
        //Given
        String word = "the is is";
        String expectResult = "is 2\nthe 1";
        validate_WordFrequency_words_process_to_expected_word(word, expectResult);
    }

    private void validate_WordFrequency_words_process_to_expected_word(String word, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String result = game.getWordFrequency(word);
        //Then
        assertThat(result).isEqualTo(expectResult);
    }
}
