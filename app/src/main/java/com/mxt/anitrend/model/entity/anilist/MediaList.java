package com.mxt.anitrend.model.entity.anilist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.mxt.anitrend.data.converter.SeriesBaseConverter;
import com.mxt.anitrend.data.converter.list.CustomListConverter;
import com.mxt.anitrend.model.entity.anilist.meta.FuzzyDate;
import com.mxt.anitrend.model.entity.base.MediaBase;
import com.mxt.anitrend.model.entity.base.UserBase;
import com.mxt.anitrend.model.entity.group.EntityGroup;
import com.mxt.anitrend.util.DateUtil;

import java.util.List;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.Transient;

/**
 * Created by Maxwell on 1/12/2017.
 */

@Entity
public class MediaList extends EntityGroup implements Parcelable {

    @Id(assignable = true)
    private long id;
    @Index
    private long mediaId;
    private String status;
    private int score;
    private int score_raw;
    private int progress;
    private int progressVolumes;
    private int repeat;
    private int priority;
    private String notes;
    @SerializedName("private")
    private boolean hidden;
    private boolean hiddenFromStatusLists;
    @Convert(converter = CustomListConverter.class, dbType = String.class)
    private List<String> customLists;
    private FuzzyDate startedAt;
    private FuzzyDate completedAt;
    private long updatedAt;
    private long createdAt;
    @Convert(converter = SeriesBaseConverter.class, dbType = String.class)
    private MediaBase media;
    @Transient
    private UserBase user;

    public MediaList() {

    }

    protected MediaList(Parcel in) {
        id = in.readLong();
        mediaId = in.readLong();
        status = in.readString();
        score = in.readInt();
        score_raw = in.readInt();
        progress = in.readInt();
        progressVolumes = in.readInt();
        repeat = in.readInt();
        priority = in.readInt();
        notes = in.readString();
        hidden = in.readByte() != 0;
        hiddenFromStatusLists = in.readByte() != 0;
        customLists = in.createStringArrayList();
        startedAt = in.readParcelable(FuzzyDate.class.getClassLoader());
        completedAt = in.readParcelable(FuzzyDate.class.getClassLoader());
        updatedAt = in.readLong();
        createdAt = in.readLong();
        media = in.readParcelable(MediaBase.class.getClassLoader());
        user = in.readParcelable(UserBase.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(mediaId);
        dest.writeString(status);
        dest.writeInt(score);
        dest.writeInt(score_raw);
        dest.writeInt(progress);
        dest.writeInt(progressVolumes);
        dest.writeInt(repeat);
        dest.writeInt(priority);
        dest.writeString(notes);
        dest.writeByte((byte) (hidden ? 1 : 0));
        dest.writeByte((byte) (hiddenFromStatusLists ? 1 : 0));
        dest.writeStringList(customLists);
        dest.writeParcelable(startedAt, flags);
        dest.writeParcelable(completedAt, flags);
        dest.writeLong(updatedAt);
        dest.writeLong(createdAt);
        dest.writeParcelable(media, flags);
        dest.writeParcelable(user, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MediaList> CREATOR = new Creator<MediaList>() {
        @Override
        public MediaList createFromParcel(Parcel in) {
            return new MediaList(in);
        }

        @Override
        public MediaList[] newArray(int size) {
            return new MediaList[size];
        }
    };

    public long getId() {
        return id;
    }

    public long getMediaId() {
        return mediaId;
    }

    public String getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    public int getScore_raw() {
        return score_raw;
    }

    public int getProgress() {
        return progress;
    }

    public int getProgressVolumes() {
        return progressVolumes;
    }

    public int getRepeat() {
        return repeat;
    }

    public int getPriority() {
        return priority;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isHiddenFromStatusLists() {
        return hiddenFromStatusLists;
    }

    public List<String> getCustomLists() {
        return customLists;
    }

    public FuzzyDate getStartedAt() {
        return startedAt;
    }

    public FuzzyDate getCompletedAt() {
        return completedAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public MediaBase getMedia() {
        return media;
    }

    public UserBase getUser() {
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MediaList)
            return ((MediaList)obj).id == id && ((MediaList)obj).mediaId == mediaId;
        else if (obj instanceof Media)
            return ((Media)obj).getId() == mediaId;
        else if (obj instanceof MediaBase)
            return ((MediaBase)obj).getId() == mediaId;
        return super.equals(obj);
    }
}