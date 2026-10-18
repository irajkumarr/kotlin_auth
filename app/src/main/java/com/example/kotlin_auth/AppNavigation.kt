package com.example.kotlin_auth

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_auth.screens.LoginScreen
import com.example.kotlin_auth.screens.SignupScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable ("login"){
            LoginScreen(
                {
                    navController.navigate("signup")
                }
            )
        }

        composable("signup") {
            SignupScreen(
                { navController.popBackStack() }
            )
        }
    }
}