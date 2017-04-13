package ru.spoddubnyak;

import java.util.Comparator;

public class UserLengthNameComporator implements Comparator<User>{

    @Override
    public int compare(User o1, User o2) {
        return o1.getName().length() - o2.getName().length();
    }
}
