package sportsapp.sashi.in.sportsapp.models.drawer;

public class ItemsHeader {

    public String iconURL;
    public String profileName;

    public ItemsHeader() {
    }

    public ItemsHeader(String iconURL, String profileName) {
        this.iconURL = iconURL;
        this.profileName = profileName;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
