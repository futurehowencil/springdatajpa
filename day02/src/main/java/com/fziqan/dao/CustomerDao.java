package com.fziqan.dao;

import com.fziqan.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @PackageName:com.fziqan.dao
 * @ClassName:CustomerDao
 * @Description:
 * @author:FziqAn
 * @date 2019/5/25 14:55
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    /**
     * 使用jpql的方式查询
     *
     * @return
     */
//    @Query(value = "select c from Customer c")
    @Query(value = "from Customer ")
    List<Customer> findAllCustomer();

    /**
     * 使用jpql的方式查询。?1代表参数的占位符，其中1对应方法中的参数索引
     *
     * @param custName
     * @return
     */
    @Query(value = "from Customer where custName = ?1")
    Customer findCustomer(String custName);

    /**
     * 更新和删除操作 需要添加 @Modifying注解
     *
     * @param custName
     * @param custId
     */
    @Query(value = "update Customer set custName = ?1 where custId = ?2")
    @Modifying
    void updateCustomer(String custName, Long custId);

    /**
     * 根据id删除
     *
     * @param custId
     */
    @Query(value = "delete from Customer where custId = ?1")
    @Modifying
    void deleteCustomer(Long custId);

    /**
     * 使用SQL语句进行查询
     *
     * @return
     */
    @Query(value = "select * from cst_customer", nativeQuery = true)
    List<Customer> findAllSql();

    /**
     * 方法命名方式查询（根据用户名称查询用户）
     * @param custName
     * @return
     */
    Customer findByCustName(String custName);
}
