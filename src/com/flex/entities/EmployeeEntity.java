package com.flex.entities;

import com.flex.utils.Lib;

import java.util.Date;

public class EmployeeEntity {

    private int emp_no;
    private String first_name;
    private String last_name;

    private String gender;
    private Date birth_date;
    private Date hire_date;
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }




    /***
     * Hàm kiểm tra dữ liệu với mỗi đối tượng Employee
     * dinh add 11/05/2019
     */
    public void validateEmployee(){
        // First_name không quá 14 kí tự
        if(!Lib.isBlank(this.first_name)&& this.first_name.length()>14){
            String name1 = this.first_name.substring(0, Math.min(this.first_name.length(), 14));
            this.setFirst_name(name1);
        }

        // Last_name không quá 16 kí tự
        if(!Lib.isBlank(this.last_name)&& this.last_name.length()>16){
            String name1 = this.first_name.substring(0, Math.min(this.first_name.length(), 16));
            this.setLast_name(name1);
        }

        // Last_name không quá 16 kí tự
        if(!Lib.isBlank(this.gender)&& this.gender.length()>1){
            String gender1 = this.gender.substring(0, Math.min(this.gender.length(), 1));
            this.setGender(gender1);
        }

    }
}
