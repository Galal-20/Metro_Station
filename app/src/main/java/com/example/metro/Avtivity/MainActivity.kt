package com.example.metro.Avtivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.metro.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var B1: Button
    lateinit var startDropDown: AutoCompleteTextView
    lateinit var endDropDown: AutoCompleteTextView

    val line1 = mutableListOf(
        "حلوان",
        "عين حلوان",
        "جامعة حلوان",
        "وادي حوف",
        "حدائق حلوان",
        "المعصرة",
        "طرة الأسمنت",
        "كوتسيكا",
        "طرة البلد",
        "ثكنات المعادي",
        "المعادي",
        "حدائق المعادي",
        "دار السلام",
        "الزهراء",
        "مارجرجس",
        "الملك الصالح",
        "سيدة زينب",
        "سعد زغلول",
        "أنور السادات",
        "جمال عبد الناصر",
        "أحمد عرابي",
        "الشهداء",
        "غمرة",
        "الدمرداش",
        "منشية الصدر",
        "كوبري القبة",
        "حمامات القبة",
        "سراي القبة",
        "حدائق الزيتون",
        "حلمية الزيتون",
        "المطرية",
        "عين شمس",
        "عزبة النخل",
        "المرج",
        "المرج الجديدة"
    )
    val line2 = mutableListOf(
        "المنيب",
        "ساقية مكي",
        "ضواحي الجيزة",
        "الجيزة",
        "فيصل",
        "جامعة القاهرة",
        "البحوث",
        "الدقي",
        "اوبرا",
        "أنور السادات",
        "محمد نجيب",
        "العتبة",
        "الشهداء",
        "مسرة",
        "رود الفرج",
        "سانت تريزا",
        "خلافاوي",
        "المظلات",
        "كلية الزراعة",
        "شبرا الخيمة"
    )
    val line3 = mutableListOf(
        "عدلي منصور",
        "الحي الايكستاب",
        "عمر بن الخطاب",
        "قبة",
        "هشام بركات",
        "النزهة",
        "نادي الشمس",
        "ألف مسكن",
        "مصر الجديدة",
        "هارون",
        "الأهرام",
        "كلية البنات",
        "الاستاد",
        "معارض القاهرة الدولية",
        "العباسية",
        "عبدو باشا",
        "الجيزة",
        "باب الشعرية",
        "العتبة",
        "جمال عبد الناصر",
        "ماسبيرو",
        "صفاء حجازي",
        "كيت كات"
    )

    lateinit var bottomNavigation: BottomNavigationView

    @SuppressLint("MissingInflatedId", "CommitPrefEdits", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        startDropDown = findViewById(R.id.startStation)
        endDropDown = findViewById(R.id.endStation)
        textView = findViewById(R.id.textview)
        B1 = findViewById(R.id.b1)
        bottomNavigation = findViewById(R.id.bottomN)


        val startAdapter = ArrayAdapter(this, R.layout.drop_down_list, line1 + line2 + line3)
        val endAdapter = ArrayAdapter(this, R.layout.drop_down_list, line1 + line2 + line3)
        startDropDown.setAdapter(startAdapter)
        endDropDown.setAdapter(endAdapter)




        B1.setOnClickListener {
            var statStation = startDropDown.text.toString()
            var endStation = endDropDown.text.toString()


            var indexFirst = line1.indexOf(statStation)
            var indexEnd = line1.indexOf(endStation)
            var rout = line1

            if (indexFirst == -1 || indexEnd == -1) {
                indexFirst = line2.indexOf(statStation)
                indexEnd = line2.indexOf(endStation)
                rout = line2
                if (indexFirst == -1 || indexEnd == -1) {
                    indexFirst = line3.indexOf(statStation)
                    indexEnd = line3.indexOf(endStation)
                    rout = line3
                }
            }
            if (statStation in line1 && endStation in line2) {
                val indexFirst = line1.indexOf(statStation)
                val indexEnd = line1.indexOf("أنور السادات")
                val count = Math.abs(indexEnd - indexFirst)
                val indexFirstx = line2.indexOf("أنور السادات")
                val indexEndx = line2.indexOf(endStation)
                val countx = Math.abs(indexEndx - indexFirstx)
                val couunt = count+countx

                val indexFirst2 = line1.indexOf(statStation)
                val indexEnd2 = line1.indexOf("الشهداء")
                val count2 = Math.abs(indexEnd2 - indexFirst2)
                val indexFirstx2 = line2.indexOf("الشهداء")
                val indexEndx2 = line2.indexOf(endStation)
                val countx2 = Math.abs(indexEndx - indexFirstx)
                val couunt2 = count2+countx2

                // time of trip:
                val timeOfTrip =  count*2
                val timeOfTrip2 =  couunt2*2

                if (couunt<=couunt2){
                    val ticketPrice = when (couunt) {
                        in 0..9 -> 5
                        in 10..16 -> 7
                        else -> 10
                    }

                    val rout1 = if (indexEnd > indexFirst) {
                        line1.subList(indexFirst, indexEnd + 1)
                    } else {
                        line1.subList(indexEnd, indexFirst + 1).reversed()
                    }
                    val rout2 = if (indexEndx > indexFirstx) {
                        line2.subList(indexFirstx, indexEndx + 1)
                    } else {
                        line2.subList(indexEndx, indexFirstx + 1).reversed()
                    }
                    val routs = rout1 + rout2

                    //textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = $timeOfTrip دقيقة \n سعر التذكرة: $ticketPrice جنية \n  يجب الذهاب من الخط الاول من  $rout1 ---->الي الخط التاني من $rout2 \n وجهة الوصول = ${line2.last()} \n")
                    textView.text = "Number of Stations is $couunt\n" +
                            "Time of trip is $timeOfTrip\n" +
                            "Price of your ticket is $ticketPrice\n" +
                            "Your Stations are $routs "


                }else{

                    val ticketPrice = when (couunt2) {
                        in 0..9 -> 5
                        in 10..16 -> 7
                        else -> 10
                    }
                    val rout1 = if (indexEnd2 > indexFirst2) {
                        line1.subList(indexFirst2, indexEnd2 + 1)
                    } else {
                        line1.subList(indexEnd2, indexFirst2 + 1).reversed()
                    }
                    val rout2 = if (indexEndx > indexFirstx) {
                        line2.subList(indexFirstx2, indexEndx2 + 1)
                    } else {
                        line2.subList(indexEndx2, indexFirstx2 + 1).reversed()
                    }
                    val routs2 = rout1 + rout2

                  //  textView.text=("عدد المحطات= $couunt2  محطة \n مدة الرحلة = ${couunt2 * 2} دقيقة \n سعر التذكرة: $ticketPrice  جنية \n \"يجب الذهاب من الخط الاول من  $rout1 ---->الي الخط التاني من $rout2 \n")
                    textView.text = "Number of Stations is $couunt\n" +
                            "Time of trip is $timeOfTrip2\n" +
                            "Price of your ticket is $ticketPrice\n" +
                            "Your Stations are $routs2 "

                }

            }
            //_______________________________________________________________
            if (statStation in line1 && endStation in line3) {
                val indexFirst = line1.indexOf(statStation)
                val indexEnd = line1.indexOf("جمال عبد الناصر")
                val count = Math.abs(indexEnd - indexFirst)
                val indexFirstx = line3.indexOf("جمال عبد الناصر")
                val indexEndx = line3.indexOf(endStation)
                val countx = Math.abs(indexEndx - indexFirstx)
                val couunt = count + countx

                val timeofTrip = couunt*2


                val ticketPrice = when (couunt) {
                    in 0..9 -> 5
                    in 10..16 -> 7
                    else -> 10
                }
                val rout1 = if (indexEnd > indexFirst) {
                    line1.subList(indexFirst, indexEnd + 1)
                } else {
                    line1.subList(indexEnd, indexFirst + 1).reversed()
                }
                val rout3 = if (indexEndx > indexFirstx) {
                    line3.subList(indexFirstx, indexEndx + 1)
                } else {
                    line3.subList(indexEndx, indexFirstx + 1).reversed()
                }

                val routs = rout1+rout3

              //  textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n يجب الذهاب من الخط الاول من  $rout1 ---->الي الخط الثالث من $rout3 \n اتجاه الوصول  = ${line3.last()} \n")
                textView.text = "Number of Stations is $couunt\n" +
                        "Time of trip is $timeofTrip\n" +
                        "Price of your ticket is $ticketPrice\n" +
                        "Your Stations are $routs "
            }
            //______________________________________________________________
            if (statStation in line2 && endStation in line1) {
                val indexFirst = line2.indexOf(statStation)
                val indexEnd = line2.indexOf("أنور السادات")
                val count = Math.abs(indexEnd - indexFirst)
                val indexFirstx = line1.indexOf("أنور السادات")
                val indexEndx = line1.indexOf(endStation)
                val countx = Math.abs(indexEndx - indexFirstx)
                val couunt = count + countx

                val timeOfTrip = couunt*2



                val indexFirst2 = line2.indexOf(statStation)
                val indexEnd2 = line2.indexOf("الشهداء")
                val count2 = Math.abs(indexEnd2 - indexFirst2)
                val indexFirstx2 = line1.indexOf("الشهداء")
                val indexEndx2 = line1.indexOf(endStation)
                val countx2 = Math.abs(indexEndx2 - indexFirstx2)
                val couunt2 = count2 + countx2

                val timeOfTrip2 = couunt2*2

                if (couunt<=couunt2){
                    val ticketPrice = when (couunt) {
                        in 0..9 -> 5
                        in 10..16 -> 7
                        else -> 10
                    }
                    val rout2 = if (indexEnd > indexFirst) {
                        line2.subList(indexFirst, indexEnd + 1)
                    } else {
                        line2.subList(indexEnd, indexFirst + 1).reversed()
                    }
                    val rout1 = if (indexEndx > indexFirstx) {
                        line1.subList(indexFirstx, indexEndx + 1)
                    } else {
                        line1.subList(indexEndx, indexFirstx + 1).reversed()
                    }
                    val routs = rout2 + rout1

                   // textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n  يجب الذهاب من الخط الثاني من $rout2 ---->الي الخط الاول من $rout1 \n وجهة الوصول = ${line1.last()} \n")
                    textView.text = "Number of Stations is $couunt\n" +
                            "Time of trip is $timeOfTrip\n" +
                            "Price of your ticket is $ticketPrice\n" +
                            "Your Stations are $routs "


                }else{

                    val ticketPrice = when (couunt2) {
                        in 0..9 -> 5
                        in 10..16 -> 7
                        else -> 10
                    }
                    val rout2 = if (indexEnd2 > indexFirst2) {
                        line2.subList(indexFirst2, indexEnd2 + 1)
                    } else {
                        line2.subList(indexEnd2, indexFirst2 + 1).reversed()
                    }
                    val rout1 = if (indexEndx2 > indexFirstx2) {
                        line1.subList(indexFirstx2, indexEndx2 + 1)
                    } else {
                        line1.subList(indexEndx2, indexFirstx2 + 1).reversed()
                    }

                    val routs2 = rout2 + rout1

                 //   textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n  يجب الذهاب من الخط الثاني من $rout2 ---->الي الخط الاول من $rout1 \n وجهة الوصول = ${line1.last()} \n")

                    textView.text = "Number of Stations is $couunt2\n" +
                            "Time of trip is $timeOfTrip2\n" +
                            "Price of your ticket is $ticketPrice\n" +
                            "Your Stations are $routs2 "
                }


            }
            //_________________________________________________________________
            if (statStation in line3 && endStation in line1) {
                val indexFirst = line3.indexOf(statStation)
                val indexEnd = line3.indexOf("جمال عبد الناصر")
                val count = Math.abs(indexEnd - indexFirst)
                val indexFirstx = line1.indexOf("جمال عبد الناصر")
                val indexEndx = line1.indexOf(endStation)
                val countx = Math.abs(indexEndx - indexFirstx)
                val couunt = count + countx

                val timeOf = couunt*2
                val ticketPrice = when (couunt) {
                    in 0..9 -> 5
                    in 10..16 -> 7
                    else -> 10
                }
                val rout3 = if (indexEnd > indexFirst) {
                    line3.subList(indexFirst, indexEnd + 1)
                } else {
                    line3.subList(indexEnd, indexFirst + 1).reversed()
                }
                val rout1 = if (indexEndx > indexFirstx) {
                    line1.subList(indexFirstx, indexEndx + 1)
                } else {
                    line1.subList(indexEndx, indexFirstx + 1).reversed()
                }

                val routss= rout3 + rout1

               // textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n يجب الذهاب من الخط الثالث من  $rout3 ---->الي الخط الاول من $rout1 \n اتجاه الوصول  = ${line1.last()} \n")
                textView.text = "Number of Stations is $couunt\n" +
                        "Time of trip is $timeOf\n" +
                        "Price of your ticket is $ticketPrice\n" +
                        "Your Stations are $routss "
            }
            //_________________________________________________________________
            if (statStation in line2 && endStation in line3) {
                val indexFirst = line2.indexOf(statStation)
                val indexEnd = line2.indexOf("العتبة")
                val count = Math.abs(indexEnd - indexFirst)
                val indexFirstx = line3.indexOf("العتبة")
                val indexEndx = line3.indexOf(endStation)
                val countx = Math.abs(indexEndx - indexFirstx)
                val couunt = count + countx

                val timeOfTrip = couunt*2

                val ticketPrice = when (couunt) {
                    in 0..9 -> 5
                    in 10..16 -> 7
                    else -> 10
                }
                val rout2 = if (indexEnd > indexFirst) {
                    line2.subList(indexFirst, indexEnd + 1)
                } else {
                    line2.subList(indexEnd, indexFirst + 1).reversed()
                }
                val rout3 = if (indexEndx > indexFirstx) {
                    line3.subList(indexFirstx, indexEndx + 1)
                } else {
                    line3.subList(indexEndx, indexFirstx + 1).reversed()
                }

                val rout23=rout2+rout3

               // textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n يجب الذهاب من الخط الثاني من $rout2 الي الخط الثالث من$rout3 \n اتجاه الوصول = ${line3.last()} \n")
                textView.text = "Number of Stations is $couunt\n" +
                        "Time of trip is $timeOfTrip\n" +
                        "Price of your ticket is $ticketPrice\n" +
                        "Your Stations are $rout23 "

            }
            //____________________________________________________________
            if (statStation in line3 && endStation in line2) {
                val indexFirst = line3.indexOf(statStation)
                val indexEnd = line3.indexOf("العتبة")
                val count = Math.abs(indexEnd - indexFirst)
                val indexFirstx = line2.indexOf("العتبة")
                val indexEndx = line2.indexOf(endStation)
                val countx = Math.abs(indexEndx - indexFirstx)
                val couunt = count + countx

                val time = couunt*2
                val ticketPrice = when (couunt) {
                    in 0..9 -> 5
                    in 10..16 -> 7
                    else -> 10
                }
                val rout3 = if (indexEnd > indexFirst) {
                    line3.subList(indexFirst, indexEnd + 1)
                } else {
                    line3.subList(indexEnd, indexFirst + 1).reversed()
                }
                val rout2 = if (indexEndx > indexFirstx) {
                    line2.subList(indexFirstx, indexEndx + 1)
                } else {
                    line2.subList(indexEndx, indexFirstx + 1).reversed()
                }
                val rout32= rout3 + rout2

              //  textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n يجب الذهاب من الخط الثالث من $rout3 الي الخط الثاني من$rout2 \n اتجاه الوصول = ${line2.last()} \n")
                textView.text = "Number of Stations is $couunt\n" +
                        "Time of trip is $time\n" +
                        "Price of your ticket is $ticketPrice\n" +
                        "Your Stations are $rout32 "
            }
            //_________________________________________________________
            if (indexFirst == -1 || indexEnd == -1) {
            }else if (statStation == endStation){
                Toast.makeText(this, "you are in the same station" , Toast.LENGTH_SHORT).show()
                textView.text = ""
            } else {
                val count = Math.abs(indexEnd - indexFirst)
                val timeForTrip = count*3

                val ticketPrice = when (count) {
                    in 0..9 -> 5
                    in 10..16 -> 7
                    else -> 10
                }

                val stations = if (indexEnd > indexFirst) {
                    rout.subList(indexFirst, indexEnd + 1)
                } else {
                    rout.subList(indexEnd, indexFirst + 1).reversed()
                }

              //  textView.text=("عدد المحطات : $count \n وقت الرحلة: ${count * 2} دقيقة \n سعر التذكرة: $ticketPrice جنيه \n المسار هو: $stations \n")
                textView.text = "Number of Stations is $count\n" +
                        "Time of trip is $timeForTrip\n" +
                        "Price of your ticket is $ticketPrice\n" +
                        "Your Stations are $stations "
            }
        }
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.map -> Intent(this, MapActivity::class.java).also { startActivity(it) }
                R.id.one -> Intent(this, LineOneActivity::class.java).also { startActivity(it) }
                R.id.Two -> Intent(this, LineTwoActivity::class.java).also { startActivity(it) }
                R.id.Three -> Intent(this, LineThreeActivity::class.java).also { startActivity(it) }
            }
            true
        }
        }
}
