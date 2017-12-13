package flatmap_example;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapper {
    public static void main(String[] args) {
        String[][] twoDimensions = {
                {"one", "two", "three"},
                {"four", "five", "six"}
        };

        Stream<String[]> ss = Arrays.stream(twoDimensions);
        Stream<String> sss = ss.flatMap(s -> Arrays.stream(s));

        sss.peek(s -> System.out.println(s))
                .collect(Collectors.toList());
        System.out.println("\n\n");

        String[][][] threeDimensions = {
                {{"one", "two", "three"}, {"four", "five", "six"}},
                {{"1", "2", "3"}, {"4", "5", "6"}},
        };

        Stream<String[][]>  ssaa3 = Stream.of(threeDimensions);
        // Stream<Stream<String []>>  sssa3 = ssaa3.map(darr2 -> Stream.of(darr2));
        Stream<String []>  ssa3 = ssaa3.flatMap(darr2 -> Stream.of(darr2));

        Stream<String> ss3 = ssa3.flatMap(a -> Stream.of(a));

        ss3.peek(s -> System.out.println(s))
                .collect(Collectors.toList());
    }
}
