package com.example.app_widget_demo

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.widget.RemoteViews
import es.antonborri.home_widget.HomeWidgetBackgroundIntent
import es.antonborri.home_widget.HomeWidgetLaunchIntent
import es.antonborri.home_widget.HomeWidgetProvider

class HomeScreenWidgetProvider : HomeWidgetProvider() {
     override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray, widgetData: SharedPreferences) {
        appWidgetIds.forEach { widgetId ->
            val views = RemoteViews(context.packageName, R.layout.widget_layout).apply {

                // Open App on Widget Click
                val pendingIntent = HomeWidgetLaunchIntent.getActivity(context,
                        MainActivity::class.java)
                setOnClickPendingIntent(R.id.widget_root, pendingIntent)

                val counter = widgetData.getInt("_counter", 0)

                var counterText = "$counter"


                setTextViewText(R.id.tv_counter, counterText)

                val backgroundIntentIncrement = HomeWidgetBackgroundIntent.getBroadcast(context,
                        Uri.parse("myAppWidget://incrementcounter"))
                setOnClickPendingIntent(R.id.bt_increment, backgroundIntentIncrement)
                val backgroundIntentDecrement = HomeWidgetBackgroundIntent.getBroadcast(context,
                        Uri.parse("myAppWidget://decrementcounter"))
                setOnClickPendingIntent(R.id.bt_decrement, backgroundIntentDecrement)
                val backgroundIntentReset = HomeWidgetBackgroundIntent.getBroadcast(context,
                Uri.parse("myAppWidget://resetcounter"))
                setOnClickPendingIntent(R.id.bt_reset, backgroundIntentReset)
            }
            appWidgetManager.updateAppWidget(widgetId, views)
        }
    }
}