package com.example.moneymanager.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Entity
@Table(name = "money_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "ID")
    private Type type;

    @Column(name = "tr_date")
    private Date date;

    @NotNull
    private Double amount;

    private String Description;

}
