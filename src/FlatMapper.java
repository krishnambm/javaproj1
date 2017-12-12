import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FlatMapper {
    public static void main(String[] args) {
        String[][] twoDimensions = {
                {"one", "two", "three"},
                {"four", "five", "six"}
        };

        Stream<String []>  ss = Arrays.stream(twoDimensions);
        Stream<String> sss = ss.flatMap(s -> Arrays.stream(s));

        sss.peek(s -> System.out.println(s))
            .collect(Collectors.toList());
    }
}
