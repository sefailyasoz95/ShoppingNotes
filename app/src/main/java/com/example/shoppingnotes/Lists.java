package com.example.shoppingnotes;

public class Lists {
    private int list_id;
    private String listName;

    public Lists() {
    }

    public Lists(int list_id, String listName) {
        this.list_id = list_id;
        this.listName = listName;
    }

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
