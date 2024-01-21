package com.example.app_widget_demo

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.widget.RemoteViews
import es.antonborri.home_widget.HomeWidgetBackgroundIntent
import es.antonborri.home_widget.HomeWidgetLaunchIntent
import es.antonborri.home_widget.HomeWidgetProvider

class HomeScreenWidgetProvider: Home WidgetProvider(){
    override fun onUpdate(context:Context,appWidgetManager: AppWidgetManager, appWidgetIds: IntArray, widgetData: SharedPreferences){
        appWidgetIds.forEach{widgetId=>val views=RemoteViews(context.packageName, R.layout.widget_layout).apply{
            val pendingIntent=HomeWidgetLaunchIntent.getActivity(context, MainActivity::class.java)
            setOnClickPendingIntent(R.id.widget_root, pendingIntent)

            val counter = widgetData.getInt("_couter",0)
            var couterText="Your counter value is : $counter"

            if(counter == 0){
                couterText = "Your have not pressed the counter button"
            }

            setTextViewText(R.id.tv_counter,counterText)

            val backgroundIntent = HomeWidgetBackgroundIntent.getBroadcast(context, Uri.parse("myAppWidget://updatecounter"))
            setOnClickPendingIntent(R.id.bt_update,backgroundIntent)
        }
        
        appWidgetManager.updateAppWidget(widgetId,views)

        }
    }
}