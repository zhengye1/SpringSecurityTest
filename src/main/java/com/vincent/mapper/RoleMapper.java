package com.vincent.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.vincent.model.Role;

public interface RoleMapper {

	@Select("SELECT RID as rid, ROLENAME as roleName from Role")
	public Set<Role> getRoles();
}
