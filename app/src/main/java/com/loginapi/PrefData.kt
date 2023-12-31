package com.loginapi

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object PrefData {
    private const val PREFERENCE = "AncEventsPref"

    //strings
    const val IS_USER_LOGIN = "is_user_login"

    //model name
    const val KEY_USER_MODEL = "user_model"
    const val KEY_CONFIGURATION_MODEL="key_configuration_model"

    fun setBooleanPrefs(context: Context, prefKey: String, value: Boolean) {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE).edit()
            .putBoolean(prefKey, value).apply()
    }

    fun getBooleanPrefs(context: Context, prefKey: String): Boolean {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            .getBoolean(prefKey, false)
    }


    fun getBooleanPrefs(context: Context, prefKey: String, defaultValue: Boolean): Boolean {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            .getBoolean(prefKey, defaultValue)
    }

    fun setStringPrefs(context: Context, prefKey: String, Value: String) {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE).edit()
            .putString(prefKey, Value).apply()
    }

    fun getStringPrefs(context: Context, prefKey: String): String? {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            .getString(prefKey, null)
    }

    fun getStringPrefs(context: Context, prefKey: String, defaultValue: String): String {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            .getString(prefKey, defaultValue)
            .toString()
    }

    fun setIntPrefs(context: Context, prefKey: String, value: Int) {
        return context.getSharedPreferences(PREFERENCE, 0).edit().putInt(prefKey, value)
            .apply()
    }

    fun getIntPrefs(context: Context, prefKey: String): Int {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE).getInt(prefKey, 0)
    }

    fun getIntPrefs(context: Context, prefKey: String, defaultValue: Int): Int {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            .getInt(prefKey, defaultValue)
    }

    fun setLongPrefs(context: Context, prefKey: String, value: Long) {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE).edit()
            .putLong(prefKey, value).apply()
    }

    fun getLongPrefs(context: Context, prefKey: String): Long {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            .getLong(prefKey, 0)
    }

    fun getLongPrefs(context: Context, prefKey: String, defaultValue: Long): Long {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            .getLong(prefKey, defaultValue)
    }

    fun setFloatPrefs(context: Context, prefKey: String, value: Float) {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE).edit()
            .putFloat(prefKey, value).apply()
    }

    fun getFloatPrefs(context: Context, prefKey: String): Float {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            .getFloat(prefKey, 0f)
    }

    fun getFloatPrefs(context: Context, prefKey: String, defaultValue: Long): Float {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
            .getFloat(prefKey, defaultValue.toFloat())
    }


    /**
     * Clear all data in SharedPreference
     *
     * @param context
     */
    fun clearWholePreference(context: Context): Boolean {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE).edit().clear()
            .commit()
    }

    /**
     * Clear single key value
     *
     * @param prefKey
     * @param context
     */
    fun remove(context: Context, prefKey: String) {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE).edit()
            .remove(prefKey).apply()
    }

    //getter and setter method for models
    inline fun <reified T> getModel(context: Context, prefKey: String): T? {
        val goon = Gson()
        val json = PreferenceManager.getDefaultSharedPreferences(context).getString(prefKey, null)
        val type = object : TypeToken<T>() {}.type
        return goon.fromJson<T>(json, type)
    }

    inline fun <reified T> setModel(context: Context, prefKey: String, model: T) {
        val goon = Gson()
        val json = goon.toJson(model)
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(prefKey, json)
            .apply()
    }


    //getter and setter arraylist
    inline fun <reified T> setArraylist(
        context: Context?,
        prefKey: String?,
        arraylist: ArrayList<T>
    ) {
        val gson = Gson()
        val json = gson.toJson(arraylist)
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(prefKey, json).apply()
        }
    }


    inline fun <reified T> getArraylist(
        context: Context?,
        prefKey: String?
    ): ArrayList<T>? {
        val gson = Gson()
        val json =
            context?.let {
                PreferenceManager.getDefaultSharedPreferences(it)
                    .getString(prefKey, ",")
            }
        val type: Type = object :
            TypeToken<ArrayList<T>?>() {}.type
        return gson.fromJson(json, type)
    }
}
