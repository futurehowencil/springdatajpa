package com.fziqan.dao;

import com.fziqan.entity.Customer;
import com.fziqan.entity.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @PackageName:com.fziqan.dao
 * @InterfaceName:LinkManDao
 * @Description:
 * @author:FziqAn
 * @date 2019/5/26 10:33
 */
public interface LinkManDao extends JpaRepository<LinkMan, Long>, JpaSpecificationExecutor<LinkMan> {
}
