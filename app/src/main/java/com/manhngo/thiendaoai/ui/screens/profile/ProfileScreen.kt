package com.manhngo.thiendaoai.ui.screens.profile

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manhngo.thiendaoai.R
import com.manhngo.thiendaoai.data.repository.CultivationSystem
import com.manhngo.thiendaoai.ui.component.AppHeader
import com.manhngo.thiendaoai.ui.component.ProfileAnimation

@Composable
fun ProfileScreen(userViewModel: UserViewModel, modifier: Modifier = Modifier) {

    val stats by userViewModel.stats.collectAsState()
    //progress logic
    val progress = CultivationSystem.getProgress(stats)
    val percent = (progress * 100).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffaf6eb))
    ) {
        AppHeader()

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileAnimation()

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "${stats.canhGioi.displayName}",
                fontSize = 30.sp,
                color = Color(0xffCCA730),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Tiến độ tu vi",
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Tầng thứ ${stats.tang}",
            fontSize = 26.sp,
            modifier = Modifier.padding(horizontal = 10.dp),
            color = Color(0xffCCA730),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                progress = {progress},
                color = Color(0xff735C00),
                trackColor = Color(0xffEFEFED),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp),
                strokeCap = StrokeCap.Round,
                gapSize = 0.dp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${stats.currentExp} / ${CultivationSystem.getCurrentExpNeeded(stats)}",
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .border(1.dp, Color.Yellow, shape = RoundedCornerShape(10.dp))
                    .background(Color(0xffEFEFED))
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                        ) {
                        Icon(
                            painter = painterResource(id = R.drawable.energy),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )

                        Spacer(Modifier.width(10.dp))

                        Text(
                            text = "Linh Lực",
                            fontSize = 20.sp,
                            color = Color(0xff005A2B),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "${stats.linhLuc}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .border(1.dp, Color.Yellow, shape = RoundedCornerShape(10.dp))
                    .background(Color(0xffEFEFED))
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.shared_vision),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )

                        Spacer(Modifier.width(10.dp))

                        Text(
                            text = "Thần Thức",
                            fontSize = 20.sp,
                            color = Color(0xff005A2B),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "${stats.thanThuc}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
