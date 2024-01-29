package com.niksde.springboot.cruddemo.dao;

import com.niksde.springboot.cruddemo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
