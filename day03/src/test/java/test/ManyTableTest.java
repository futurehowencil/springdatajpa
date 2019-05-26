package test;

import com.fziqan.dao.CustomerDao;
import com.fziqan.dao.LinkManDao;
import com.fziqan.entity.Customer;
import com.fziqan.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

/**
 * @PackageName:test
 * @ClassName:ManyTableTest
 * @Description:
 * @author:FziqAn
 * @date 2019/5/26 15:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyTableTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    //由于是在java代码中测试，为了解决no session问题，将操作配置到同一个事务中
    public void findTest() {
        Customer customer = customerDao.findOne(7L);
        //对象导航查询
        Set<LinkMan> linkManSet = customer.getLinkmans();
        for (LinkMan linkMan : linkManSet) {
            System.out.println("LinkMan：" + linkMan);
        }
    }

    @Test
    public void findTest02() {
        LinkMan linkMan = linkManDao.findOne(1L);
        //对象导航查询
        Customer customer = linkMan.getCustomer();
        System.out.println("Customer：" + customer);
    }

    /**
     * Specification的多表查询
     */
    @Test
    public void specificationFindTest() {
        Specification<LinkMan> spec = new Specification<LinkMan>() {
            @Override
            public Predicate toPredicate(Root<LinkMan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //Join代表链接查询，通过root对象获取
                //创建的过程中，第一个参数为关联对象的属性名称，第二个参数为连接查询的方式（left，inner，right）
                //JoinType.LEFT : 左外连接,JoinType.INNER：内连接,JoinType.RIGHT：右外连接
                Join<LinkMan, Customer> join = root.join("customer", JoinType.INNER);
                return cb.like(join.get("custName").as(String.class), "TBD云集中心");
            }
        };
        List<LinkMan> list = linkManDao.findAll(spec);
        if (list != null && list.size() > 0) {
            for (LinkMan linkMan : list) {
                System.out.println("linkMan:"+linkMan);
            }
        }
    }

}
