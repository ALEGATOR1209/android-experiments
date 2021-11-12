package ua.alegator1209.core_ui.animation

import androidx.annotation.AnimRes
import ua.alegator1209.core_ui.R

internal sealed class AnimationSet(
    @AnimRes val enter: Int,
    @AnimRes val exit: Int,
) {
    object Forward : AnimationSet(R.anim.slide_from_right, R.anim.slide_to_left)
    object Backward : AnimationSet(R.anim.slide_from_left, R.anim.slide_to_right)
}
