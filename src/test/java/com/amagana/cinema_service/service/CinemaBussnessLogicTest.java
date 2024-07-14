package com.amagana.cinema_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CinemaBussnessLogicTest {

    @Test
    void givenString_findNonRepeating_ReturnChar() {
        assertEquals('i', CinemaBussnessLogic.findFirstNotRepeatingString("aabbiddef"));
    }

    @Test
    void givenString_findNonRepeating_ReturnCharStream() throws Exception {
        assertEquals("i", CinemaBussnessLogic.findFirstNotRepeatingStringStream("aabibddef"));
    }

    @Test
    void givznString_countChar() {
        Map<Character, Long> map = Map.of('a', 2L, 'b', 2L, 'd', 2L, 'e', 1L, 'f', 1L);
        assertEquals(map, CinemaBussnessLogic.findRepeatingString("aabbddef"));
    }

    @Test
    void given2String_chechAnagram_ReturnTrue() {
        assertTrue(CinemaBussnessLogic.checkAnagram("amagana", "aamnaga"));
    }

    @Test
    void given2String_chechAnagram_ReturnFalse() {
        assertFalse(CinemaBussnessLogic.checkAnagram("amagana", "aamnagaf"));
    }

    @Test
    void givenString_countNumberOfvowels_returnInt() {
        assertEquals(4, CinemaBussnessLogic.countNumberVowels("amagana"));
    }

    @Test
    void givenString_countNumbersOfvowels_returnInt() {
        assertEquals(4, CinemaBussnessLogic.counterVowels("amagana"));
    }

    @Test
    void givenString_countNumbersOfvowelsStream_returnInt() {
        assertEquals(4, CinemaBussnessLogic.counterVowelsStream("amagana"));
    }

    @Test
    void calcul_factorielleRecursive() {
        assertEquals(3628800, CinemaBussnessLogic.factorielleRecursive(10));
    }

    @Test
    void calcul_factorielleLitteral() {
        assertEquals(3628800, CinemaBussnessLogic.factorielleLitteral(10));
    }

    @Test
    void calcul_factorielleStream() {
        assertEquals(3628800, CinemaBussnessLogic.factorielleStream(10));
    }

    @Test
    void calcul_fibonnacRecurssivei() {
        assertEquals("011235", CinemaBussnessLogic.fibonnaci(5));
    }

    @Test
    void calcul_fibonnaciStream() {
        List<Integer> lt = List.of(0,1,1,2,3, 5);
        assertEquals(lt, CinemaBussnessLogic.fibonnaciStream(5)); 
    }

    @Test
    void calcul_fibonnacieLiiterral() {
        List<Integer> lt = List.of(0,1,1,2,3, 5);
        assertEquals(lt, CinemaBussnessLogic.fibonnaciLitteral(5)); 
    }

    @Test
    void calculreverseString(){
        assertEquals("ecirual", CinemaBussnessLogic.reverseString("laurice"));
    }

    @Test
    void calculreverseStringRecursive(){
        assertEquals("ecirual", CinemaBussnessLogic.reverseStringRecursive("laurice"));
    }

    @Test
    void longest_substring_without_repetition() {
        assertEquals("amgn", CinemaBussnessLogic.longestSubstring("amagana"));
    }

    @Test
    void check_if_panagram_ReturnTrue() {
        assertTrue(CinemaBussnessLogic.panagram("the quick brown fox jumps over the lazy dog"));
    }

    @Test
    void check_if_panagram_ReturnFalse() {
        assertFalse(CinemaBussnessLogic.panagram("amagana"));
    }

    @Test
    void check_if_panagram_ReturnTrueStream() {
        assertTrue(CinemaBussnessLogic.panagramStream("the quick brown fox jumps over the lazy dog"));
    }

    @Test
    void check_if_panagram_ReturnFalseStream() {
        assertFalse(CinemaBussnessLogic.panagramStream("amagana"));
    }

    @Test
    void reverseEachWord() {
        assertEquals("world hello", CinemaBussnessLogic.reverseEachWord("Hello #@World"));
    }

    @Test
    void reversecheckif2stringEquals() {
        assertEquals("progamin", CinemaBussnessLogic.removeDuplicateWord("programming"));
    }

    @Test
    void extractOnlyUppercaseCaract() {
        assertEquals("AG", CinemaBussnessLogic.extractOnlyUppercaseCharacter("AmaGana"));
    }

    @Test
    void extractOnlyDigit() {
        assertEquals("12", CinemaBussnessLogic.extractOnlyDigitCharacter("Amag1nana2"));
    }

    @Test
    void extractEvenNumbers() {
        int[] givenNumbers = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> expected = List.of(2, 4, 6, 8, 10);
        assertEquals(expected, CinemaBussnessLogic.extractEven(givenNumbers));
    }

    @Test
    void exttactAllStringEvenLength() {
        List<String> str = List.of("Donald");
        String st = "Donald Laurice Evann";
        assertEquals(str, CinemaBussnessLogic.extractAllStringlengthEven(st));
    }

    @Test
    void firstLetterUppercase() {
        List<String> str = List.of("Donald", "Laurice", "Evann");
        String st = "donald laurice evann";
        assertEquals(str, CinemaBussnessLogic.extractAllStringstartWith(st));
    }

    @Test
    void extractdigitNumber() {
        assertEquals(16, CinemaBussnessLogic.sumNumber(-268));
    }

}
 