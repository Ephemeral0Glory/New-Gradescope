package entity;
public enum Season {

    SPRING, SUMMER, FALL;

    public static Season getSeason(String season) {
        for (Season szn : Season.values()) {
            if (szn.toString().equalsIgnoreCase(season)) {
                return szn;
            }
        }
        throw new IllegalArgumentException();
    }

}