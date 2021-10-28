package ua.alegator1209.core_ui

import android.app.Application

abstract class BaseApplication : Application() {
    abstract val baseComponent: BaseComponent
}
