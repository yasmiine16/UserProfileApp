package ys.pam.userprofile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ys.pam.userprofile.ui.theme.DarkMaroon
import ys.pam.userprofile.ui.theme.LightPink
import ys.pam.userprofile.ui.theme.MaroonButton
import ys.pam.userprofile.ui.theme.MaroonPrimary


class ProfileActivity : ComponentActivity() {

    // status avatar
    private var showBrow by mutableStateOf(true)
    private var showEye by mutableStateOf(true)
    private var showNose by mutableStateOf(true)
    private var showMouth by mutableStateOf(true)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadAvatar()

        // ngambil data regist
        val preferences = getSharedPreferences(
            "user_data",
            MODE_PRIVATE
        )

        val firstName = preferences.getString(
            "firstName",
            ""
        ) ?: ""

        val lastName = preferences.getString(
            "lastName",
            ""
        ) ?: ""
        val fullName = "$firstName $lastName".trim()

        val username = preferences.getString(
            "username",
            ""
        ) ?: ""

        val email = preferences.getString(
            "email",
            ""
        ) ?: ""

        val nim = preferences.getString(
            "nim",
            ""
        ) ?: ""

        val phoneNumber = preferences.getString(
            "phoneNumber",
            ""
        ) ?: ""

        val birthDate = preferences.getString(
            "birthDate",
            ""
        ) ?: ""


        setContent {

            ProfileScreen(
                fullName = fullName,
                username = username,
                email = email,
                nim = nim,
                phoneNumber = phoneNumber,
                birthDate = birthDate,

                showBrow = showBrow,
                showEye = showEye,
                showNose = showNose,
                showMouth = showMouth,

                onAvatar = {

                    startActivity(
                        Intent(
                            this,
                            AvatarActivity::class.java
                        )
                    )
                },

                onLogout = {

                    // balik ke Login
                    val intent = Intent(
                        this,
                        LoginActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                }
            )
        }
    }


    override fun onResume() {
        super.onResume()

        loadAvatar()
    }


    private fun loadAvatar() {

        val preferences = getSharedPreferences(
            "user_data",
            MODE_PRIVATE
        )

        showBrow = preferences.getBoolean(
            "avatar_brow",
            true
        )

        showEye = preferences.getBoolean(
            "avatar_eye",
            true
        )

        showNose = preferences.getBoolean(
            "avatar_nose",
            true
        )

        showMouth = preferences.getBoolean(
            "avatar_mouth",
            true
        )
    }
}


@Composable
fun ProfileScreen(
    fullName: String,
    username: String,
    email: String,
    nim: String,
    phoneNumber: String,
    birthDate: String,

    showBrow: Boolean,
    showEye: Boolean,
    showNose: Boolean,
    showMouth: Boolean,

    onAvatar: () -> Unit,
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPink)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Profil Pengguna",
            color = DarkMaroon,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        // avatar

        ProfileAvatar(
            showBrow = showBrow,
            showEye = showEye,
            showNose = showNose,
            showMouth = showMouth
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // data diri

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(text = "Nama lengkap : $fullName")
            Spacer(Modifier.height(8.dp))

            Text(
                text = "Nama pengguna : $username"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Email : $email"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "NIM : $nim"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Nomor telepon : $phoneNumber"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Tanggal lahir : $birthDate"
            )
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // edit avatar
        Button(
            onClick = onAvatar,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaroonButton
            )
        ) {
            Text("Edit Avatar")
        }


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        // log out

        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaroonPrimary
            )
        ) {
            Text("Keluar")
        }
    }
}

@Composable
fun ProfileAvatar(
    showBrow: Boolean,
    showEye: Boolean,
    showNose: Boolean,
    showMouth: Boolean
) {
    Box(
        modifier = Modifier.size(
            width = 180.dp,
            height = 255.dp
        ),
        contentAlignment = Alignment.TopCenter
    ) {

        // base
        Image(
            painter = painterResource(id = R.drawable.face_0004),
            contentDescription = "Avatar",
            modifier = Modifier.size(
                width = 180.dp,
                height = 256.dp
            ),
            contentScale = ContentScale.Fit
        )

        // alis
        if (showBrow) {
            Image(
                painter = painterResource(id = R.drawable.face_0001),
                contentDescription = "Alis",
                modifier = Modifier
                    .width(114.dp)
                    .aspectRatio(229f / 64f)
                    .align(Alignment.TopCenter)
                    .offset(y = 90.dp),
                contentScale = ContentScale.Fit
            )
        }

        // mata
        if (showEye) {
            Image(
                painter = painterResource(id = R.drawable.face_0003),
                contentDescription = "Mata",
                modifier = Modifier
                    .width(114.dp)
                    .aspectRatio(607f / 176f)
                    .align(Alignment.TopCenter)
                    .offset(y = 110.dp),
                contentScale = ContentScale.Fit
            )
        }

        // hidung
        if (showNose) {
            Image(
                painter = painterResource(id = R.drawable.face_0002),
                contentDescription = "Hidung",
                modifier = Modifier
                    .width(36.dp)
                    .aspectRatio(153f / 80f)
                    .align(Alignment.TopCenter)
                    .offset(y = 140.dp),
                contentScale = ContentScale.Fit
            )
        }

        // mulut
        if (showMouth) {
            Image(
                painter = painterResource(id = R.drawable.face_0000),
                contentDescription = "Mulut",
                modifier = Modifier
                    .width(48.dp)
                    .aspectRatio(257f / 128f)
                    .align(Alignment.TopCenter)
                    .offset(y = 165.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}