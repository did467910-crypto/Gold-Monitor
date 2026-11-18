package bg.goldmonitor.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Gold = Color(0xFFD9A928)
private val Bg = Color(0xFF0B1118)
private val Panel = Color(0xFF151F2A)
private val Muted = Color(0xFF9AA7B4)
private val White = Color(0xFFF4F6F8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { GoldMonitorApp() }
    }
}

@Composable
private fun GoldMonitorApp() {
    var tab by remember { mutableStateOf("Обзор") }
    MaterialTheme(colorScheme = darkColorScheme(primary=Gold, background=Bg, surface=Panel, onBackground=White, onSurface=White)) {
        Scaffold(containerColor=Bg, bottomBar={
            NavigationBar(containerColor=Panel) {
                listOf("Обзор","Прогнози","Точност","Източници").forEach { item ->
                    NavigationBarItem(selected=tab==item, onClick={tab=item},
                        icon={Text(when(item){"Обзор"->"◈";"Прогнози"->"↗";"Точност"->"◎";else->"▤"}, color=if(tab==item) Gold else Muted)},
                        label={Text(item,fontSize=10.sp)})
                }
            }
        }) { padding ->
            LazyColumn(Modifier.fillMaxSize().padding(padding), contentPadding=PaddingValues(18.dp), verticalArrangement=Arrangement.spacedBy(14.dp)) {
                item {
                    Column {
                        Text("GOLD MONITOR", color=Gold, fontSize=22.sp, fontWeight=FontWeight.ExtraBold)
                        Text("ЗЛАТО · ГЛОБАЛНИ ПАЗАРИ · EUR", color=Muted, fontSize=10.sp)
                        Spacer(Modifier.height(10.dp))
                        Text("DEMO BUILD", color=Gold, fontSize=10.sp, fontWeight=FontWeight.Bold)
                    }
                }
                when(tab) {
                    "Обзор" -> {
                        item { CardBlock("ЗЛАТО · XAU / EUR", "— € за тройунция\n— € за грам\n— € за килограм", "Няма свързана пазарна котировка. Няма показани измислени цени.") }
                        item { CardBlock("ПРОГНОЗИ", "3 дни: —\n7 дни: —\n30 дни: —", "Прогнозите ще се появят след интеграция и проверка на данните.") }
                    }
                    "Прогнози" -> {
                        item { CardBlock("3 календарни дни", "Посока: — · Диапазон: —", "Първоначалната и коригираната прогноза ще се пазят отделно.") }
                        item { CardBlock("7 календарни дни", "Посока: — · Диапазон: —", "Оценка след достигане на целевата дата.") }
                        item { CardBlock("30 календарни дни", "Посока: — · Диапазон: —", "Без прогнозни стойности преди свързване с модела.") }
                    }
                    "Точност" -> {
                        item { CardBlock("Историческа точност", "MAE: —\nRMSE: —\nТочност на посоката: —", "Метриките ще се изчисляват само от приключили прогнози и реални цени.") }
                        item { CardBlock("Сравнение на моделите", "Първоначална срещу коригирана", "Архивът на първоначалната прогноза остава непроменен.") }
                    }
                    else -> {
                        item { CardBlock("Stooq / Yahoo Finance", "Дневни XAUUSD данни", "Индикативна серия, не LBMA fixing или COMEX settlement.") }
                        item { CardBlock("Европейска централна банка", "Референтен EUR/USD курс", "Работни дни; не е intraday котировка.") }
                        item { CardBlock("World Gold Council", "Пазарни анализи", "Източник за контекст.") }
                    }
                }
                item { Text("GOLD MONITOR v1.0 · Интерфейсна версия", color=Muted, fontSize=10.sp) }
            }
        }
    }
}

@Composable
private fun CardBlock(title:String, body:String, note:String) {
    Surface(color=Panel, shape=RoundedCornerShape(18.dp)) {
        Column(Modifier.fillMaxWidth().padding(17.dp)) {
            Text(title, color=Gold, fontWeight=FontWeight.Bold, fontSize=14.sp)
            Spacer(Modifier.height(9.dp))
            Text(body, color=White, fontSize=18.sp, lineHeight=27.sp)
            Spacer(Modifier.height(8.dp))
            Text(note, color=Muted, fontSize=11.sp, lineHeight=16.sp)
        }
    }
}
