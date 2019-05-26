package com.fziqan.test;



import com.fziqan.dao.CustomerDao;
import com.fziqan.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @PackageName:com.fziqan.test
 * @ClassName:CustomerDaoTest
 * @Description:
 * @author:FziqAn
 * @date 2019/5/25 15:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 保存
     */
    @Test
    public void saveTest() {
        Customer customer = new Customer();
        customer.setCustName("你好");
        customerDao.save(customer);
    }

    /**
     * 修改客户：调用save(obj)方法
     * 对于save方法的解释：如果执行此方法是对象中存在id属性，即为更新操作会先根据id查询，再更新
     * 如果执行此方法中对象中不存在id属性，即为保存操作
     */
    @Test
    public void updateTest() {
        //1.根据id查询用户
        Customer one = customerDao.findOne(2L);
        //2.修改用户名称
        one.setCustName("老王");
        //3.执行更新
        customerDao.save(one);
    }

    /**
     * 根据id删除：调用delete(id)方法
     */
    @Test
    public void delete() {
        customerDao.delete(5L);
    }

    /**
     * 根据id查询：调用findOne(id)方法
     * 立即加载
     */
    @Test
    public void findOne() {
        Customer one = customerDao.findOne(2L);
        //即使没有打印输出语句 一样会执行SQL语句 即立即加载
        // System.out.println(one);
    }

    /**
     * 根据id查询
     * 延迟加载
     */
    @Test
    @Transactional
    public void getOne() {
        Customer one = customerDao.getOne(2L);
        //当执行到打印输出语句的时候 才会执行SQL语句 即延迟加载
        // System.out.println(one);
    }

    //下面使用自定义的方法操作数据库

    /**
     * 使用自定义方法
     * 查询所有
     */
    @Test
    public void findAllCustomer() {
        List<Customer> customerList = customerDao.findAllCustomer();
        if (customerList != null & customerList.size() > 0) {
            for (Customer customer : customerList) {
                System.out.println(customer);
            }
        }
    }

    /**
     * 使用自定义方法
     * 根据用户名字查询单个用户
     * 这个方法为立即加载
     */
    @Test
    public void findCustomer() {
        Customer customer = customerDao.findCustomer("老王");
        // System.out.println(customer);
    }

    /**
     * 使用自定义方法
     * 根据用户id更新用户名字
     * 根据用户id删除
     * org.hibernate.hql.internal.QueryExecutionRequestException: Not supported for DML operations:
     * *    当前是一个查询异常： 查询执行的时候不执行DML语句
     * * javax.persistence.TransactionRequiredException: Executing an update/delete query
     * *   :事务异常
     * * springdatajpa 默认将数据进行回滚了，设置不回滚
     */
    @Test
    @Transactional
    @Rollback(false)
    public void updateCustomer() {
//        customerDao.updateCustomer("老陈", 2L);
        customerDao.deleteCustomer(7L);
    }

    /**
     * 使用自定义方法
     * 不使用jpql方式，而改为SQL语句进行操作数据库
     */
    @Test
    public void findAllSql() {
        List<Customer> customerList = customerDao.findAllSql();
        if (customerList != null && customerList.size() > 0) {
            for (Customer customer : customerList) {
                System.out.println(customer);
            }
        }
    }

    /**
     * 方法命名方式查询（根据用户名称查询用户）
     */
    @Test
    public void findByCustName() {
        Customer customer = customerDao.findByCustName("老陈");
         System.out.println(customer);
    }
}
