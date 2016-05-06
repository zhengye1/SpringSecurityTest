package com.vincent.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;

import com.vincent.model.Role;
import com.vincent.model.User;

public interface UserMapper {

	  @Select("SELECT UID as uid, USERNAME as userName, EMAIL as email, "
	  		+ "PASSWORD as password, "
	          + "FIRSTNAME as firstName, LASTNAME as lastName, "
	          + "DATEOFBIRTH as dateOfBirth, STATUS as status, DEPID as depId"
	          + "FROM student WHERE userName = #{userName}")
	  @Results(value={@Result(property="roles", javaType=Set.class, column="uid",
	  		many=@Many(select="getRoles"))})
	  public User findByUsername(String username);
	  
	  @Select("SELECT userrole.role_rid as rid, role.rolename as roleName"
	  		+ "from userrole join role on userrole.role_rid = role.rid "
	  		+ "where userrole.user_uid = #{uid}")
	  public Set<Role> getRoles(int uid);
}
