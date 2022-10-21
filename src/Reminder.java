public class Reminder {

    Reminder(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public void visitReminder(NodeVisitor visitor) {

        visitor.visitReminder(this);
    }
}
