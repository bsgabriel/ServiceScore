package br.ucs.servicescore.entity.yelpresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Location implements Serializable {

    private final static long serialVersionUID = -7885615557585908763L;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("address3")
    @Expose
    private String address3;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zip_code")
    @Expose
    private String zipCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("display_address")
    @Expose
    private List<String> displayAddress = new ArrayList<String>();

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(List<String> displayAddress) {
        this.displayAddress = displayAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Location.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("address1");
        sb.append('=');
        sb.append(((this.address1 == null) ? "<null>" : this.address1));
        sb.append(',');
        sb.append("address2");
        sb.append('=');
        sb.append(((this.address2 == null) ? "<null>" : this.address2));
        sb.append(',');
        sb.append("address3");
        sb.append('=');
        sb.append(((this.address3 == null) ? "<null>" : this.address3));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null) ? "<null>" : this.city));
        sb.append(',');
        sb.append("zipCode");
        sb.append('=');
        sb.append(((this.zipCode == null) ? "<null>" : this.zipCode));
        sb.append(',');
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null) ? "<null>" : this.country));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null) ? "<null>" : this.state));
        sb.append(',');
        sb.append("displayAddress");
        sb.append('=');
        sb.append(((this.displayAddress == null) ? "<null>" : this.displayAddress));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.zipCode == null) ? 0 : this.zipCode.hashCode()));
        result = ((result * 31) + ((this.country == null) ? 0 : this.country.hashCode()));
        result = ((result * 31) + ((this.address3 == null) ? 0 : this.address3.hashCode()));
        result = ((result * 31) + ((this.address2 == null) ? 0 : this.address2.hashCode()));
        result = ((result * 31) + ((this.city == null) ? 0 : this.city.hashCode()));
        result = ((result * 31) + ((this.address1 == null) ? 0 : this.address1.hashCode()));
        result = ((result * 31) + ((this.displayAddress == null) ? 0 : this.displayAddress.hashCode()));
        result = ((result * 31) + ((this.state == null) ? 0 : this.state.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Location)) {
            return false;
        }
        Location rhs = ((Location) other);
        return ((((((((Objects.equals(this.zipCode, rhs.zipCode)) && (Objects.equals(this.country, rhs.country))) && (Objects.equals(this.address3, rhs.address3))) && (Objects.equals(this.address2, rhs.address2))) && (Objects.equals(this.city, rhs.city))) && (Objects.equals(this.address1, rhs.address1))) && (Objects.equals(this.displayAddress, rhs.displayAddress))) && (Objects.equals(this.state, rhs.state)));
    }

}
