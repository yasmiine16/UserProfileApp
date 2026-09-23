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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.foundation.background
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ys.pam.userprofile.ui.theme.LightPink
import ys.pam.userprofile.ui.theme.MaroonPrimary
import ys.pam.userprofile.ui.theme.MaroonButton
import ys.pam.userprofile.ui.theme.SoftBorder
import ys.pam.userprofile.ui.theme.SoftText

class RegistrationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RegistrationScreen(
                onSave = {
                        firstName,
                        lastName,
                        nim,
                        username,
                        email,
                        password,
                        phoneNumber,
                        birthDate ->

                    val preferences = getSharedPreferences(
                        "user_data",
                        MODE_PRIVATE
                    )

                    preferences.edit()
                        .putString("firstName", firstName)
                        .putString("lastName", lastName)
                        .putString("username", username)
                        .putString("nim", nim)
                        .putString("email", email)
                        .putString("password", password)
                        .putString("phoneNumber", phoneNumber)
                        .putString("birthDate", birthDate)
                        .apply()

                    startActivity(
                        Intent(
                            this,
                            LoginActivity::class.java
                        )
                    )

                    finish()
                }
            )
        }
    }
}

@Composable
fun RegistrationScreen(
    onSave: (
        String,
        String,
        String,
        String,
        String,
        String,
        String,
        String
    ) -> Unit
) {

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var username by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var nim by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var birthDate by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    var visiblePasswordIndex by remember {
        mutableStateOf(-1)
    }

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
            .verticalScroll(rememberScrollState())
            .padding(24.dp),

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Buat akun",
            color = MaroonPrimary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Isi informasi berikut",
            color = SoftText,
            fontSize = 14.sp
        )


        OutlinedTextField(
            value = firstName,
            onValueChange = {
                firstName = it
            },
            label = {
                Text("Nama awal")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaroonPrimary,
                unfocusedBorderColor = SoftBorder,
                focusedLabelColor = MaroonPrimary,
                unfocusedLabelColor = SoftText
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            label = {
                Text("Nama akhir")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaroonPrimary,
                unfocusedBorderColor = SoftBorder,
                focusedLabelColor = MaroonPrimary,
                unfocusedLabelColor = SoftText
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = nim,
            onValueChange = {
                nim = it
            },
            label = {
                Text("NIM")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaroonPrimary,
                unfocusedBorderColor = SoftBorder,
                focusedLabelColor = MaroonPrimary,
                unfocusedLabelColor = SoftText
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text("Nama pengguna")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaroonPrimary,
                unfocusedBorderColor = SoftBorder,
                focusedLabelColor = MaroonPrimary,
                unfocusedLabelColor = SoftText
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )


        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaroonPrimary,
                unfocusedBorderColor = SoftBorder,
                focusedLabelColor = MaroonPrimary,
                unfocusedLabelColor = SoftText
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )


        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
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
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            label = {
                Text("Nomor telepon")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaroonPrimary,
                unfocusedBorderColor = SoftBorder,
                focusedLabelColor = MaroonPrimary,
                unfocusedLabelColor = SoftText
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = birthDate,
            onValueChange = {
                birthDate = it
            },
            label = {
                Text("Tanggal lahir")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaroonPrimary,
                unfocusedBorderColor = SoftBorder,
                focusedLabelColor = MaroonPrimary,
                unfocusedLabelColor = SoftText
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        if (errorMessage.isNotEmpty()) {

            Text(
                text = errorMessage,
                color = Color.Red
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }


        Button(
            onClick = {

                    if (
                        firstName.isBlank() ||
                        lastName.isBlank() ||
                        username.isBlank() ||
                        email.isBlank() ||
                        password.isBlank() ||
                        nim.isBlank() ||
                        phoneNumber.isBlank() ||
                        birthDate.isBlank()
                    ) {
                        errorMessage = "Semua data harus diisi"
                    } else {
                        errorMessage = ""

                        onSave(
                            firstName,
                            lastName,
                            nim,
                            username,
                            email,
                            password,
                            phoneNumber,
                            birthDate
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaroonButton
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }
    }
}



class LastCharacterPasswordTransformation(
    private val visibleIndex: Int
) : VisualTransformation {

    override fun filter(text: androidx.compose.ui.text.AnnotatedString):
            androidx.compose.ui.text.input.TransformedText {

        val transformedText = buildString {

            text.forEachIndexed { index, char ->

                if (index == visibleIndex) {
                    append(char)
                } else {
                    append("•")
                }
            }
        }

        return androidx.compose.ui.text.input.TransformedText(
            text = androidx.compose.ui.text.AnnotatedString(
                transformedText
            ),
            offsetMapping = OffsetMapping.Identity
        )
    }
}