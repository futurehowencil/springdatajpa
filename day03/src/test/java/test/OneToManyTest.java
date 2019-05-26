package test;

import com.fziqan.dao.CustomerDao;
import com.fziqan.dao.LinkManDao;
import com.fziqan.entity.Customer;
import com.fziqan.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OneToMany;

/**
 * @PackageName:test
 * @ClassName:OneToManyTest
 * @Description:
 * @author:FziqAn
 * @date 2019/5/26 10:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存操作
     * 需求:
     * 保存一个客户和一个联系人
     * 要求：
     * 创建一个客户对象和一个联系人对象
     * 建立客户和联系人之间关联关系（双向一对多的关联关系）
     * 先保存客户，再保存联系人
     * 问题：
     * 当我们建立了双向的关联关系之后，先保存主表，再保存从表时：
     * 会产生2条insert和1条update.
     * 而实际开发中我们只需要2条insert。
     */

    @Test
    @Transactional
    @Rollback(false)
    public void addCustomerAndLinkManTest() {
        Customer c = new Customer();
        c.setCustName("TBD云集中心");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");

        LinkMan l = new LinkMan();
        l.setLkmName("TBD联系人");
        l.setLkmGender("male");
        l.setLkmMobile("13811111111");
        l.setLkmPhone("010-34785348");
        l.setLkmEmail("98354834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");
        //设置属性
        c.getLinkmans().add(l);
        l.setCustomer(c);
        customerDao.save(c);
        linkManDao.save(l);
    }

    /**
     * 删除
     * 报错：
     * org.springframework.dao.DataIntegrityViolationException:
     * could not execute statement; SQL [n/a]; constraint [null];
     * nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement
     *
     * 原因：
     * 如果配置了放弃维护关联关系的权利，则不能删除（与外键字段是否允许为null，没有关系）
     * 因为在删除时，它根本不会去更新从表的外键字段了。
     *
     * 解决方法：级联删除
     *
     * cascade:配置级联操作
     * 		CascadeType.MERGE	级联更新
     * 		CascadeType.PERSIST	级联保存：
     * 		CascadeType.REFRESH 级联刷新：
     * 		CascadeType.REMOVE	级联删除：
     * 		CascadeType.ALL		包含所有
     * 	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
     */
    @Test
    @Transactional
    @Rollback(false)
    public void deleteTest() {
        customerDao.delete(7L);
    }
}
