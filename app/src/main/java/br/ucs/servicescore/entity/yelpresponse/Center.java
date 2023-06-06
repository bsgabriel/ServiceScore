package br.ucs.servicescore.entity.yelpresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Center implements Serializable {

    private final static long serialVersionUID = -8598078148956668601L;
    @SerializedName("longitude")
    @Expose
    private float longitude;
    @SerializedName("latitude")
    @Expose
    private float latitude;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Center.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("longitude");
        sb.append('=');
        sb.append(this.longitude);
        sb.append(',');
        sb.append("latitude");
        sb.append('=');
        sb.append(this.latitude);
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
        result = ((result * 31) + Float.floatToIntBits(this.longitude));
        result = ((result * 31) + Float.floatToIntBits(this.latitude));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Center)) {
            return false;
        }
        Center rhs = ((Center) other);
        return ((Float.floatToIntBits(this.longitude) == Float.floatToIntBits(rhs.longitude)) && (Float.floatToIntBits(this.latitude) == Float.floatToIntBits(rhs.latitude)));
    }

}
