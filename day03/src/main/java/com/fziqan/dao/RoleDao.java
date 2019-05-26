package com.fziqan.dao;

import com.fziqan.entity.LinkMan;
import com.fziqan.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @PackageName:com.fziqan.dao
 * @InterfaceName:RoleDao
 * @Description:
 * @author:FziqAn
 * @date 2019/5/26 11:11
 */
public interface RoleDao extends JpaRepository<SysRole, Long>, JpaSpecificationExecutor<SysRole> {
}
