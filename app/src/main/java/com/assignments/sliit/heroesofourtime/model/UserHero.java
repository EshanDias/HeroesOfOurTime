package com.assignments.sliit.heroesofourtime.model;

import java.util.Calendar;
import java.util.Date;

public class UserHero {
    private int UserID;
    private int HeroID;
    private String Comments;
    private Boolean FavouriteStatus;
    private Date CreatedDate;
    private Date ModifiedDate;

    public UserHero() {
        setModifiedDate(Calendar.getInstance().getTime());
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getHeroID() {
        return HeroID;
    }

    public void setHeroID(int heroID) {
        HeroID = heroID;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public Boolean getFavouriteStatus() {
        return FavouriteStatus;
    }

    public void setFavouriteStatus(Boolean favouriteStatus) {
        FavouriteStatus = favouriteStatus;
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

}
