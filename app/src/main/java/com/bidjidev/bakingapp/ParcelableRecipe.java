package com.bidjidev.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableRecipe implements Parcelable {

    private String id;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    private String servings;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServings() {
        return servings;
    }

    public ParcelableRecipe(String id, String name, String servings) {

        this.id = id;
        this.name = name;
        this.servings = servings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel a, int flags) {
        a.writeString(this.id);
        a.writeString(this.servings);
        a.writeString(this.name);
    }

    protected ParcelableRecipe(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.servings = in.readString();
    }

    public static final Creator<ParcelableRecipe> CREATOR = new Creator<ParcelableRecipe>() {
        @Override
        public ParcelableRecipe createFromParcel(Parcel source) {
            return new ParcelableRecipe(source);
        }

        @Override
        public ParcelableRecipe[] newArray(int size) {
            return new ParcelableRecipe[size];
        }
    };
}
