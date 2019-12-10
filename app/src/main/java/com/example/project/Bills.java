package com.example.project;

public class Bills {
    private String billTitle, billTotalAmount, billItemAmount, billDateDay, billDateMonth, billDateYear;

    public Bills(String billTitle, String billTotalAmount, String billItemAmount, String billDateDay, String billDateMonth, String billDateYear) {
        this.billTitle = billTitle;
        this.billTotalAmount = billTotalAmount;
        this.billItemAmount = billItemAmount;
        this.billDateDay = billDateDay;
        this.billDateMonth = billDateMonth;
        this.billDateYear = billDateYear;
    }

    public String getBillTitle() {
        return billTitle;
    }

    public String getBillTotalAmount() {
        return billTotalAmount;
    }

    public String getBillItemAmount() {
        return billItemAmount;
    }

    public String getBillDateDay() {
        return billDateDay;
    }

    public String getBillDateMonth() {
        return billDateMonth;
    }

    public String getBillDateYear() {
        return billDateYear;
    }
}
