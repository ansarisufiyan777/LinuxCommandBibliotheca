package com.inspiredandroid.linuxcommandbibliotheca.sql;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by simon on 29.11.15.
 */
public class BookmarkManager {

    private static final String KEY_APP_OPEN_COUNT = "KEY_APP_OPEN_COUNT";
    private static final String KEY_RATING_DIALOG_STATE = "KEY_RATING_DIALOG_STATE";
    private static final String KEY_BOOKMARKS = "KEY_BOOKMARKS";
    private static final String KEY_BOOKMARKCHANGED = "KEY_BOOKMARKCHANGED";

    public static boolean shouldShowRateDialog(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if(prefs.getInt(KEY_RATING_DIALOG_STATE, 0) == 0) {

            SharedPreferences.Editor edit = prefs.edit();
            edit.putInt(KEY_APP_OPEN_COUNT, prefs.getInt(KEY_APP_OPEN_COUNT, 0) +1);
            edit.apply();

            if (prefs.getInt(KEY_APP_OPEN_COUNT, 0) % 10 == 0) {
                return true;
            }
        }
        return false;
    }

    public static void disableRateDialog(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt(KEY_RATING_DIALOG_STATE, 1);
        edit.apply();
    }

    /**
     * get arraylist of ids
     *
     * @param context
     * @return
     */
    public static ArrayList<Long> getBookmarkIds(Context context) {
        String[] bookmarkIds = getBookmarkIdsChain(context).split(",");
        ArrayList<Long> ids = new ArrayList<>();
        for (String id : bookmarkIds) {
            if (!id.isEmpty()) {
                ids.add(Long.valueOf(id));
            }
        }
        return ids;
    }

    /**
     * get list of ids divided by commas
     *
     * @param context
     * @return
     */
    public static String getBookmarkIdsChain(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String bookmarksChain = prefs.getString(KEY_BOOKMARKS, "");
        if (!bookmarksChain.isEmpty()) {
            bookmarksChain = bookmarksChain.substring(1);
        }
        return bookmarksChain;
    }

    /**
     * add id to bookmark list
     *
     * @param context
     * @param id
     */
    public static void addBookmark(Context context, long id) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String bookmarksChain = prefs.getString(KEY_BOOKMARKS, "");
        bookmarksChain += "," + id;
        prefs.edit().putString(KEY_BOOKMARKS, bookmarksChain).apply();
        bookmarkHasChanged(context);
    }

    /**
     * is id bookmarked
     *
     * @param context
     * @param id
     * @return
     */
    public static boolean hasBookmark(Context context, long id) {
        return getBookmarkIds(context).contains(id);
    }

    /**
     * remove id from list
     *
     * @param context
     * @param id
     */
    public static void removeBookmark(Context context, long id) {
        ArrayList<Long> bookmarksIds = getBookmarkIds(context);

        bookmarksIds.remove(id);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String bookmarksChain = "";
        for (Long bookmark : bookmarksIds) {
            bookmarksChain += "," + bookmark;
        }
        prefs.edit().putString(KEY_BOOKMARKS, bookmarksChain).apply();

        bookmarkHasChanged(context);
    }

    /**
     * Set a flag for the list view that the content has changed
     *
     * @param context
     */
    private static void bookmarkHasChanged(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(KEY_BOOKMARKCHANGED, true).apply();
    }

    /**
     * Reads the flag if bookmark has changed, automatically resets flag
     *
     * @param context
     * @return
     */
    public static boolean hasBookmarkChanged(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean changed = prefs.getBoolean(KEY_BOOKMARKCHANGED, false);
        prefs.edit().putBoolean(KEY_BOOKMARKCHANGED, false).apply();
        return changed;
    }
}
