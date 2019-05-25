package com.fziqan.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @PackageName:com.fziqan.pojo.com.fziqan.util
 * @ClassName:JPAUtil
 * @Description:
 * @author:FziqAn
 * @date 2019/5/24 17:08
 */
public class JPAUtil {
    //JPA的实体管理工厂：相当于hibernate的SessionFactory
    private static EntityManagerFactory factory;

    //使用静态代码块复制
    static {
        //注意：该方法参数必须和persistence.xml中persistence-unit标签的name属性取值一致
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     * 使用管理工厂生产一个管理对象
     *
     * @return
     */
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
