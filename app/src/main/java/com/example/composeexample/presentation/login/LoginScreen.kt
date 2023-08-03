package com.example.composeexample.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeexample.R
import com.example.composeexample.ui.theme.ComposeExampleTheme


const val TAG = "LOGIN"
const val LOGIN_TEXT_FIELD_USER = "USER-TEST-TAG"
const val LOGIN_TEXT_FIELD_PASSWORD = "PASS-TEST-TAG"
const val LOGIN_TEXT_FIELD_BUTTON = "BTN-TEST-TAG"

@Composable
fun LoginScreen(
    loginComplete: () -> Unit
){

    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Header()
            Spacer(modifier = Modifier.height(18.dp))
            LoginForm(loginComplete)
        }
    }
}

@Composable
fun Header(){

    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Image Logo",
        Modifier.size(150.dp))
    Text("Android Superpoderes", fontSize = 28.sp)

}

@Composable
fun LoginForm(loginComplete: () -> Unit) {
    EmailTextField(modifier = Modifier.testTag(LOGIN_TEXT_FIELD_USER)){ s->{}}
    PasswordTextField(modifier = Modifier.testTag(LOGIN_TEXT_FIELD_PASSWORD)){ s->{}}
    Spacer(modifier = Modifier.height(18.dp))
    Button(
        modifier = Modifier
            .testTag(LOGIN_TEXT_FIELD_BUTTON)
            .fillMaxWidth(),
        onClick = { loginComplete.invoke()}) {
        Text(text = "LOG IN")
    }

}


@Composable
fun EmailTextField(modifier: Modifier,onChange: (String) -> Unit,){
    CustomEditTex(modifier = modifier,hintValue = "Email", onChange = onChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Icono email"
            )
        },
        keyBordType = KeyboardType.Email
    )
}

@Composable
fun PasswordTextField( modifier: Modifier,onChange: (String) -> Unit,){

    var showPassword by remember { mutableStateOf(false) }

    CustomEditTex(
        hintValue = "Password", onChange = onChange,
        keyBordType = KeyboardType.Password,
        showPassword = showPassword,
        leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Icono password") },
        trialingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    imageVector = if(showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (showPassword) "Show Password" else "Hide Password"
                )
            }
        },
        modifier = modifier
    )
}



@Composable
fun CustomEditTex(
    initValue: String = "",
    hintValue: String,
    onChange: (String) -> Unit,
    keyBordType: KeyboardType? = null,
    showPassword: Boolean = true,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trialingIcon: @Composable() (() -> Unit)? = null,
    modifier: Modifier,
){
    var value by remember {
        mutableStateOf(initValue)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
    ){

        OutlinedTextField(

            value = value,
            onValueChange = {newValue ->
                value = newValue
                onChange.invoke(newValue)
            },
            label = { Text(text = hintValue) },
            leadingIcon = leadingIcon,
            trailingIcon = trialingIcon,
            shape = RoundedCornerShape(12.dp),
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = keyBordType ?: KeyboardType.Ascii),
        )
    }
}




@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ComposeExampleTheme {
        LoginScreen{}
    }
}
