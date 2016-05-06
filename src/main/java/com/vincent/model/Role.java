package com.vincent.model;

public class Role {
	private int rid;
	private String roleName = RoleType.MEMBER.getRoleType();
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rid;
        result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Role))
            return false;
        Role other = (Role) obj;
        if (rid != other.rid)
            return false;
        if (roleName == null) {
            if (other.roleName != null)
                return false;
        } else if (!roleName.equals(other.roleName))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Role [id=" + rid + ",  Role Name=" + roleName  + "]";
    }
}
