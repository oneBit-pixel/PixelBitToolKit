package com.example.lib_view.view;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("WeakerAccess")
public class Constants {

    public final static String URL_API = "http://d2sfs9dx3ednnn.cloudfront.net/";
    public final static String FORMER_URL = "\\{app_domain\\}";
    public final static String PACKAGE_NAME = "com.wallpaper.art.emojisticker";
    public final static String SERVICE_NAME_3D = PACKAGE_NAME + ".ui.service.MyWallpaperService";
    public final static String SERVICE_NAME_LIVE = PACKAGE_NAME + ".ui.service.LiveWallpaperService";
    public final static String REQUEST_URL ="fWFhZWYvOjpxI3NifyJ+JicgJWNgO3Z5emBxc2d6e2E7e3BhOnRlZXZ2OmMkOnRxSnZ6e3N8cg==";
    public final static String SECRET_KEY ="c3AsJS0gdCYmcHEiJScncQ==";

    // Parallax
    public final static double SENSITIVITY_MIN = 0.1;
    public final static double SENSITIVITY_MAX = 0.5;
    public final static double DEPTH_MIN = 0.001;
    public final static double DEPTH_MAX = 0.01;
    public final static double FALLBACK_MIN = 0.0;
    public final static double FALLBACK_MAX = 0.05;
    public final static double ZOOM_MIN = 0.6;
    public final static double ZOOM_MAX = 1.0;
    public final static double SCROLL_AMOUNT_MIN = 0.3;
    public final static double SCROLL_AMOUNT_MAX = 0.05;
    public final static double DIM_MAX = 200.0;

    // Sensor
    public final static double VERTICAL_FIX = 0.01;

    // Time
    public final static long T_CATALOG_EXPIRATION = 24 * 3600;
    public final static int T_SERVER_TIMEOUT = 15000;
    // File system
    public final static String FS_DIR_ZERO = "Zero";
    public final static String FS_DIR_CACHE = "cache";

    // Local data
    public final static String LD_CATALOG = "catalog";
    public final static String LD_TIMESTAMP = "timestamp";

    // Background
    public final static String BG_FORMAT = ".jpg";

    // Preferences
    // NOTE: These are internal preferences not available to the user in the settings
    public final static String PREF_BACKGROUND = "background";
    public final static String PREF_BACKGROUND_DEFAULT = "fallback";
    public final static String PREF_CHECKSENS = "checksens";
    public final static boolean PREF_CHECKSENS_DEFAULT = true;
    public final static String PREF_FIRSTPREV = "firstprev";
    public final static boolean PREF_FIRSTPREV_DEFAULT = true;

    // led
    public static final String LED_CURRENT_GRADIENT_COLOR = "led_current_gradient_color";
    public static final String LED_CURRENT_SPEED = "led_current_speed";
    public static final String LED_BORDER_SIZE = "led_border_size";
    public static final String LED_BOTTOM_RADIUS = "led_bottom_radius";
    public static final String LED_TOP_RADIUS = "led_top_radius";
    public static final String LED_BORDER_TYPE_BITMAP = "led_border_type_bitmap";

    public static final String BROADCAST_PARAMS_CONTROL_ACTION = "com.wallpaper.art.emojisticker";
    public static final String KEY_SERVICE_HOLDER = "surfaceHolder";

    public static class Sp{
        public static final String CONFIG_IS_FIRST_LAUNCH = "config_is_first_launch";
    }

    public static class TopOn{
        public static final String APP_ID = "a62d7ad6fba90f";
        public static final String APP_KEY = "dicmJiclIycgIyAkJiYkJnR3JCMjJCF3IXEicCIjIiY=";
        public static final String INTER_ID = "b62d7ad9b64f0b";
        public static final String INTER_ID_2 = "";
        public static final String BANNER_ID = "";
        public static final String NATIVE_ID = "b62d7ad9d864fb";
        public static final String NATIVE_BANNER_ID = "";
        public static final String REWARD_ID = "b62d8b55787eaa";
    }


    public static class PlacementId{
        public static final String inter_default = "f62d7af7a183e5";

        public static final String native_default = "f62d7afa905ceb";

        public static final String reward_default = "f62d7afd81455a";

    }


    public static final class Singleton {
        public static final Singleton INSTANCE;

        public final void xxx() {
        }

        private Singleton() {
        }

        static {
            Singleton var0 = new Singleton();
            INSTANCE = var0;
            final String www = "";
        }
    }
}

