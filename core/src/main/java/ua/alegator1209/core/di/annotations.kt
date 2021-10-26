package ua.alegator1209.core.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class PerApplication

@Scope
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class PerFeature

@Scope
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class PerFragment
