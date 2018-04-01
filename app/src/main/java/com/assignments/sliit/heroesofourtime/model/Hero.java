package com.assignments.sliit.heroesofourtime.model;

import android.graphics.drawable.Drawable;
import android.text.method.DateTimeKeyListener;

import java.sql.Blob;
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
    private Date CreatedDate;
    private Date ModifiedDate;
    private Integer HeroImage;

    public boolean isFavouriteStatus() {
        return FavouriteStatus;
    }

    public void setFavouriteStatus(boolean favouriteStatus) {
        FavouriteStatus = favouriteStatus;
    }

    private boolean FavouriteStatus;

    public Hero (){
        ModifiedDate = Calendar.getInstance().getTime();
    }

    public Hero (String name){
        Name = name;
        Birthday = Calendar.getInstance().getTime();
        Death = null;
        Summary = "";
        Description = "";
        ModifiedDate = Calendar.getInstance().getTime();
        CreatedDate = Calendar.getInstance().getTime();
    }

    public Hero (String name, Date birthdate, Date death, String summary, String description, Integer image){
        Name = name;
        Birthday = birthdate;
        Death = death;
        Summary = summary;
        Description = description;
        ModifiedDate = Calendar.getInstance().getTime();
        CreatedDate = Calendar.getInstance().getTime();
        HeroImage = image;
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
        if (this.Death != null) {
            cal.setTime(this.Death);
        }
        int currentYear = cal.get(Calendar.YEAR);

        cal.setTime(Birthday);
        age =  currentYear - cal.get(Calendar.YEAR);
        return age;
    }
    public Integer getHeroImage() {
        return HeroImage;
    }

    public void setHeroImage(Integer heroImage) {
        HeroImage = heroImage;
    }
}
