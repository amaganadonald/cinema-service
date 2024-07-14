package com.amagana.cinema_service.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.amagana.cinema_service.entity.Spectators;

public class CinemaBussnessLogic {

    private CinemaBussnessLogic() {}

    public static char findFirstNotRepeatingString(String str) {
        char[] ch = str.toLowerCase().toCharArray();
        int count = 0;
        for(int i=0;i<ch.length;i++) {
          for(int j=0;j<ch.length;j++) {
            if (ch[i] == ch[j])
              count++;
          }
          if (count == 1)
            return ch[i];
          count = 0;
        }
        return 0;
    }
    public static String findFirstNotRepeatingStringStream(String str) throws Exception {
       return str.chars()
                 .mapToObj(c->(char)c)
                 .map(String::valueOf)
                 .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
                 .entrySet()
                 .stream()
                 .filter(s->s.getValue()==1)
                 .findFirst()
                 .map(Map.Entry::getKey)
                 .orElseThrow(()-> new Exception("string is null"));
    }
    public static Map<Character, Long> findRepeatingString(String str) {
       return str.chars()
              .mapToObj(c-> (char) c)
             .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static boolean checkAnagram(String s1, String s2) {
        if (s1.length()!=s2.length())
           return false;
        return sortString(s2).equals(sortString(s1));
    }

    public static String sortString(String s) {
        return s.chars().mapToObj(String::valueOf).sorted(Comparator.naturalOrder()).collect(Collectors.joining(""));
    }

    public static int countNumberVowels(String sts) {
       char[] vowels = {'a', 'e', 'o', 'u', 'i'};
       int numberVowels = 0;
       for(int i=0; i<vowels.length;i++) {
         for(int j=0; j<sts.length();j++){
            if(vowels[i]==sts.charAt(j)) {
                numberVowels++;
            }
         }
       }
       return numberVowels;
    }

    public static int counterVowels(String str) {
        int count = 0;
        String vowels = "aeuio";
        for(int i=0;i<str.length();i++) {
            if(vowels.indexOf(str.charAt(i)) != -1){
                count++;
            }
        }
        return count;
    }

    public static Long counterVowelsStream(String str) {
        String vowels = "aeuio";
        return str.chars()
           .mapToObj(c->(char) c)
           .filter(c->vowels.indexOf(c)!=-1)
           .count();
    }

    public static int factorielleRecursive(int number) {
        if (number < 0)
          throw new IllegalArgumentException("number must be non negative");
        return (number == 1) || (number == 0) ? 1 : number * factorielleRecursive(number-1);
    }

    public static int factorielleLitteral(int number) {
        if (number < 0)
          throw new IllegalArgumentException("number must be not negative  with this number");
        if(number == 1 || number ==0)
          return 1;
        int j = 2;
        int factorielle = 1; 
        while(j<=number) {
            factorielle = factorielle * j;
            j++;
        }
        return factorielle;
    }

    public static int factorielleStream(int number) {
        if (number < 0)
          throw new IllegalArgumentException("number must be not negative");
        return Stream.iterate(1, n->n<=number, n->n+1).reduce(1, (a,b)->a*b);
    }

    public static int sumNumber(int n) {
        String nb = String.valueOf(n).replaceAll("[\\D]","0");
        return Stream.of(nb.split(""))
                     .mapToInt(Integer::valueOf)
                     .sum();
    }

    public static String fibonnaci(int number) {
        StringBuilder st = new StringBuilder();
        for (int i=0; i<=number; i++) {
            st.append(fibonnacireccursive(i));
        }
        return st.toString();
    }
    public static int fibonnacireccursive(int number) {
        if (number < 0)
          throw new IllegalArgumentException("number must be not negative");
       return (number == 1) || (number == 0) ? number : fibonnacireccursive(number-1) + fibonnacireccursive(number-2);
    }
    public static List<Integer> fibonnaciStream(int number) {
        int numberLimit = number + 1;
       return Stream.iterate(new int[] {0, 1}, t->new int[] {t[1], t[0]+t[1]})
                    .limit(numberLimit)
                    .map(fib->fib[0])
                    .toList();

    }

    public static List<Integer> fibonnaciLitteral(int number) {
        if (number < 0)
          throw new IllegalArgumentException("number must not be negative");
        List<Integer> fibo = new ArrayList<>();
        for(int i=0; i<=number; i++) {
            if (i == 0 || i == 1) {
              fibo.add(i);
            } else {
                fibo.add(fibo.get(i-1) + fibo.get(i-2));
            }
        }
        return fibo;
    }

    public static String reverseString(String st) {
        return IntStream.range(0, st.length())
                        .mapToObj(i->st.charAt(st.length()- i -1))
                        .map(String::valueOf)
                        .collect(Collectors.joining(""));
    }
    public static String reverseStringRecursive(String st) {
        return st.length() <=1 ? st : reverseStringRecursive(st.substring(1))+st.charAt(0);
    }

    public static String longestSubstring(String st) {
        return st.chars()
                 .mapToObj(c->(char)c)
                 .distinct()
                 .map(String::valueOf)
                 .collect(Collectors.joining(""));

    }

    public static boolean panagram(String st) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i<alphabet.length();i++){
            if(st.indexOf(alphabet.charAt(i))==-1)
              return false;
        }
        return true;
    }

    public static boolean panagramStream(String st) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String txt = alphabet.chars().mapToObj(c->(char)c).filter(s->st.indexOf(s)!=-1).map(String::valueOf)
                             .collect(Collectors.joining(""));
        return txt.equals(alphabet);
    }

    public static String reverseEachWord(String str) {
        String[] st = str.toLowerCase().replaceAll("[^a-z0-9\\s]", "").split(" ");
        return IntStream.range(0, st.length)
                 .mapToObj(i->st[st.length - i - 1])
                 .map(String::valueOf)
                 .collect(Collectors.joining(" "));
                     
    }

    public static String removeDuplicateWord(String str) {
        return str.chars().mapToObj(c->(char)c).distinct().map(String::valueOf).collect(Collectors.joining());
    }

    public static boolean checkiftwolistareequal(String st, String st2) {
       return  Collections.disjoint(st.chars().mapToObj(s->(char)s).toList(), st2.chars().mapToObj(ss->(char)ss).toList());
    }

    public static String extractOnlyUppercaseCharacter(String str) {
        return str.chars()
                  .mapToObj(c->(char)c)
                  .filter(Character::isUpperCase)
                  .map(String::valueOf)
                  .collect(Collectors.joining(""));
    }

    public static String extractOnlyDigitCharacter(String str) {
        return str.chars()
                  .mapToObj(c->(char)c)
                  .filter(Character::isDigit)
                  .map(String::valueOf)
                  .collect(Collectors.joining(""));
    }

    public static List<Integer> extractEven(int[] numbers) {
        return Arrays.stream(numbers)
                     .filter(n-> n % 2 == 0)
                     .boxed()
                     .toList();
    }
    public static List<Double> calculSquare(int[] numbers) {
        return Arrays.stream(numbers)
                     .mapToDouble(Math::sqrt)
                     .boxed()
                     .toList();
    }

    public static List<String> extractAllStringlengthEven(String str){
        return Stream.of(str.split(" "))
                     .filter(st->st.length()%2==0)
                     .toList();
    }
    public static List<String> extractAllStringstartWith(String str){
        return Stream.of(str.split(" "))
                     .map(ch->ch.substring(0, 1).toUpperCase().concat(ch.substring(1, ch.length())))
                     .toList();
    }

    public static int maxNumber(int[] nb) {
        return Arrays.stream(nb)
                     .max()
                     .orElseThrow();
    }

    public static int minNumber(int[] nb) {
        return Arrays.stream(nb)
                     .min()
                     .orElseThrow();
    }

    public static void minMaxNumber(int[] nb) {
        int max = nb[0];
        int min = nb[0];
        for (int i=0; i<nb.length;i++){
            if (nb[i] > max) {
                max = nb[i];
            }
            if (nb[i] < min) {
                min = nb[i];
            }
        }
    }

    public static List<Spectators> sortBySalaryAndAge(List<Spectators> employees) {
        return employees.stream()
                 .sorted(Comparator.comparingDouble(Spectators::getSalary).reversed()
                 .thenComparingInt(Spectators::getAge))
                 .toList();
    }

}
 