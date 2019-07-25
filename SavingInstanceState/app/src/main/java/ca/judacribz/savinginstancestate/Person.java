package ca.judacribz.savinginstancestate;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private String name, gender;

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    protected Person(Parcel in) {
        name = in.readString();
        gender = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
    }

    @Override
    public String toString() {
        return "Person{" +
                    "name='" + name + "', " +
                    "gender='" + gender + "'" +
                '}';
    }
}
