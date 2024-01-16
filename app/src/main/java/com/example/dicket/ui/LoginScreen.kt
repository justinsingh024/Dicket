package com.example.dicket.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicket.R

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClicked: (String, String) -> Boolean,
    onLoginFailed: () -> Unit // Callback für den Fall, dass die Anmeldung fehlschlägt
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //val focusManager = LocalFocusManager.current
    //val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111620))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.spash_logo_big_login),
            contentDescription = null,
            modifier = Modifier.size(190.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-Mail", color = Color(0xFFC4D3F0)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = Color(0xFFC4D3F0)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color(0xFFC4D3F0)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFC4D3F0)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),

            )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val loginCheck = onLoginClicked(email, password)
                if (!loginCheck) onLoginFailed()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 8.dp, end = 8.dp), // Adjust the height as needed,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(
                    255,
                    128,
                    54
                )
            ), // Change the color as needed
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "Login",
                fontSize = 18.sp, // Adjust the font size as needed
            )
        }
    }
}
/*
@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginClicked = { _, _ -> })
}
 */