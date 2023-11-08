package com.example.coursework2.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hikes")
public class Hike {
    @PrimaryKey(autoGenerate = true)
    public long hike_id;
    public String hike_name;
    public String location;
    public String date;
    public String parking;
    public float hike_length;
    public String hike_level;
    public String description;

//    public Hike() {
//        // Constructor không tham số, không cần làm gì cả
//    }
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//    // Phương thức write data vào Parcel
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(hike_name);
//        dest.writeString(location);
//        dest.writeString(date);
//        dest.writeString(parking);
//        dest.writeFloat(hike_length);
//        dest.writeString(hike_level);
//        dest.writeString(description);
//        // Đối với các trường dữ liệu khác, thêm vào Parcel ở đây
//    }
//    public static final Creator<Hike> CREATOR = new Creator<Hike>() {
//        @Override
//        public Hike createFromParcel(Parcel in) {
//            return new Hike(in);
//        }
//
//        @Override
//        public Hike[] newArray(int size) {
//            return new Hike[size];
//        }
//    };
//    protected Hike(Parcel in) {
//        hike_name = in.readString();
//        location = in.readString();
//        date = in.readString();
//        parking = in.readString();
//        hike_length = in.readFloat();
//        hike_level = in.readString();
//        description = in.readString();
//
//    }
}
