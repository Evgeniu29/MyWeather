package genius.paad.com.myweather.currentModel;

import com.google.gson.annotations.SerializedName;

public class DescriptionWeather {


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
