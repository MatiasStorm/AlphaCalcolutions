package easyon.alphacalcolutions.model;

public class UserTitle {
    private int userTitleId;
    private String userTitle;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass() ) return false;
        UserTitle ut = (UserTitle) o;
        return userTitleId == ut.getUserTitleId()
                && userTitle.equals(ut.getUserTitle());
    }

    @Override
    public int hashCode() {
        return userTitleId;
    }

    public int getUserTitleId() {
        return userTitleId;
    }

    public void setUserTitleId(int userTitleId) {
        this.userTitleId = userTitleId;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }
}
