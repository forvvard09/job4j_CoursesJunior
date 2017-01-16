/**
 * Created by Админ on 15.01.2017.
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public String executeStrategy(int side){
         return strategy.draw(side);
    }
}
