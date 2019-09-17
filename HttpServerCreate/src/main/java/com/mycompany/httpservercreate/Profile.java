package com.mycompany.httpservercreate;

/**
 *
 * @author koushik
 */
public class Profile {
    
    private String name,phone;

    public Profile(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}