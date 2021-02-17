package JDK8Test.stream.test;

import org.junit.jupiter.api.Test;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author Gaoming
 * @Email mineok@foxmail.com
 * @Date 2021/01/20/ 21:08
 * @Description 中间操作符
 */
class StreamIntermediateOperatorTest {

    /**
     * filter: Filter the unqualified elements in the collection(过滤集合中不符合条件的元素)
     */
    @Test
    void filter() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 集合转为[流进行过滤操作]之后再转为集合
        List<String> streamToList = stringList.stream().filter(str -> str.contains("f")).collect(Collectors.toList());
        System.out.println(streamToList);
    }

    /**
     * distinct: Deduplication(去重)
     */
    @Test
    void distinct() {
        List<String> stringList = Arrays.asList("abc", "bc", "bc", "efg", "abcd", "", "jkl", "abc");
        List<String> streamToList = stringList.stream().distinct().collect(Collectors.toList());
        System.out.println(streamToList);
        // ----------------------------------------
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "z"));
        users.add(new User(1, "z"));
        users.add(new User(2, "s"));
        List<User> streamToUserList = users.stream().distinct().collect(Collectors.toList());
        System.out.println(streamToUserList);
    }

    /**
     * limit: Get the first n elements in the stream(获取流中前n个元素)
     */
    @Test
    void limit() {
        List<String> stringList = Arrays.asList("abc", "bc", "bc", "efg", "abcd", "", "jkl", "abc");
        List<String> streamToStringList = stringList.stream().limit(2).collect(Collectors.toList());
        System.out.println(streamToStringList);
    }

    /**
     * skip: Skip the first n elements in the stream(跳过流中前n个元素)
     */
    @Test
    void skip() {
        List<String> stringList = Arrays.asList("abc", "qc", "bc", "efg", "abcd", "*", "jkl", "abc");
        List<String> streamToStringList = stringList.stream().skip(2).collect(Collectors.toList());
        System.out.println(streamToStringList);
    }

    /**
     * map: Treat all elements in the stream uniformly(对流中所有元素做统一处理)
     */
    @Test
    void map() {
        List<String> stringList = Arrays.asList("abc", "qc", "bc", "efg", "abcd", "*", "jkl", "abc");
        List<String> streamToStringList = stringList.stream().map(str -> str.concat("HELLO!")).collect(Collectors.toList());
        System.out.println(streamToStringList);
    }

    /**
     * flatMap: Stream flattening, convert each value in the stream into a stream, and merge all streams into a total stream after processing
     * (流扁平化,把流中每一个值转换为一个流,处理完成后把所有流汇成一个总流)
     */
    @Test
    void flatMap() {
        List<String> stringList = Arrays.asList("abc", "qc", "bc", "efg", "abcd", "", "jkl", "abc");
        List<Character> streamToCharacterList = stringList.stream().flatMap(this::getCharacterByString).collect(Collectors.toList());
        System.out.println(streamToCharacterList);
    }

    @Test
    void mapVSflatMap() {
        List<String> stringList = Arrays.asList("abc", "qc", "bc", "efg", "abcd", "", "jkl", "abc");

        /* 流(解决层级过深问题)
           1.[a,b,c],[b,c]...[a,b,c]
           2.[a,b,c,...c]
         */
        Stream<Character> flatMap = stringList.stream().flatMap(this::getCharacterByString);
        flatMap.forEach(s -> System.out.print(s + " "));
        System.out.println();

        /* 流中流
           1.[a,b,c],[b,c]...[a,b,c]
         */
        Stream<Stream<Character>> map = stringList.stream().map(this::getCharacterByString);
        map.forEach(s -> s.forEach(ss -> System.out.print(ss + " ")));
        /*
            本质区别:
            map返回一个值
            flatMap返回一个流,多个值
         */
    }

    /**
     * sorted: Sort by criteria(排序)
     */
    @Test
    void sorted() {
        List<String> stringList = Arrays.asList("abc", "qc", "bc", "efg", "abcd", "xyz", "jkl", "abc");
        List<String> streamSortedList = stringList.stream().sorted().collect(Collectors.toList());
        System.out.println(streamSortedList);

        // ------------------数字排序----------------------------
        List<Integer> intList = Arrays.asList(123, 453, 32, 65, -1, 0, 666, 10000);
        List<Integer> intSortedList = intList.stream().sorted().collect(Collectors.toList());
        System.out.println(intSortedList);

        // ------------------汉字排序----------------------------
        List<String> ChinesecCharList = Arrays.asList("张三", "李四", "王五", "哈哈哈", "你好", "皇帝");
        // 汉字按首字母正排序(Collator.getInstance(Locale.CHINA))
        List<String> chinaCharList = ChinesecCharList.stream().sorted(Collator.getInstance(Locale.CHINA)).collect(Collectors.toList());
        // 汉字首字母倒序
        chinaCharList = ChinesecCharList.stream().sorted(Collections.reverseOrder(Collator.getInstance(Locale.CHINA))).collect(Collectors.toList());
        System.out.println(chinaCharList);

    }

    /**
     * 字符串转换为字符流
     *
     * @param str
     * @return
     */
    Stream<Character> getCharacterByString(String str) {
        ArrayList<Character> characterList = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            characterList.add(character);
        }
        return characterList.stream();
    }
}
