/*
 * Copyright 2014 Devmil Solutions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.devmil.muzei.bingimageofthedayartsource;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;

import de.devmil.muzei.bingimageofthedayartsource.cache.CacheUtils;

/**
 * Created by devmil on 24.02.14.
 *
 * This content provider provides access to the cached images
 */
public class BingImageContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "de.devmil.muzei.bingimageofthedayartsource.provider.BingImages";
    private static final String TAG = BingImageContentProvider.class.getName();

    public static final Uri CONTENT_URI = Uri.parse("content://"
            + PROVIDER_NAME + "/images");

    public static Uri getContentUri(String fileName, boolean useSalt)
    {
        return Uri.parse(CONTENT_URI.toString()
                + "/"
                + fileName
                + "/"
                + (useSalt ? Long.toString(Calendar.getInstance()
                .getTimeInMillis()) : ""));
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException
    {
        String fileName = uri.getPathSegments().get(1);

        File file = new File(CacheUtils.getCacheDirectory(getContext()), fileName);
        if(!file.exists())
            throw new FileNotFoundException();

        return ParcelFileDescriptor.open(file,
                ParcelFileDescriptor.MODE_READ_ONLY);
    }
}
