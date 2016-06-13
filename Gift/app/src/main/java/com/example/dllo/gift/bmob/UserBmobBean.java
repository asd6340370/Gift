package com.example.dllo.gift.bmob;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/6/12.
 */
public class UserBmobBean extends BmobObject implements Parcelable {
    private String id,price,titleName,likeCount,imgUrl,purchaseUrl;
    private String key;
    private String userName;

    protected UserBmobBean(Parcel in) {
        id = in.readString();
        price = in.readString();
        titleName = in.readString();
        likeCount = in.readString();
        imgUrl = in.readString();
        purchaseUrl = in.readString();
        key = in.readString();
        userName = in.readString();
    }

    public static final Creator<UserBmobBean> CREATOR = new Creator<UserBmobBean>() {
        @Override
        public UserBmobBean createFromParcel(Parcel in) {
            return new UserBmobBean(in);
        }

        @Override
        public UserBmobBean[] newArray(int size) {
            return new UserBmobBean[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserBmobBean() {
    }





    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    public void setPurchaseUrl(String purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(price);
        dest.writeString(titleName);
        dest.writeString(likeCount);
        dest.writeString(imgUrl);
        dest.writeString(purchaseUrl);
        dest.writeString(key);
        dest.writeString(userName);
    }
}
