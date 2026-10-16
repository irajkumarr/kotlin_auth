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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_auth.R

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isObscureText by remember { mutableStateOf(true) }
    var rememberMe by remember { mutableStateOf(false) }
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        // Logo
        Image(
            painter = painterResource(R.drawable.jci_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(100.dp)
                .width(150.dp),
            contentScale = ContentScale.Crop
        )
        // Login text
        Text(
            text = "Log in to your",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Account",
            color = Color.Blue,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Enter your email and password to login in.",
            color = Color.Gray,
            fontSize = 15.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.size(16.dp))
        // Box with form field with proper validation, Remember me checkbox and forgot password button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Email or phone no.",
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.W500
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text(text = "example@gmail.com") }
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
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text(text = "*********") },
                visualTransformation = if (isObscureText) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        imageVector = if (isObscureText) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                isObscureText = !isObscureText
                            },
                        Color.Gray

                    )
                }
            )

            // Row with remember me checkbox and forgot password button
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier
                        .clickable {
                            rememberMe = !rememberMe
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Blue,  // The box background color when checked
                            checkmarkColor = Color.White,       // The internal checkmark tick color
                        ),
                        modifier = Modifier.padding(0.dp)
                    )
                    Text(
                        text = "Remember Me",
                        color = Color.DarkGray,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W500
                    )
                }

                Text(
                    text = "Forgot Password?",
                    color = Color.Blue,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.clickable {
                        // show toast for now
                        Toast.makeText(
                            context,
                            "Forgot Password clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )

            }
// Login Button
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF1E80B9),
                                    Color(0xFF0A4E7E),
                                ) // Green gradient example
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Login",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                    )
                }
            }
            // Or with
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically // Keeps lines and text perfectly centered horizontally
            ) {
                // Left Line
                Box(modifier = Modifier.weight(1f)) {
                    HorizontalDivider(
                        modifier = Modifier.padding(end = 16.dp), // Pushes line away from text
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }

                // Center Text
                Text(
                    text = "Or with",
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500
                )

                // Right Line
                Box(modifier = Modifier.weight(1f)) {
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 16.dp), // Pushes line away from text
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
            }

            // Social media Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .border(
                            1.dp,
                            Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            Color.LightGray.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .clickable {
                            // show toast for now
                            Toast.makeText(
                                context,
                                "Facebook clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)

                ) {
                    Icon(
                        imageVector = Icons.Filled.Facebook,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp),
                        Color.Blue
                    )

                    Text(
                        text = "Sign in\nwith Facebook",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        lineHeight = 16.sp
                    )
                }
                // Another Icon
                Row(
                    modifier = Modifier
                        .border(
                            1.dp,
                            Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            Color.LightGray.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .clickable {
                            // show toast for now
                            Toast.makeText(
                                context,
                                "Facebook clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)

                ) {
                    Icon(
                        imageVector = Icons.Filled.Facebook,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp),
                        Color.Blue
                    )

                    Text(
                        text = "Sign in\nwith Facebook",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        lineHeight = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            // Signup Text
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Sign Up",
                    color = Color.Blue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        // show toast for now
                        Toast.makeText(
                            context,
                            "Sign Up clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }


    }
}