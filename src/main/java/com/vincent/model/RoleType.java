package com.vincent.model;

public enum RoleType {
    MEMBER("MEMBER"),
    LEADER("LEADER"),
    ADMIN("ADMIN");
     
    String roleType;
     
    private RoleType(String roleType){
        this.roleType = roleType;
    }
     
    public String getRoleType(){
        return roleType;
    }
}
