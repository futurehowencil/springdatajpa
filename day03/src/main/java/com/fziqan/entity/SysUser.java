package com.fziqan.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @PackageName:com.fziqan.entity
 * @ClassName:SysUser
 * @Description:
 * @author:FziqAn
 * @date 2019/5/26 11:05
 */
/**
 * 用户的数据模型
 */
@Entity
@Table(name="sys_user")
public class SysUser implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @Column(name="user_code")
    private String userCode;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="user_state")
    private String userState;

    //多对多关系映射
//    @ManyToMany(mappedBy="users")
    @ManyToMany
    @JoinTable(name="user_role_rel",//中间表的名称
            //中间表user_role_rel字段关联sys_role表的主键字段role_id
//            joinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")},

            inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")},
            //中间表user_role_rel的字段关联sys_user表的主键user_id
//            inverseJoinColumns={@JoinColumn(name="user_id",referencedColumnName="user_id")}

            joinColumns={@JoinColumn(name="user_id",referencedColumnName="user_id")}
    )
    private Set<SysRole> roles = new HashSet<SysRole>(0);

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserState() {
        return userState;
    }
    public void setUserState(String userState) {
        this.userState = userState;
    }
    public Set<SysRole> getRoles() {
        return roles;
    }
    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SysUser [userId=" + userId + ", userCode=" + userCode + ", userName=" + userName + ", userPassword="
                + userPassword + ", userState=" + userState + "]";
    }
}