package com.fziqan.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @PackageName:com.fziqan.entity
 * @ClassName:SysRole
 * @Description:
 * @author:FziqAn
 * @date 2019/5/26 11:07
 */
/**
 * 角色的数据模型
 */
@Entity
@Table(name="sys_role")
public class SysRole implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;
    @Column(name="role_name")
    private String roleName;
    @Column(name="role_memo")
    private String roleMemo;

    //多对多关系映射
    /**
     * @ManyToMany ://多对多
     * @JoinTable: 关联中间表的注解  用户id 角色id
     * name：user_role_rel用户角色关系表名
     * joinColumns：角色表和中间表关联字段配置
     *      name:role_id（中间表中的字段 ） referencedColumnName（角色表的主键id）
     * <p>
     * inverseJoinColumns：多对多另一方用户表和中间表关联字段配置
     *      name:user_id（中间表中的字段 ） referencedColumnName（用户表的主键id）
     *
     *      通过角色来维护用户和角色表之间的关系
     */
//    @ManyToMany(mappedBy = "roles")
//    @JoinTable(name="user_role_rel",//中间表的名称
//            //中间表user_role_rel字段关联sys_role表的主键字段role_id
//            joinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")},
//            //中间表user_role_rel的字段关联sys_user表的主键user_id
//            inverseJoinColumns={@JoinColumn(name="user_id",referencedColumnName="user_id")}
//    )
//    private Set<SysUser> users = new HashSet<SysUser>(0);
//    ManyToMany：多对多注解：mappedBy="users" 用户这方放弃了维护关系权限
    @ManyToMany(mappedBy="roles")
    private Set<SysUser> users = new HashSet<SysUser>(0);


    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleMemo() {
        return roleMemo;
    }
    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
    }
    public Set<SysUser> getUsers() {
        return users;
    }
    public void setUsers(Set<SysUser> users) {
        this.users = users;
    }
    @Override
    public String toString() {
        return "SysRole [roleId=" + roleId + ", roleName=" + roleName + ", roleMemo=" + roleMemo + "]";
    }
}