package com.example.bumblebeebackend.model;

public class Lease {
    private Long customerID;
    private String customerName;

    private String DOB;

    public Long getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public Integer getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(Integer loanBalance) {
        this.loanBalance = loanBalance;
    }

    public String getUsedPlan() {
        return usedPlan;
    }

    public void setUsedPlan(String usedPlan) {
        this.usedPlan = usedPlan;
    }

    public String getInstallmentPlan() {
        return installmentPlan;
    }

    public void setInstallmentPlan(String installmentPlan) {
        this.installmentPlan = installmentPlan;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    private Integer loanBalance;

    private String usedPlan;

    private String installmentPlan;
}
