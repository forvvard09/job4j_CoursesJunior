/**
 * Created by Админ on 15.01.2017.
 */
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new Triangle());
        System.out.println("triangle" + context.executeStrategy(1));

        context = new Context(new Square());
        System.out.println("square" + context.executeStrategy(1));

    }
}
