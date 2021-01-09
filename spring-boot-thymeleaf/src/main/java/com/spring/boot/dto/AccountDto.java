package com.spring.boot.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MD. Zakir Hossain on 09/01/2021.
 */


@Getter
@Setter
public class AccountDto {
 
    private Long id;

    private String accountType;

    private String accountName;

    private String accountNumber;

    private Double currentBalance;

    private Long branchId;
    
    private Long bankId;
    
    private String branchName;

}
