package com.bindiya.ejyleassessment.presentation.route

sealed class AppScreen(val route:String){
    object HomeScreen:AppScreen(ConstantAppScreenName.HOME_SCREEN)
}
object ConstantAppScreenName{
    const val HOME_SCREEN="home_screen"
}