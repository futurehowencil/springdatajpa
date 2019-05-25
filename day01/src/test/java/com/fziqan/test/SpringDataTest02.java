package com.fziqan.test;

import com.fziqan.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @PackageName:PACKAGE_NAME
 * @ClassName:SpringDataTest02
 * @Description:
 * @author:FziqAn
 * @date 2019/5/24 19:39
 */
public class SpringDataTest02 {
    //1.查询所有客户
    @Test
    public void findAll() {
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
            //创建query对象
            String jpql = "from Customer";
            Query query = manager.createQuery(jpql);
            //查询并得到返回结果
            //返回一个List
            List resultList = query.getResultList();
            for (Object o : resultList) {
                System.out.println(o);
            }
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

    //2.分页查询
    @Test
    public void findPage() {
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
            //创建Query对象
            String jpql = "from Customer";
            Query query = manager.createQuery(jpql);
            //起始索引
            query.setFirstResult(0);
            //每页显示条数
            query.setMaxResults(2);
            //查询并返回结果
            List resultList = query.getResultList();
            for (Object o : resultList) {
                System.out.println(o);
            }
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            transaction.rollback();
        } finally {
            manager.close();
        }
    }

    //3.条件查询
    @Test
    public void findCondition() {
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
            //创建Query对象
            String jpql = "from Customer where custLevel like ?";
            Query query = manager.createQuery(jpql);
            //对占位符赋值，从第1个开始
            query.setParameter(1, "传智%");
            //查询并返回结果
            //得到唯一的结果集对象 (single只能返回一个对象)
            Object singleResult = query.getSingleResult();
            System.out.println(singleResult);
            //提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            transaction.rollback();
        } finally {
            //释放资源
            manager.close();
        }
    }

    //4.根据客户id倒序查询所以客户
    //查询所有客户
    @Test
    public void orderTest() {
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
            //创建Query对象
            String jpql = "from Customer order by custId desc";
            Query query = manager.createQuery(jpql);
            //查询并返回结果
            List resultList = query.getResultList();
            for (Object o : resultList) {
                System.out.println(o);
            }
            //提交事务
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            transaction.rollback();
        } finally {
            //释放资源
            manager.close();
        }
    }

    //5.统计查询
    @Test
    public void findCount() {
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
            //创建Query对象
            String jpql = "select count(*) from Customer";
//            String jpql = "select count(c) from Customer c";
//            String jpql = "select count(custId) from Customer";

            Query query = manager.createQuery(jpql);
            //查询并返回结果
            List resultList = query.getResultList();
            for (Object o : resultList) {
                System.out.println("统计查询：" + o);
            }
            //提交事务
            transaction.commit();

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
