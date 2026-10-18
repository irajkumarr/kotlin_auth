package com.example.kotlin_auth.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_auth.R

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignupScreen(
    onLoginClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        val context = LocalContext.current
        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var isObscurePasswordText by remember { mutableStateOf(true) }
        var isObscureConfirmPasswordText by remember { mutableStateOf(true) }
        var termsAndConditions by remember { mutableStateOf(false) }
        // Validations
        var fullNameError by remember { mutableStateOf<String?>(null) }
        var emailError by remember { mutableStateOf<String?>(null) }
        var phoneError by remember { mutableStateOf<String?>(null) }
        var passwordError by remember { mutableStateOf<String?>(null) }
        var confirmPasswordError by remember { mutableStateOf<String?>(null) }
        var termsError by remember { mutableStateOf<String?>(null) }

        fun validateForm(): Boolean {
            fullNameError = when {
                fullName.isBlank() ->
                    "Full name is required"

                fullName.trim().length < 2 ->
                    "Name must be at least 2 characters"

                else -> null
            }

            emailError = when {
                email.isBlank() ->
                    "Email is required"

                !android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email.trim())
                    .matches() ->
                    "Enter a valid email address"

                else -> null
            }

            phoneError = when {
                phone.isBlank() ->
                    "Mobile number is required"

                !phone.matches(Regex("^9\\d{9}$")) ->
                    "Enter a valid 10-digit Nepali mobile number"

                else -> null
            }

            passwordError = when {
                password.isBlank() ->
                    "Password is required"

                password.length < 8 ->
                    "Password must be at least 8 characters"

                !password.any { it.isUpperCase() } ->
                    "Include at least one uppercase letter"

                !password.any { it.isLowerCase() } ->
                    "Include at least one lowercase letter"

                !password.any { it.isDigit() } ->
                    "Include at least one number"

                !password.any { !it.isLetterOrDigit() } ->
                    "Include at least one special character"

                else -> null
            }

            confirmPasswordError = when {
                confirmPassword.isBlank() ->
                    "Please confirm your password"

                confirmPassword != password ->
                    "Passwords do not match"

                else -> null
            }

            termsError = if (!termsAndConditions) {
                "Please accept the Terms & Conditions"
            } else {
                null
            }

            return fullNameError == null &&
                    emailError == null &&
                    phoneError == null &&
                    passwordError == null &&
                    confirmPasswordError == null &&
                    termsError == null
        }


        Image(
            painter = painterResource(R.drawable.jci_logo),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally).height(100.dp).width(150.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Get Started Now",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Create an account to explore about our app",
            color = Color.Gray,
            fontSize = 15.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.size(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)).padding(16.dp),
        ) {
            Text(
                text = "Full Name",
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.W500
            )
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text(text = "Enter your full name") },
                isError = fullNameError != null,
                supportingText = {
                    fullNameError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Enter your email",
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.W500
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text(text = "example@gmail.com") },
                isError = emailError != null,
                supportingText = {
                    emailError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Mobile Number",
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.W500
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text(text = "9800000000") },
                isError = phoneError != null,
                supportingText = {
                    phoneError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Password",
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.W500
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text(text = "*********") },
                visualTransformation = if (isObscurePasswordText) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        imageVector = if (isObscurePasswordText) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable {
                            isObscurePasswordText = !isObscurePasswordText
                        },
                        Color.Gray

                    )
                },
                isError = passwordError != null,
                supportingText = {
                    passwordError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Confirm Password",
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.W500
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text(text = "*********") },
                visualTransformation = if (isObscureConfirmPasswordText) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        imageVector = if (isObscureConfirmPasswordText) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable {
                            isObscureConfirmPasswordText = !isObscureConfirmPasswordText
                        },
                        Color.Gray

                    )
                },
                isError = confirmPasswordError != null,
                supportingText = {
                    confirmPasswordError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                singleLine = true
            )
            Row(
                modifier = Modifier.clickable {
                    termsAndConditions = !termsAndConditions
                }, verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = termsAndConditions,
                    onCheckedChange = {
                        termsAndConditions = it
                        if (it) termsError = null
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Blue,  // The box background color when checked
                        checkmarkColor = Color.White,       // The internal checkmark tick color
                    ),
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = "I agree the T & C of App Name",
                    color = Color.DarkGray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500
                )
            }
            termsError?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Button(
                onClick = {
                    if (validateForm()) {
                        Toast.makeText(
                            context,
                            "All validations passed!",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Call your signup ViewModel here.
                        // Validation alone does not create an account.
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues()
            ) {

                Box(
                    modifier = Modifier.fillMaxWidth().height(50.dp).background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF1E80B9),
                                Color(0xFF0A4E7E),
                            ) // Green gradient example
                        ), shape = RoundedCornerShape(8.dp)
                    ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Signup",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account?",
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Login",
                    color = Color.Blue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onLoginClicked()
                    })
            }
        }
    }
}