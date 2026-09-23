package ys.pam.userprofile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import ys.pam.userprofile.ui.theme.LightPink
import androidx.compose.material3.ButtonDefaults
import ys.pam.userprofile.ui.theme.MaroonButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ys.pam.userprofile.ui.theme.DarkMaroon


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightPink),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Selamat datang!",
                    color = DarkMaroon,
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Buat akun atau masuk",
                    color = DarkMaroon,
                    fontSize = 14.sp
                )

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

// login
                Button(
                    onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                LoginActivity::class.java
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaroonButton
                    ),
                    modifier = Modifier.width(180.dp)
                ) {
                    Text("Masuk")
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

// registrasi
                Button(
                    onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                RegistrationActivity::class.java
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaroonButton
                    ),
                    modifier = Modifier.width(180.dp)
                ) {
                    Text("Registrasi")
                }

                }
            }
        }
    }
