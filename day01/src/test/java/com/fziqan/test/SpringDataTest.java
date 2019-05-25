package com.fziqan.test;

import com.fziqan.pojo.Customer;
import com.fziqan.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * SpringDataJpa测试类
 *
 * @PackageName:PACKAGE_NAME
 * @ClassName:SpringDataTest
 * @Description:
 * @author:FziqAn
 * @date 2019/5/24 16:47
 */
public class SpringDataTest {
    @Test
    public void saveTest() {
        /**
         * 创建实体管理工厂，借助Persistence的静态方法获取
         *      其中传递的参数为持久化单元名称，需要JPA配置文件中指定
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //1.创建管理实体类
        EntityManager entityManager = factory.createEntityManager();
        //2.获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //3.开启事务
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("1234");
        //4.保存操作
        entityManager.persist(customer);
        //5.提交事务
        transaction.commit();
        //6.释放资源
        entityManager.close();
        factory.close();
    }

    /**
     * 保存一个实体
     */
    @Test
    public void addTest() {
        // 定义对象
        Customer customer = new Customer();
        customer.setCustName("深圳");
        customer.setCustLevel("VIP客户");
        customer.setCustSource("网络");
        customer.setCustIndustry("IT教育");
        customer.setCustAddress("昌平区北七家镇");
        customer.setCustPhone("010-84389340");
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            //1.获取实体管理对象
            entityManager = JPAUtil.getEntityManager();
            //2.获取事务对象
            transaction = entityManager.getTransaction();
            //3.开启事务
            transaction.begin();
            //4.执行操作
            entityManager.persist(customer);
            //5.提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //异常，则事务回滚
            transaction.rollback();
        } finally {
            //释放资源
            entityManager.close();
        }
    }

    /**
     * 删除对象
     */
    @Test
    public void removeTest() {
        //定义对象
        EntityManager manager = null;
        EntityTransaction transaction = null;

        try {
            //1.获取实体管理对象
            manager = JPAUtil.getEntityManager();
            //2.获取事务对象
            transaction = manager.getTransaction();
            //3.开启事务
            transaction.begin();
            //4.执行操作
            Customer c = manager.find(Customer.class, 1L);
            manager.remove(c);
            //5.提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            transaction.rollback();
        } finally {
            //释放资源
            manager.close();
        }
    }

    /**
     * 根据id查询 （立即加载）
     */
    @Test
    public void queryTest01() {
        //定义对象
        EntityManager manager = null;
        EntityTransaction transaction = null;

        try {
            //获取实体管理对象
            manager = JPAUtil.getEntityManager();
            //获取事务对象
            transaction = manager.getTransaction();
            //开启事务
            transaction.begin();
            //执行操作
            Customer customer = manager.find(Customer.class, 3L);
            //find()方法即使没有打印输出语句 一样会执行SQL语句 即立即加载
            // System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            transaction.rollback();
        } finally {
            //释放资源
            manager.close();
        }
    }

    /**
     * 根据id查询 （延迟加载）
     */
    @Test
    public void queryTest02() {
        //定义对象
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            //获取实体管理对象
            manager = JPAUtil.getEntityManager();
            //获取事务对象
            transaction = manager.getTransaction();
            //开启事务
            transaction.begin();
            //执行操作
            Customer customer = manager.getReference(Customer.class, 2L);
            //当执行到打印输出语句的时候 才会执行SQL语句 即延迟加载
//            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            transaction.rollback();
        } finally {
            //释放资源
            manager.close();
        }
    }
}
