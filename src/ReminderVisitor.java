// ReminderVisitor class to visit reminders
public class ReminderVisitor extends NodeVisitor {


    @Override
    public void visitReminder(Reminder r) {
        System.out.println(r.getMessage());
    }
}
