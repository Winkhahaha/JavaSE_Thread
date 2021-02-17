package JDK8Test.stream.test;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Gaoming
 * @Email mineok@foxmail.com
 * @Date 2021/01/20/ 21:08
 * @Description 终止操作符
 */
class StreamTerminationOperatorTest {

    /**
     * anyMatch: Whether an element in the set satisfies the condition(集合中是否有一个元素满足条件)
     */
    @Test
    void anyMatch() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 集合转为[流进行过滤操作]之后再转为集合
        boolean match = stringList.stream().anyMatch(str -> str.contains("bc"));
        System.out.println(match);
    }

    /**
     * allMatch: Whether the elements in the set meet the condition(集合中元素是否都满足条件)
     */
    @Test
    void allMatch() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        boolean match = stringList.stream().allMatch(str -> str.length() > 3);
        System.out.println(match);
    }

    /**
     * noneMatch: Whether none of the elements in the set meet the condition(集合中元素是否都不满足条件)
     */
    @Test
    void noneMatch() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        boolean match = stringList.stream().noneMatch(str -> str.length() > 5);
        System.out.println(match);
    }

    /**
     * findAny: Return any element in the collection(返回集合中任意元素)
     */
    @Test
    void findAny() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        for (int i = 0; i < 100; i++) {
            Optional<String> optional = stringList.parallelStream().findAny();
            System.out.println(optional.orElse(null));
        }
    }

    /**
     * findFirst: Return the first element in the collection(返回集合中第一个元素)
     */
    @Test
    void findFirst() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        Optional<String> optional = stringList.stream().findFirst();
        System.out.println(optional.orElse(null));
    }

    /**
     * forEach: Loop(循环)
     */
    @Test
    void forEach() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        stringList.forEach(System.out::println);
    }

    /**
     * collect: Collector, convert the stream to other types(收集器,将流转换为其它类型)
     */
    @Test
    void collect() {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        Set<String> collect = stringList.stream().collect(Collectors.toSet());
        System.out.println(collect);
        // ------------------------------------------------------
        Map<String, String> stringMap = stringList.stream().collect(Collectors.toMap(k -> "$$" + k, v -> v + "%%", (oldValue, newValue) -> newValue));
        System.out.println(stringMap);
    }

    /**
     * reduce: All elements in the stream can be combined repeatedly to get a value(可以将流中所有元素反复结合起来得到一个值)
     */
    @Test
    void reduce() {
        List<String> stringList = Arrays.asList("abc", "**", "bc", "efg", "abcd", "$", "jkl");
        Optional<String> reduce = stringList.stream().reduce((result, item) -> (result + item).replace("a", "A"));
        System.out.println(reduce.orElse(null));
    }

    /**
     * count: Get the number of elements in the collection(获取集合中元素数量)
     */
    @Test
    void count() {
        List<String> stringList = Arrays.asList("abc", "**", "bc", "efg", "abcd", "$", "jkl");
        long count = stringList.stream().count();
        System.out.println(count);
    }
}
