package com.spring.boot.repo;

import com.spring.boot.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by MD. Zakir Hossain on 09/01/2021.
 */
public interface AccountRepository extends JpaRepository<Account,Long> {


     Account findByAccountNumber(Integer accountNo);

	public List<Account> findAllByOrderByAccountNameAsc();
}

