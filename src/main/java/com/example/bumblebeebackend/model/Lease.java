package com.example.bumblebeebackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_entity_seq")
    @SequenceGenerator(name = "my_entity_seq", sequenceName = "MY_ENTITY_SEQ", initialValue = 50000, allocationSize = 1)
   private Long customerLeaseID;

   private String customerName;

   private Date dateOfBirth;

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(Double loanBalance) {
        this.loanBalance = loanBalance;
    }

    public String getInstallmentPlan() {
        return installmentPlan;
    }

    public void setInstallmentPlan(String installmentPlan) {
        this.installmentPlan = installmentPlan;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    private String installmentPlan;

   private String planStatus;

}
