package com.example.dicerollerkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dicerollerkotlin.ui.theme.DiceRollerKotlinTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerKotlinTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImages()
}

@Composable
fun DiceWithButtonAndImages(modifier: Modifier = Modifier) {
    var dice1Value by remember { mutableStateOf(1) }
    var dice2Value by remember { mutableStateOf(1) }
    var resultMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Zarları yan yana yerleştirmek için Row kullanıyoruz
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Zarlar arasına boşluk ekle
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Birinci zarın görseli
            Image(
                painter = painterResource(id = getDiceImage(dice1Value)),
                contentDescription = "Dice 1",
                modifier = Modifier.size(150.dp)
            )

            // İkinci zarın görseli
            Image(
                painter = painterResource(id = getDiceImage(dice2Value)),
                contentDescription = "Dice 2",
                modifier = Modifier.size(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Zarları atma butonu
        Button(onClick = {
            dice1Value = Random.nextInt(1, 7) // Birinci zarı at
            dice2Value = Random.nextInt(1, 7) // İkinci zarı at
            val total = dice1Value + dice2Value // Toplamı hesapla
            // Kazanma veya kaybetme durumunu belirle
            resultMessage = if (total > 7) "Kazandınız!" else "Kaybettiniz!"
        }) {
            Text(text = stringResource(R.string.roll)) // Butonun yazısı
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sonuç mesajı
        Text(text = resultMessage)
    }
}

// Zarın görselini döndüren fonksiyon
fun getDiceImage(value: Int): Int {
    return when (value) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1 // Varsayılan olarak 1 numaralı zar
    }
}
