package com.fziqan.dao;

import com.fziqan.entity.LinkMan;
import com.fziqan.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @PackageName:com.fziqan.dao
 * @ClassName:UserDao
 * @Description:
 * @author:FziqAn
 * @date 2019/5/26 11:10
 */
public interface UserDao extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {
}
