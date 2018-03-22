package com.assignments.sliit.heroesofourtime.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by esh_d on 21/03/2018.
 * Define the Hero Class
 */

public class Hero {

    private int HeroID;
    private String Name;
    private Date Birthday;
    private Date Death;
    private String Summary;
    private String Description;
    private String Comments;
    private Date CreatedDate;
    private Date ModifiedDate;

    public Hero (){
        Name = "";
        Birthday = Calendar.getInstance().getTime();
        Death = null;
        Summary = "";
        Description = "";
        Comments = "";
        ModifiedDate = Calendar.getInstance().getTime();
        CreatedDate = Calendar.getInstance().getTime();
    }

    public Hero (String name){
        Name = name;
        Birthday = Calendar.getInstance().getTime();
        Death = null;
        Summary = "";
        Description = "";
        Comments = "";
        ModifiedDate = Calendar.getInstance().getTime();
        CreatedDate = Calendar.getInstance().getTime();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHeroID() {
        return HeroID;
    }

    public void setHeroID(int heroID) {
        HeroID = heroID;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public Date getDeath() {
        return Death;
    }

    public void setDeath(Date death) {
        Death = death;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Date getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public int CalculateAge() {
        int age;
        Calendar cal = Calendar.getInstance();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        cal.setTime(Birthday);
        age =  currentYear - cal.get(Calendar.YEAR);
        return age;
    }
}
