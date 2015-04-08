package RES;

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.time.ClockType;
import com.alee.extended.time.WebClock;
import com.alee.managers.notification.*;

import java.awt.*;

/**
 * Created by Alexandru on 16/03/2015.
 */
public class Notification {

    public static String ERROR = "error";
    public static String INFORMATION = "info";
    public static String APPLICATION = "app";
    public static String CALENDAR = "calendar";
    public static String CLOCK = "clock";
    public static String WARNING = "warning";
    public static String MESSAGE = "message";
    public static String QUESTION = "question";
    public static String DATABASE = "database";

    public static void show(String str) {
        NotificationManager.showNotification(str);
    }

    public static void show(String str, String option) {

        if (option.equals("error")) {
            NotificationManager.showNotification(str, NotificationIcon.error.getIcon());

        } else if (option.equals("info")) {
            NotificationManager.showNotification(str, NotificationIcon.information.getIcon());

        } else if (option.equals("app")) {
            NotificationManager.showNotification(str, NotificationIcon.application.getIcon());

        } else if (option.equals("calendar")) {
            NotificationManager.showNotification(str, NotificationIcon.calendar.getIcon());

        } else if (option.equals("clock")) {
            NotificationManager.showNotification(str, NotificationIcon.clock.getIcon());

        } else if (option.equals("warning")) {
            NotificationManager.showNotification(str, NotificationIcon.warning.getIcon());

        } else if (option.equals("message")) {
            NotificationManager.showNotification(str, NotificationIcon.tip.getIcon());

        } else if (option.equals("question")) {
            NotificationManager.showNotification(str, NotificationIcon.question.getIcon());

        } else if (option.equals("database")) {
            NotificationManager.showNotification(str, NotificationIcon.database.getIcon());
        }
    }

    public static void showTimer(String str, String option, int seconds) {
        final WebNotificationPopup notificationPopup = new WebNotificationPopup();
        if (option.equals("error")) {
            notificationPopup.setIcon(NotificationIcon.error);

        } else if (option.equals("info")) {
            notificationPopup.setIcon(NotificationIcon.information);

        } else if (option.equals("app")) {
            notificationPopup.setIcon(NotificationIcon.application);

        } else if (option.equals("calendar")) {
            notificationPopup.setIcon(NotificationIcon.calendar);

        } else if (option.equals("clock")) {
            notificationPopup.setIcon(NotificationIcon.clock);

        } else if (option.equals("warning")) {
            notificationPopup.setIcon(NotificationIcon.warning);

        } else if (option.equals("message")) {
            notificationPopup.setIcon(NotificationIcon.tip);

        } else if (option.equals("question")) {
            notificationPopup.setIcon(NotificationIcon.question);

        } else if (option.equals("database")) {
            notificationPopup.setIcon(NotificationIcon.database);

        }
        int time = seconds*1000;
        notificationPopup.setDisplayTime(time);

        final WebClock clock = new WebClock();
        clock.setClockType ( ClockType.timer );
        clock.setTimeLeft ( time+1000 );
        clock.setTimePattern ( "'"+str+" ('ss')'" );
        notificationPopup.setContent(new GroupPanel(clock));

        NotificationManager.showNotification(notificationPopup);
        clock.start();
    }

    public static void showCloseOption(String str, String option) {
        final WebNotificationPopup notificationPopup = new WebNotificationPopup();
        if (option.equals("error")) {
            notificationPopup.setIcon(NotificationIcon.error);

        } else if (option.equals("info")) {
            notificationPopup.setIcon(NotificationIcon.information);

        } else if (option.equals("app")) {
            notificationPopup.setIcon(NotificationIcon.application);

        } else if (option.equals("calendar")) {
            notificationPopup.setIcon(NotificationIcon.calendar);

        } else if (option.equals("clock")) {
            notificationPopup.setIcon(NotificationIcon.clock);

        } else if (option.equals("warning")) {
            notificationPopup.setIcon(NotificationIcon.warning);

        } else if (option.equals("message")) {
            notificationPopup.setIcon(NotificationIcon.tip);

        } else if (option.equals("question")) {
            notificationPopup.setIcon(NotificationIcon.question);

        } else if (option.equals("database")) {
            notificationPopup.setIcon(NotificationIcon.database);

        }
        notificationPopup.setContent(str);
        notificationPopup.setOptions(NotificationOption.accept, NotificationOption.cancel);
        notificationPopup.addNotificationListener(new NotificationListener() {
            @Override
            public void optionSelected(NotificationOption notificationOption) {
                if (notificationOption == NotificationOption.accept) {
                    if (Gioco.frame.gioco_panel != null) Gioco.frame.gioco_panel.setRunning(false);
                    Gioco.frame.dispose();
                }
            }

            @Override
            public void accepted() {

            }

            @Override
            public void closed() {

            }
        });
        NotificationManager.showNotification(notificationPopup);
    }
}
