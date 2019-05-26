package com.fziqan.dao;

import com.fziqan.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @PackageName:com.fziqan.dao
 * @ClassName:CustomerDao
 * @Description:
 * @author:FziqAn
 * @date 2019/5/25 14:55
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

}
