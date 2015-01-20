package projet.m2dl.com.projetandroid.classes;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by fabien on 15/01/15.
 */
public class Picture implements Parcelable {

    private Bitmap picture;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private double altitude = 0.0;
    private Date date;
    private String user;
    private float pointInteret_x;
    private float pointInteret_y;
    private int pointInteret_hight;
    private int pointInteret_weight;
    private String commentaire;
    private String destinataire;
    private Stack<String> key = new Stack<String>();

    public Picture(Bitmap picture) {
        this.picture = picture;
    }

    public Picture() {

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

    public float getPointInteret_x() {
        return pointInteret_x;
    }

    public void setPointInteret_x(float pointInteret_x) {
        this.pointInteret_x = pointInteret_x;
    }

    public float getPointInteret_y() {
        return pointInteret_y;
    }

    public void setPointInteret_y(float pointInteret_y) {
        this.pointInteret_y = pointInteret_y;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        picture.writeToParcel(dest, 0);
        dest.writeString(String.valueOf(latitude));
        dest.writeString(String.valueOf(longitude));
        dest.writeString(String.valueOf(altitude));
        dest.writeString(String.valueOf(date.getTime()));
        dest.writeString(user);
        dest.writeString(String.valueOf(pointInteret_x));
        dest.writeString(String.valueOf(pointInteret_y));
        dest.writeString(String.valueOf(pointInteret_hight));
        dest.writeString(String.valueOf(pointInteret_weight));
        dest.writeString(commentaire);
        dest.writeString(destinataire);
    }

    public static final Parcelable.Creator<Picture> CREATOR = new Parcelable.Creator<Picture>()
    {
        @Override
        public Picture createFromParcel(Parcel source)
        {
            return new Picture(source);
        }

        @Override
        public Picture[] newArray(int size)
        {
            return new Picture[size];
        }
    };

    public Picture(Parcel in) {
        this.picture = Bitmap.CREATOR.createFromParcel(in);
        this.latitude = Double.parseDouble(in.readString());
        this.longitude = Double.parseDouble(in.readString());
        this.altitude = Double.parseDouble(in.readString());
        this.date = new Date(Long.parseLong(in.readString()));
        this.user = in.readString();
        this.pointInteret_x = Float.parseFloat(in.readString());
        this.pointInteret_y = Float.parseFloat(in.readString());
        this.pointInteret_hight =Integer.parseInt(in.readString());
        this.pointInteret_weight = Integer.parseInt(in.readString());
        this.commentaire = in.readString();
        this.destinataire = in.readString();
    }
}
