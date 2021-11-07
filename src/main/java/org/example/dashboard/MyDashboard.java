package org.example.dashboard;

import org.example.Sidebar;

public class MyDashboard extends Sidebar {

    public static void removeCalendarFilters(){}

    public static void removeCalendarFilter(String ...filter){}

    public static void applyCalendarFilter(String ...filter){}

    public static void viewNextPrevCalendarMonth(String direction){}

    //viewType - Month, Week, Day, List
    public static void switchToCalendarView(String viewType){}

    public void navigateToPendingActionPage(String pendingAction){}

    public void verifydisplayOfWidgets(String widgetType){}

    public void addNewWidget(String widgetType, String widgetName){}

    public void addNewWidget(String widgetName){}

    public void closeWidgetAdditionEditMode(){}

    public void navigateToCalendarEvent(String date){}

    public void navigateToCalendarEvent(int day){}

}
