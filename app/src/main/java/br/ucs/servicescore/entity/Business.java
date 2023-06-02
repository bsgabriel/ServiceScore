package br.ucs.servicescore.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Business implements Serializable {

    private static final long serialVersionUID = 2295350356926449998L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("is_closed")
    @Expose
    private boolean isClosed;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("review_count")
    @Expose
    private int reviewCount;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = new ArrayList<Category>();
    @SerializedName("rating")
    @Expose
    private float rating;
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("transactions")
    @Expose
    private List<String> transactions = new ArrayList<String>();
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("display_phone")
    @Expose
    private String displayPhone;
    @SerializedName("distance")
    @Expose
    private float distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplayPhone() {
        return displayPhone;
    }

    public void setDisplayPhone(String displayPhone) {
        this.displayPhone = displayPhone;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Business.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("alias");
        sb.append('=');
        sb.append(((this.alias == null) ? "<null>" : this.alias));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null) ? "<null>" : this.name));
        sb.append(',');
        sb.append("imageUrl");
        sb.append('=');
        sb.append(((this.imageUrl == null) ? "<null>" : this.imageUrl));
        sb.append(',');
        sb.append("isClosed");
        sb.append('=');
        sb.append(this.isClosed);
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null) ? "<null>" : this.url));
        sb.append(',');
        sb.append("reviewCount");
        sb.append('=');
        sb.append(this.reviewCount);
        sb.append(',');
        sb.append("categories");
        sb.append('=');
        sb.append(((this.categories == null) ? "<null>" : this.categories));
        sb.append(',');
        sb.append("rating");
        sb.append('=');
        sb.append(this.rating);
        sb.append(',');
        sb.append("coordinates");
        sb.append('=');
        sb.append(((this.coordinates == null) ? "<null>" : this.coordinates));
        sb.append(',');
        sb.append("transactions");
        sb.append('=');
        sb.append(((this.transactions == null) ? "<null>" : this.transactions));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null) ? "<null>" : this.price));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null) ? "<null>" : this.location));
        sb.append(',');
        sb.append("phone");
        sb.append('=');
        sb.append(((this.phone == null) ? "<null>" : this.phone));
        sb.append(',');
        sb.append("displayPhone");
        sb.append('=');
        sb.append(((this.displayPhone == null) ? "<null>" : this.displayPhone));
        sb.append(',');
        sb.append("distance");
        sb.append('=');
        sb.append(this.distance);
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
        result = ((result * 31) + ((this.displayPhone == null) ? 0 : this.displayPhone.hashCode()));
        result = ((result * 31) + Float.floatToIntBits(this.distance));
        result = ((result * 31) + Float.floatToIntBits(this.rating));
        result = ((result * 31) + ((this.coordinates == null) ? 0 : this.coordinates.hashCode()));
        result = ((result * 31) + ((this.transactions == null) ? 0 : this.transactions.hashCode()));
        result = ((result * 31) + ((this.url == null) ? 0 : this.url.hashCode()));
        result = ((result * 31) + (this.isClosed ? 1 : 0));
        result = ((result * 31) + this.reviewCount);
        result = ((result * 31) + ((this.phone == null) ? 0 : this.phone.hashCode()));
        result = ((result * 31) + ((this.price == null) ? 0 : this.price.hashCode()));
        result = ((result * 31) + ((this.imageUrl == null) ? 0 : this.imageUrl.hashCode()));
        result = ((result * 31) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((result * 31) + ((this.alias == null) ? 0 : this.alias.hashCode()));
        result = ((result * 31) + ((this.location == null) ? 0 : this.location.hashCode()));
        result = ((result * 31) + ((this.id == null) ? 0 : this.id.hashCode()));
        result = ((result * 31) + ((this.categories == null) ? 0 : this.categories.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Business)) {
            return false;
        }
        Business rhs = ((Business) other);
        return ((((((((((((((((Objects.equals(this.displayPhone, rhs.displayPhone)) && (Float.floatToIntBits(this.distance) == Float.floatToIntBits(rhs.distance))) && (Float.floatToIntBits(this.rating) == Float.floatToIntBits(rhs.rating))) && (Objects.equals(this.coordinates, rhs.coordinates))) && (Objects.equals(this.transactions, rhs.transactions))) && (Objects.equals(this.url, rhs.url))) && (this.isClosed == rhs.isClosed)) && (this.reviewCount == rhs.reviewCount)) && (Objects.equals(this.phone, rhs.phone))) && (Objects.equals(this.price, rhs.price))) && (Objects.equals(this.imageUrl, rhs.imageUrl))) && (Objects.equals(this.name, rhs.name))) && (Objects.equals(this.alias, rhs.alias))) && (Objects.equals(this.location, rhs.location))) && (Objects.equals(this.id, rhs.id))) && (Objects.equals(this.categories, rhs.categories)));
    }

}
