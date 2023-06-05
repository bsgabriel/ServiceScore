package br.ucs.servicescore.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class YelpSearchResponse implements Serializable {

    private final static long serialVersionUID = -5978094281235681080L;
    @SerializedName("businesses")
    @Expose
    private List<Business> businesses = new ArrayList<Business>();
    @SerializedName("total")
    @Expose
    private int total;

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(YelpSearchResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("businesses");
        sb.append('=');
        sb.append(((this.businesses == null) ? "<null>" : this.businesses));
        sb.append(',');
        sb.append("total");
        sb.append('=');
        sb.append(this.total);
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
        result = ((result * 31) + this.total);
        result = ((result * 31) + ((this.businesses == null) ? 0 : this.businesses.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof YelpSearchResponse)) {
            return false;
        }
        YelpSearchResponse rhs = ((YelpSearchResponse) other);
        return this.total == rhs.total && Objects.equals(this.businesses, rhs.businesses);
    }

}
