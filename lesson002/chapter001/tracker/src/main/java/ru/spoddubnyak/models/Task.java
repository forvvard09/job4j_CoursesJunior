package ru.spoddubnyak.models;

public class Task extends Item {
    public Task(String name1, String desc1) {
        name = name1;
        description = desc1;
    }

    public String calculatePrice() {
        return "100%";
    }
}
