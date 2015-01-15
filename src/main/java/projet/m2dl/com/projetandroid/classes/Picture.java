package projet.m2dl.com.projetandroid.classes;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by fabien on 15/01/15.
 */
public class Picture {

    private Bitmap picture;
    private double latitude;
    private double longitude;
    private double altitude;
    private Date date;
    private String user;
    private int pointInteret_x;
    private int pointInteret_y;
    private int pointInteret_hight;
    private int pointInteret_weight;
    private String commentaire;

    public Picture(Bitmap picture) {
        this.picture = picture;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPointInteret_x() {
        return pointInteret_x;
    }

    public void setPointInteret_x(int pointInteret_x) {
        this.pointInteret_x = pointInteret_x;
    }

    public int getPointInteret_y() {
        return pointInteret_y;
    }

    public void setPointInteret_y(int pointInteret_y) {
        this.pointInteret_y = pointInteret_y;
    }

    public int getPointInteret_hight() {
        return pointInteret_hight;
    }

    public void setPointInteret_hight(int pointInteret_hight) {
        this.pointInteret_hight = pointInteret_hight;
    }

    public int getPointInteret_weight() {
        return pointInteret_weight;
    }

    public void setPointInteret_weight(int pointInteret_weight) {
        this.pointInteret_weight = pointInteret_weight;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
