package ua.alegator1209.core_ui.ui.application

import android.app.Application
import ua.alegator1209.core_ui.di.BaseComponent

abstract class BaseApplication : Application() {
    abstract val baseComponent: BaseComponent
}
