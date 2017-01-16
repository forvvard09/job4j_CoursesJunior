/**
 * Created by Админ on 15.01.2017.
 */
public class Square implements Strategy{
    @Override
    public String draw(int side) {
        String answer = "-";
        for (int i = 0; i < side -1 ; i++) {
           answer = answer + "-";
        }
        for (int i = 0; i < side-1; i++) {
            answer = answer + "|" + System.getProperty("line.separator");
        }
        return answer;
    }
}
