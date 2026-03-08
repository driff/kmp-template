package com.driff.template.di

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate

@Component
abstract class AppComponent {



}

@KmpComponentCreate
expect fun createAppComponent(): AppComponent