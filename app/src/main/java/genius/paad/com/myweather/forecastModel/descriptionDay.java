package genius.paad.com.myweather.forecastModel;

import com.google.gson.annotations.SerializedName;

public class descriptionDay {

    @SerializedName("description")

    public String description = new String();

    @SerializedName("icon")

    public String icon = new String();


    public String getDescription() {
        return  description;
    }

    public String getIcon() {
        return  icon;
    }

}
