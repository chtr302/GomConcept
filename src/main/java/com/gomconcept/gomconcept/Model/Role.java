package com.gomconcept.gomconcept.Model;

public enum Role {
    HBT("HaiBaTrung","Admin"),
    NHBT("NgoaiHaiBaTrung","NguoiDung");
    
    private final String code;
    private final String displayname;

    Role(String code, String displayname){
        this.code = code;
        this.displayname = displayname;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayname() {
        return displayname;
    }
    
    public static Role fromString(String roleStr){
        if (roleStr == null) return NHBT;
        
        for (Role role : Role.values()){
            if (role.code.equalsIgnoreCase(roleStr)){return role;}
        } return NHBT;
    }
    
    @Override
    public String toString(){
        return this.code;
    }
}
