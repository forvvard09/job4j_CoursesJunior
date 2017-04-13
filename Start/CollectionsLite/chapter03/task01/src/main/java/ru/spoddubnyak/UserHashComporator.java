package ru.spoddubnyak;

import java.util.Comparator;

public class UserHashComporator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.hashCode() - o2.hashCode();
    }
}
