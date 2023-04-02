package com.example.bumblebeebackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Lease {

    @Id
    @GeneratedValue
   private Long customerLeaseID;

   private String customerName;

   private String DOB;

   private Double loanBalance;

    public Long getCustomerLeaseID() {
        return customerLeaseID;
    }

    public void setCustomerLeaseID(Long customerLeaseID) {
        this.customerLeaseID = customerLeaseID;
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

    public Double getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(Double loanBalance) {
        this.loanBalance = loanBalance;
    }

    public String getInstallementPlan() {
        return installementPlan;
    }

    public void setInstallementPlan(String installementPlan) {
        this.installementPlan = installementPlan;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    private String installementPlan;

   private String planStatus;

}
