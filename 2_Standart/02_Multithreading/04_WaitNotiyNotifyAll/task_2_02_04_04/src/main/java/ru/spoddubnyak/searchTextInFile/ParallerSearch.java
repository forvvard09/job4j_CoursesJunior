package ru.spoddubnyak.searchTextInFile;

import java.util.List;

public class ParallerSearch {
    private String root;
    private String text;
    private List<String> exst;

    public ParallerSearch(String root, String text) {
        this.root = root;
        this.text = text;
        //this.exst = ""
    }

    public ParallerSearch(String root, String text, List<String> exst) {

        this.root = root;
        this.text = text;
        this.exst = exst;
    }
}
