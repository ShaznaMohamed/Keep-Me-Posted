package com.keepmeposted.utility.misc;

import android.content.Context;

import com.keepmeposted.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;


/**
 * Created by dsraj on 10/28/2016.
 */

public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/ClanPro-Book.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}
