package com.example.dicket

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * [Application] subclass for the Dicket application.
 * Annotated with [HiltAndroidApp] for Dagger Hilt integration.
 */
@HiltAndroidApp
class DicketApplication : Application()
