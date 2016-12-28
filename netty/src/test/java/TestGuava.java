import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

/**
 * Created by nick on 16/12/21.
 */
public class TestGuava {

    public static void main(String[] args) {

    }

    //缓存测试
    public static void testCache() {

    }

    /**
     * 区间测试
     * 并、交、补
     */
    public static void testRange() {
        print(Range.openClosed(0, 10));
        print(Range.open(0, 10));
        print(Range.closed(0, 10));
        print(Range.closedOpen(0,10));
        print(Range.downTo(10, BoundType.OPEN));
        print(Range.downTo(10, BoundType.CLOSED));
        print(Range.upTo(10, BoundType.OPEN));
        print(Range.upTo(10, BoundType.CLOSED));
    }

    public static void print(Range range) {
        int number = 10;
        System.out.print(range);
        System.out.println(range.contains(number));
    }
}
