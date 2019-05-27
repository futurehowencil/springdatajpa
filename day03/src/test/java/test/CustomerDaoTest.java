package test;


import com.fziqan.dao.CustomerDao;
import com.fziqan.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
     * 使用Specifications完成条件查询 （模糊查询）
     */
    @Test
    public void specificationsTest() {
        //1.用匿名内部类的方式，创建一个Specification的实现类，并实现toPredicate()方法
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //cb:构建查询，添加查询方式 like:模糊匹配
                //root:从实体类Customer对象中按照custName属性进行查询
                return cb.like(root.get("custName").as(String.class), "%老%");
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 基于Specifications的分页查询
     */
    @Test
    public void specificationsToPageTest() {
        //1.构建查询条件
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("custName").as(String.class), "老%");
            }
        };

        /**
         * 构造分页参数
         * 		Pageable : 接口
         * 			PageRequest实现了Pageable接口，调用构造方法的形式构造
         * 				第一个参数：页码（从0开始）
         * 				第二个参数：每页查询条数
         */
        Pageable pageable = new PageRequest(0, 2);

        /**
         * 分页查询，封装为Spring Data Jpa 内部的page bean
         * 		此重载的findAll方法为分页方法需要两个参数
         * 			第一个参数：查询条件Specification
         * 			第二个参数：分页参数
         *
         * 返回的pageBean所包含的方法
         * 	//获取总页数
         * int getTotalPages();
         * //获取总记录数
         * long getTotalElements();
         * //获取列表数据
         * List<T> getContent();
         */
        Page<Customer> page = customerDao.findAll(spec, pageable);
        //1.获取总页数
        int pages = page.getTotalPages();
        System.out.println("总页数：" + pages);
        //2.获取总记录数
        long elements = page.getTotalElements();
        System.out.println("总记录数：" + elements);
        //3.获取列表数据
        List<Customer> list = page.getContent();
        if (list != null && list.size() > 0) {
            for (Customer customer : list) {
                System.out.println(customer);
            }
        }
    }

    /**
     * equal()方法查询
     */
    @Test
    public void specificationsTest2() {
        //1.构建查询条件
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("custName").as(String.class), "老陈");
            }
        };
        //使用lambda表达式
        Specification<Customer> specification1 = ((root, query, cb) ->{ return cb.equal(root.get("").as(String.class), "");});
        Specification<Customer> specification2 = (root, query, cb) ->{ return cb.equal(root.get("").as(String.class), "");};
        Specification<Customer> specification3 = (root, query, cb) -> cb.equal(root.get("").as(String.class), "");

        Customer one = customerDao.findOne(spec);
        System.out.println(one);
    }


}

