package com.spring.boot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.domain.Branch;

/**
 * Created by Asad on 12/14/2020.
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {

	List<Branch> findAllByOrderByBranchNameAsc();

	List<Branch> findByBank_BankId(Long bankId);
}
