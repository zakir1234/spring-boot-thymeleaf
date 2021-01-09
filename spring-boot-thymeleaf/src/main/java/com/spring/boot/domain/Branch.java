package com.spring.boot.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Md. Zakir Hossain on 09/01/2021.
 */

@Entity
@Table(name = "branch")
@Getter
@Setter
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "branch_name")
    private String branchName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

}
