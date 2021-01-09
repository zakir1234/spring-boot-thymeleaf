package com.spring.boot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.domain.Bank;

/**
 * Created by Md. Zakir on 09/01/2021.
 */
public interface BankRepository extends JpaRepository<Bank,Long> {

	List<Bank> findAllByOrderByBankNameAsc();

}
