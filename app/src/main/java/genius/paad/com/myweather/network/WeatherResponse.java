package genius.paad.com.myweather.network;

import genius.paad.com.myweather.currentModel.Weather;

public class WeatherResponse {

        private Weather weather;
        private Throwable error;

        public WeatherResponse(Weather weather) {
            this.weather = weather;
            this.error = null;
        }

        public WeatherResponse(Throwable error) {
            this.error = error;
            this.weather = null;
        }

    }

