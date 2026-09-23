package ys.pam.userprofile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.foundation.background
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import ys.pam.userprofile.ui.theme.LightPink
import ys.pam.userprofile.ui.theme.MaroonPrimary
import ys.pam.userprofile.ui.theme.MaroonButton
import ys.pam.userprofile.ui.theme.SoftBorder
import ys.pam.userprofile.ui.theme.SoftText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ambil data yang  disimpan pas regist
        val preferences = getSharedPreferences(
            "user_data",
            MODE_PRIVATE
        )

        val savedUsername = preferences.getString(
            "username",
            ""
        ) ?: ""

        val savedPassword = preferences.getString(
            "password",
            ""
        ) ?: ""

        val savedEmail = preferences.getString(
            "email",
            ""
        ) ?: ""

        val savedPhoneNumber = preferences.getString(
            "phoneNumber",
            ""
        ) ?: ""
        setContent {
            LoginScreen(
                savedUsername = savedUsername,
                savedPassword = savedPassword,
                savedEmail = savedEmail,
                savedPhoneNumber = savedPhoneNumber,

                onLoginSuccess = {
                    println("Berhasil masuk!")
                    startActivity(
                        Intent(
                            this,
                            ProfileActivity::class.java
                        )
                    )

                    finish()
                }
            )
        }
    }
}

@Composable
fun LoginScreen(
    savedUsername: String,
    savedPassword: String,
    savedEmail: String,
    savedPhoneNumber: String,
    onLoginSuccess: () -> Unit
) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    var visiblePasswordIndex by remember {
        mutableStateOf(-1)
    }

    var showForgotPassword by remember {
        mutableStateOf(false)
    }

    var recoveryInput by remember {
        mutableStateOf("")
    }

    var recoveryMessage by remember {
        mutableStateOf("")
    }

    // biar karakter tersembunyi
    LaunchedEffect(password) {

        if (password.isNotEmpty()) {

            visiblePasswordIndex = password.length - 1

            delay(800)

            if (visiblePasswordIndex == password.length - 1) {
                visiblePasswordIndex = -1
            }

        } else {
            visiblePasswordIndex = -1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPink)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Selamat datang!",
            color = MaroonPrimary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Masuk untuk melanjutkan",
            color = SoftText,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(25.dp))

        // Username
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                errorMessage = ""
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaroonPrimary,
                unfocusedBorderColor = SoftBorder,
                focusedLabelColor = MaroonPrimary,
                unfocusedLabelColor = SoftText
            ),
            label = {
                Text("Nama pengguna")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorMessage = ""
            },
            label = {
                Text("Kata sandi")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaroonPrimary,
                unfocusedBorderColor = SoftBorder,
                focusedLabelColor = MaroonPrimary,
                unfocusedLabelColor = SoftText
            ),
            visualTransformation = LastCharacterPasswordTransformation(
                visibleIndex = visiblePasswordIndex
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        // Pesan error
        if (errorMessage.isNotEmpty()) {

            Text(
                text = errorMessage,
                color = Color.Red
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }

        // Login button
        Button(
            onClick = {
                if (username == savedUsername && password == savedPassword) {
                    onLoginSuccess()
                } else {
                    errorMessage = "Nama pengguna atau kata sandi salah"
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaroonButton
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Masuk")
        }



        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // Forgot Password
        TextButton(
            onClick = {
                recoveryInput = ""
                recoveryMessage = ""
                showForgotPassword = true
            }
        ) {
            Text(
                text = "Lupa kata sandi?",
                color = MaroonPrimary
            )
        }
        if (showForgotPassword) {
            AlertDialog(
                onDismissRequest = {
                    showForgotPassword = false
                },
                title = {
                    Text("Lupa kata sandi")
                },
                text = {
                    Column {

                        Text(
                            text = "Masukkan Email/nomor telepon yang terdaftar"
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        OutlinedTextField(
                            value = recoveryInput,
                            onValueChange = {
                                recoveryInput = it
                                recoveryMessage = ""
                            },
                            label = {
                                Text("Email / Nomor telepon")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaroonPrimary,
                                unfocusedBorderColor = SoftBorder,
                                focusedLabelColor = MaroonPrimary,
                                unfocusedLabelColor = SoftText
                            )
                        )

                        if (recoveryMessage.isNotEmpty()) {
                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = recoveryMessage,
                                color = MaroonPrimary
                            )
                        }
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (
                                recoveryInput == savedEmail ||
                                recoveryInput == savedPhoneNumber
                            ) {
                                recoveryMessage = "Data ditemukan."
                            } else {
                                recoveryMessage =
                                    "Email atau nomor telepon tidak terdaftar."
                            }
                        }
                    ) {
                        Text(
                            text = "Verifikasi",
                            color = MaroonPrimary
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showForgotPassword = false
                        }
                    ) {
                        Text("Batal")
                    }
                }
            )
        }

    }
}
