package com.spring.boot.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MD. Zakir Hossain on 09/01/2021.
 */

@Getter
@Setter
public class BranchDto {

    private Long id;

    private String branchName;

    private Long bankId;
    
    private String bankName;

}
