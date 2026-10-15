package com.example.quanlychitieu;

import java.io.Serializable;

public class Expense implements Serializable {
    private String name;
    private long amount;
    private String category;
    private String date;   // dd/MM/yyyy
    private String note;

    public Expense(String name, long amount, String category, String date, String note) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    public String getName() { return name; }
    public long getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public String getNote() { return note; }
}
