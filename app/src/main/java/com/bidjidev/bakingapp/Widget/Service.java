package com.bidjidev.bakingapp.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by You on 8/27/17.
 */

public class BakingAppWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        RemoteView data = new RemoteView(this, intent);
        return data;
    }
}
