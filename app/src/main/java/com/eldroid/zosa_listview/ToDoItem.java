package com.eldroid.zosa_listview;

public class ToDoItem {
    private boolean isChecked;
    private String text;
    private int imageResource;

    public ToDoItem(boolean isChecked, String text, int imageResource) {
        this.isChecked = isChecked;
        this.text = text;
        this.imageResource = imageResource;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
