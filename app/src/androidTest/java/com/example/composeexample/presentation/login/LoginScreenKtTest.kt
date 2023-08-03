package com.example.composeexample.presentation.login

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LoginScreenKtTest{
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testClickLoginButton(){
        var logged = false
        rule.setContent {
            LoginScreen (loginComplete = {
                logged = true
            })
        }

        rule.onNodeWithTag(LOGIN_TEXT_FIELD_USER).performTextInput("algun@login.com")
        rule.onNodeWithTag(LOGIN_TEXT_FIELD_PASSWORD).performTextInput("12345")

        rule.onNodeWithTag(LOGIN_TEXT_FIELD_BUTTON).performClick()

        assertEquals(logged,true)
    }
}