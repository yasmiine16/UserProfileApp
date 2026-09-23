package ys.pam.userprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AvatarActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ngambil avatar yang sebelumnya disimpan
        val preferences = getSharedPreferences(
            "user_data",
            MODE_PRIVATE
        )

        val savedBrow = preferences.getBoolean(
            "avatar_brow",
            true
        )

        val savedEye = preferences.getBoolean(
            "avatar_eye",
            true
        )

        val savedNose = preferences.getBoolean(
            "avatar_nose",
            true
        )

        val savedMouth = preferences.getBoolean(
            "avatar_mouth",
            true
        )

        setContent {

            AvatarScreen(
                initialBrow = savedBrow,
                initialEye = savedEye,
                initialNose = savedNose,
                initialMouth = savedMouth,

                onSave = {
                    preferences.edit()
                        .putBoolean(
                            "avatar_brow",
                            it.brow
                        )
                        .putBoolean(
                            "avatar_eye",
                            it.eye
                        )
                        .putBoolean(
                            "avatar_nose",
                            it.nose
                        )
                        .putBoolean(
                            "avatar_mouth",
                            it.mouth
                        )
                        .apply()

                    // balik ke Profile
                    finish()
                }
            )
        }
    }
}


// pilihan avatar
data class AvatarSettings(
    val brow: Boolean,
    val eye: Boolean,
    val nose: Boolean,
    val mouth: Boolean
)


@Composable
fun AvatarScreen(
    initialBrow: Boolean,
    initialEye: Boolean,
    initialNose: Boolean,
    initialMouth: Boolean,
    onSave: (AvatarSettings) -> Unit
) {

    var showBrow by remember {
        mutableStateOf(initialBrow)
    }

    var showEye by remember {
        mutableStateOf(initialEye)
    }

    var showNose by remember {
        mutableStateOf(initialNose)
    }

    var showMouth by remember {
        mutableStateOf(initialMouth)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFFFE1E5)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 20.dp
            ),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Avatar",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5C234A)
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )


        Box(
            modifier = Modifier.size(
                width = 300.dp,
                height = 420.dp
            ),
            contentAlignment = Alignment.TopCenter
        ) {

            // base
            Image(
                painter = painterResource(
                    id = R.drawable.face_0004
                ),
                contentDescription = "Base Face",
                modifier = Modifier
                    .size(
                        width = 300.dp,
                        height = 427.dp
                    ),
                contentScale = ContentScale.Fit
            )



            if (showBrow) {

                Image(
                    painter = painterResource(
                        id = R.drawable.face_0001
                    ),
                    contentDescription = "Eyebrows",
                    modifier = Modifier
                        .width(190.dp)
                        .aspectRatio(229f / 64f)
                        .align(Alignment.TopCenter)
                        .offset(y = 150.dp),
                    contentScale = ContentScale.Fit
                )
            }


            if (showEye) {

                Image(
                    painter = painterResource(
                        id = R.drawable.face_0003
                    ),
                    contentDescription = "Eyes",
                    modifier = Modifier
                        .width(190.dp)
                        .aspectRatio(607f / 176f)
                        .align(Alignment.TopCenter)
                        .offset(y = 180.dp),
                    contentScale = ContentScale.Fit
                )
            }


            if (showNose) {

                Image(
                    painter = painterResource(
                        id = R.drawable.face_0002
                    ),
                    contentDescription = "Nose",
                    modifier = Modifier
                        .width(60.dp)
                        .aspectRatio(153f / 80f)
                        .align(Alignment.TopCenter)
                        .offset(y = 240.dp),
                    contentScale = ContentScale.Fit
                )
            }

            if (showMouth) {

                Image(
                    painter = painterResource(
                        id = R.drawable.face_0000
                    ),
                    contentDescription = "Mouth",
                    modifier = Modifier
                        .width(80.dp)
                        .aspectRatio(257f / 128f)
                        .align(Alignment.TopCenter)
                        .offset(y = 280.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Spacer(
            modifier = Modifier.height(5.dp)
        )
        // checkbox

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = showBrow,
                onCheckedChange = {
                    showBrow = it
                }
            )

            Text(
                text = "Alis",
                fontSize = 12.sp
            )


            Checkbox(
                checked = showEye,
                onCheckedChange = {
                    showEye = it
                }
            )

            Text(
                text = "Mata",
                fontSize = 12.sp
            )


            Checkbox(
                checked = showNose,
                onCheckedChange = {
                    showNose = it
                }
            )

            Text(
                text = "Hidung",
                fontSize = 12.sp
            )


            Checkbox(
                checked = showMouth,
                onCheckedChange = {
                    showMouth = it
                }
            )

            Text(
                text = "Mulut",
                fontSize = 12.sp
            )
        }


        Spacer(
            modifier = Modifier.height(10.dp)
        )



        Button(
            onClick = {

                onSave(
                    AvatarSettings(
                        brow = showBrow,
                        eye = showEye,
                        nose = showNose,
                        mouth = showMouth
                    )
                )
            }
        ) {
            Text(
                text = "Simpan Avatar"
            )
        }
    }
}