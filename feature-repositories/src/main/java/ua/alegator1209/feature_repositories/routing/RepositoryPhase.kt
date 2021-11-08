package ua.alegator1209.feature_repositories.routing

import ua.alegator1209.core.common.Phase

sealed class RepositoryPhase : Phase {
    object List : RepositoryPhase()
    object Detailed : RepositoryPhase()
}
