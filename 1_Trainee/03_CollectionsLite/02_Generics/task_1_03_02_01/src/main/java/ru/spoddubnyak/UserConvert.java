package ru.spoddubnyak;

import java.util.HashMap;
import java.util.List;

/**
 * Class .
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.04.2017
 */
public class UserConvert {

    /**
     * Method onverts the list of users to HashMap<Integer, User>.
     *
     * @param list - a list of users
     * @return HashMap<Integer, User>
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> hashMappUser = new HashMap<>();
        for (User user : list) {
            hashMappUser.put(user.getId(), user);
        }
        return hashMappUser;
    }
}
