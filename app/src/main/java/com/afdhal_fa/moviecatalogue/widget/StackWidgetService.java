package com.afdhal_fa.moviecatalogue.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetAdapter(this.getApplicationContext());
    }
}
