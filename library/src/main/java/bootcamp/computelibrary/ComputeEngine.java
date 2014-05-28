package bootcamp.computelibrary;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ComputeEngine {
    public static Map<Integer, Long> aggregate(List<Integer> integerList) {
        return integerList.stream()
                .filter(i -> i >= 0)
                .collect(Collectors.groupingBy(Function.<Integer>identity(), Collectors.counting()));
    }
}