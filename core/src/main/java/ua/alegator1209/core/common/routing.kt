package ua.alegator1209.core.common

sealed class Stage {
    object Login : Stage()
    object Profile : Stage()
    object Repositories : Stage()
}

interface Router {
    fun goTo(stage: Stage)
    fun returnTo(stage: Stage)
    fun replaceTo(stage: Stage)
}
