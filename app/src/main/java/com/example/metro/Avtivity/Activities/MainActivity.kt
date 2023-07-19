package com.example.metro.Avtivity.Activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.metro.Avtivity.Activities.Stations.Companion.station
import com.example.metro.Avtivity.DataBase.Metro
import com.example.metro.Avtivity.DataBase.MetroDatabase
import com.example.metro.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import mumayank.com.airlocationlibrary.AirLocation
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


class MainActivity : AppCompatActivity(), AirLocation.Callback {
    private lateinit var textView: TextView
    private lateinit var titleTv: TextView
    private lateinit var btn_sh:Button
    private lateinit var mylocation : MaterialButton
    private lateinit var imageLogo: ImageView
    private lateinit var tilStart: TextInputLayout
    private lateinit var tilEnd: TextInputLayout
    lateinit var startDropDown: AutoCompleteTextView
    lateinit var endDropDown: AutoCompleteTextView
    lateinit var startAdapter: ArrayAdapter<String>
    lateinit var endAdapter: ArrayAdapter<String>
    lateinit var bottomNavigation: BottomNavigationView
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
        "ام المصريين",
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
        "الخلفاوي",
        "المظلات",
        "كلية الزراعة",
        "شبرا الخيمة"
    )
    val line3 = mutableListOf(
        "عدلي منصور",
        "الهايكستب",
        "عمر بن الخطاب",
        "قباء",
        "هشام بركات",
        "النزهة",
        "نادي الشمس",
        "ألف مسكن",
        "هيلوبوليس",
        "هارون",
        "الأهرام",
        "كلية البنات",
        "الاستاد",
        "أرض المعارض",
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
    lateinit var b:AirLocation


    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    @SuppressLint("MissingInflatedId", "CommitPrefEdits", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fvb()
        adapter()
        yoyo()
        startDropDown.setOnItemClickListener { parent, view, position, id ->
            cal()
        }
        endDropDown.setOnItemClickListener { parent, view, position, id ->
            cal()
        }
        bottomNavigation.setOnNavigationItemSelectedListener {it ->
            when (it.itemId) {
                R.id.map -> Intent(this, MapActivity::class.java).also { startActivity(it) }
                R.id.one -> Intent(this, LineOneActivity::class.java).also { startActivity(it) }
                R.id.Two -> Intent(this, LineTwoActivity::class.java).also { startActivity(it) }
                R.id.Three -> Intent(this, LineThreeActivity::class.java).also { startActivity(it) }
                R.id.history -> Intent(this,HistoryActivity::class.java).also { startActivity(it) }
            }
            true
        }

    }

    private fun yoyo(){
        YoYo.with(Techniques.FadeIn).duration(3000).playOn(imageLogo)
        YoYo.with(Techniques.FadeIn).duration(3000).playOn(titleTv)
        YoYo.with(Techniques.FadeIn).duration(3000).playOn(btn_sh)
        YoYo.with(Techniques.FadeIn).duration(4000).playOn(tilStart)
        YoYo.with(Techniques.FadeIn).duration(4000).playOn(tilEnd)
        YoYo.with(Techniques.FadeIn).duration(4000).playOn(mylocation)
    }
    fun switch(view: View){
        val temp = startDropDown.text.toString()
        startDropDown.setText(endDropDown.text.toString(),false)
        endDropDown.setText(temp,false)
        cal()
        YoYo.with(Techniques.SlideInUp).duration(1000).playOn(textView)
    }
    private fun adapter(){
        //Adapter
        startAdapter = ArrayAdapter(this, R.layout.drop_down_list, line1 + line2 + line3)
        endAdapter = ArrayAdapter(this, R.layout.drop_down_list, line1 + line2 + line3)
        startDropDown.setAdapter(startAdapter)
        endDropDown.setAdapter(endAdapter)

    }
    private fun fvb(){
        startDropDown = findViewById(R.id.startStation)
        endDropDown = findViewById(R.id.endStation)
        textView = findViewById(R.id.textview)
        bottomNavigation = findViewById(R.id.bottomN)
        btn_sh = findViewById(R.id.b2)
        mylocation = findViewById(R.id.myLocationButton)
        imageLogo = findViewById(R.id.textTitle)
        titleTv = findViewById(R.id.name)
        btn_sh = findViewById(R.id.b2)
        tilStart = findViewById(R.id.startLayout)
        tilEnd = findViewById(R.id.endStationLayout)
    }
    @SuppressLint("SetTextI18n")
    private fun cal() {
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
            val count = abs(indexEnd - indexFirst)
            val indexFirstx = line2.indexOf("أنور السادات")
            val indexEndx = line2.indexOf(endStation)
            val countx = abs(indexEndx - indexFirstx)
            val couunt = count+countx

            val indexFirst2 = line1.indexOf(statStation)
            val indexEnd2 = line1.indexOf("الشهداء")
            val count2 = abs(indexEnd2 - indexFirst2)
            val indexFirstx2 = line2.indexOf("الشهداء")
            val indexEndx2 = line2.indexOf(endStation)
            val countx2 = abs(indexEndx - indexFirstx)
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
                val routs = (rout1 + rout2).joinToString(",")



                //textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = $timeOfTrip دقيقة \n سعر التذكرة: $ticketPrice جنية \n  يجب الذهاب من الخط الاول من  $rout1 ---->الي الخط التاني من $rout2 \n وجهة الوصول = ${line2.last()} \n")
                textView.text = "1-Number of Stations is $couunt\n"+
                        "2-Time of trip is $timeOfTrip\n"+
                        "3-Price of your ticket is $ticketPrice\n"+
                        "4-Your Stations are \n${routs}"


                val dialog = AlertDialog.Builder(this)
                    .setTitle("Save trip")
                    .setMessage("Do you want save your trip?")
                    .setPositiveButton("Yes"){ _,_ ->
                        val data = Metro(couunt,timeOfTrip,ticketPrice, routs )
                        val save = MetroDatabase.getDatabase(this).metroDao().save(data)
                        if (save != null){
                            Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
                        }else Toast.makeText(this,"Empty Data", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("No"){_,_ ->
                        Toast.makeText(this,"Data not saved",Toast.LENGTH_SHORT).show()
                    }.create()
                dialog.show()




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
                val routs2 = (rout1 + rout2).joinToString(", ")



                //  textView.text=("عدد المحطات= $couunt2  محطة \n مدة الرحلة = ${couunt2 * 2} دقيقة \n سعر التذكرة: $ticketPrice  جنية \n \"يجب الذهاب من الخط الاول من  $rout1 ---->الي الخط التاني من $rout2 \n")
                textView.text = "1-Number of Stations is $couunt\n"+
                        "2-Time of trip is $timeOfTrip2\n"+
                        "3-Price of your ticket is $ticketPrice\n"+
                        "4-Your Stations are \n${routs2}"


                val dialog = AlertDialog.Builder(this)
                    .setTitle("Save trip")
                    .setMessage("Do you want save your trip?")
                    .setPositiveButton("Yes"){ _,_ ->
                        val data = Metro(couunt,timeOfTrip2,ticketPrice,routs2 )
                        val save = MetroDatabase.getDatabase(this).metroDao().save(data)
                        if (save != null){
                            Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
                        }else Toast.makeText(this,"Empty Data", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("No"){_,_ ->
                        Toast.makeText(this,"Data not saved",Toast.LENGTH_SHORT).show()
                    }.create()
                dialog.show()


            }

        }
        //_______________________________________________________________
        if (statStation in line1 && endStation in line3) {
            val indexFirst = line1.indexOf(statStation)
            val indexEnd = line1.indexOf("جمال عبد الناصر")
            val count = abs(indexEnd - indexFirst)
            val indexFirstx = line3.indexOf("جمال عبد الناصر")
            val indexEndx = line3.indexOf(endStation)
            val countx = abs(indexEndx - indexFirstx)
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

            val routs = (rout1+rout3).joinToString(", ")


            //  textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n يجب الذهاب من الخط الاول من  $rout1 ---->الي الخط الثالث من $rout3 \n اتجاه الوصول  = ${line3.last()} \n")
            textView.text = "1-Number of Stations is $couunt\n"+
                    "2-Time of trip is $timeofTrip\n"+
                    "3-Price of your ticket is $ticketPrice\n"+
                    "4-Your Stations are \n${routs}"



            val dialog = AlertDialog.Builder(this)
                .setTitle("Save trip")
                .setMessage("Do you want save your trip?")
                .setPositiveButton("Yes"){ _,_ ->
                    val data = Metro(couunt,timeofTrip,ticketPrice,routs )
                    val save = MetroDatabase.getDatabase(this).metroDao().save(data)
                    if (save != null){
                        Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
                    }else Toast.makeText(this,"Empty Data", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){_,_ ->
                    Toast.makeText(this,"Data not saved",Toast.LENGTH_SHORT).show()
                }.create()
            dialog.show()
        }
        //______________________________________________________________
        if (statStation in line2 && endStation in line1) {
            val indexFirst = line2.indexOf(statStation)
            val indexEnd = line2.indexOf("أنور السادات")
            val count = abs(indexEnd - indexFirst)
            val indexFirstx = line1.indexOf("أنور السادات")
            val indexEndx = line1.indexOf(endStation)
            val countx = abs(indexEndx - indexFirstx)
            val couunt = count + countx

            val timeOfTrip = couunt*2



            val indexFirst2 = line2.indexOf(statStation)
            val indexEnd2 = line2.indexOf("الشهداء")
            val count2 = abs(indexEnd2 - indexFirst2)
            val indexFirstx2 = line1.indexOf("الشهداء")
            val indexEndx2 = line1.indexOf(endStation)
            val countx2 = abs(indexEndx2 - indexFirstx2)
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
                val routs = (rout2 + rout1).joinToString(", ")


                // textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n  يجب الذهاب من الخط الثاني من $rout2 ---->الي الخط الاول من $rout1 \n وجهة الوصول = ${line1.last()} \n")
                textView.text = "1-Number of Stations is $couunt\n"+
                        "2-Time of trip is $timeOfTrip\n"+
                        "3-Price of your ticket is $ticketPrice\n"+
                        "4-Your Stations are \n${routs}"


                val dialog = AlertDialog.Builder(this)
                    .setTitle("Save trip")
                    .setMessage("Do you want save your trip?")
                    .setPositiveButton("Yes"){ _,_ ->
                        val data = Metro(couunt,timeOfTrip,ticketPrice,routs )
                        val save = MetroDatabase.getDatabase(this).metroDao().save(data)
                        if (save != null){
                            Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
                        }else Toast.makeText(this,"Empty Data", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("No"){_,_ ->
                        Toast.makeText(this,"Data not saved",Toast.LENGTH_SHORT).show()
                    }.create()
                dialog.show()


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

                val routs2 = (rout2 + rout1).joinToString(", ")


                //   textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n  يجب الذهاب من الخط الثاني من $rout2 ---->الي الخط الاول من $rout1 \n وجهة الوصول = ${line1.last()} \n")

                textView.text = "1-Number of Stations is $couunt2\n"+
                        "2-Time of trip is $timeOfTrip2\n"+
                        "3-Price of your ticket is $ticketPrice\n"+
                        "4-Your Stations are \n${routs2}"


                val dialog = AlertDialog.Builder(this)
                    .setTitle("Save trip")
                    .setMessage("Do you want save your trip?")
                    .setPositiveButton("Yes"){ _,_ ->
                        val data = Metro(couunt2,timeOfTrip2,ticketPrice ,routs2)
                        val save = MetroDatabase.getDatabase(this).metroDao().save(data)
                        if (save != null){
                            Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
                        }else Toast.makeText(this,"Empty Data", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("No"){_,_ ->
                        Toast.makeText(this,"Data not saved",Toast.LENGTH_SHORT).show()
                    }.create()
                dialog.show()
            }


        }
        //_________________________________________________________________
        if (statStation in line3 && endStation in line1) {
            val indexFirst = line3.indexOf(statStation)
            val indexEnd = line3.indexOf("جمال عبد الناصر")
            val count = abs(indexEnd - indexFirst)
            val indexFirstx = line1.indexOf("جمال عبد الناصر")
            val indexEndx = line1.indexOf(endStation)
            val countx = abs(indexEndx - indexFirstx)
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

            val routss= (rout3 + rout1).joinToString(", ")


            // textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n يجب الذهاب من الخط الثالث من  $rout3 ---->الي الخط الاول من $rout1 \n اتجاه الوصول  = ${line1.last()} \n")
            textView.text = "1-Number of Stations is $couunt\n"+
                    "2-Time of trip is $timeOf\n"+
                    "3-Price of your ticket is $ticketPrice\n"+
                    "4-Your Stations are \n${routss}"


            val dialog = AlertDialog.Builder(this)
                .setTitle("Save trip")
                .setMessage("Do you want save your trip?")
                .setPositiveButton("Yes"){ _,_ ->
                    val data = Metro(couunt,timeOf,ticketPrice,routss )
                    val save = MetroDatabase.getDatabase(this).metroDao().save(data)
                    if (save != null){
                        Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
                    }else Toast.makeText(this,"Empty Data", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){_,_ ->
                    Toast.makeText(this,"Data not saved",Toast.LENGTH_SHORT).show()
                }.create()
            dialog.show()
        }
        //_________________________________________________________________
        if (statStation in line2 && endStation in line3) {
            val indexFirst = line2.indexOf(statStation)
            val indexEnd = line2.indexOf("العتبة")
            val count = abs(indexEnd - indexFirst)
            val indexFirstx = line3.indexOf("العتبة")
            val indexEndx = line3.indexOf(endStation)
            val countx = abs(indexEndx - indexFirstx)
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

            val rout23= (rout2+rout3).joinToString(", ")


            // textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n يجب الذهاب من الخط الثاني من $rout2 الي الخط الثالث من$rout3 \n اتجاه الوصول = ${line3.last()} \n")
            textView.text = "1-Number of Stations is $couunt\n"+
                    "2-Time of trip is $timeOfTrip\n"+
                    "3-Price of your ticket is $ticketPrice\n"+
                    "4-Your Stations are \n${rout23}"


            val dialog = AlertDialog.Builder(this)
                .setTitle("Save trip")
                .setMessage("Do you want save your trip?")
                .setPositiveButton("Yes"){ _,_ ->
                    val data = Metro(couunt,timeOfTrip,ticketPrice,rout23 )
                    val save = MetroDatabase.getDatabase(this).metroDao().save(data)
                    if (save != null){
                        Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
                    }else Toast.makeText(this,"Empty Data", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){_,_ ->
                    Toast.makeText(this,"Data not saved",Toast.LENGTH_SHORT).show()
                }.create()
            dialog.show()
        }
        //____________________________________________________________
        if (statStation in line3 && endStation in line2) {
            val indexFirst = line3.indexOf(statStation)
            val indexEnd = line3.indexOf("العتبة")
            val count = abs(indexEnd - indexFirst)
            val indexFirstx = line2.indexOf("العتبة")
            val indexEndx = line2.indexOf(endStation)
            val countx = abs(indexEndx - indexFirstx)
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
            val rout32= (rout3 + rout2).joinToString(", ")


            //  textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = ${couunt * 2} دقيقة \n سعر التذكرة: $ticketPrice جنية \n يجب الذهاب من الخط الثالث من $rout3 الي الخط الثاني من$rout2 \n اتجاه الوصول = ${line2.last()} \n")
            textView.text = "1-Number of Stations is $couunt\n"+
                    "2-Time of trip is $time\n"+
                    "3-Price of your ticket is $ticketPrice\n"+
                    "4-Your Stations are \n${rout32}"


            val dialog = AlertDialog.Builder(this)
                .setTitle("Save trip")
                .setMessage("Do you want save your trip?")
                .setPositiveButton("Yes"){ _,_ ->
                    val data = Metro(couunt,time,ticketPrice,rout32 )
                    val save = MetroDatabase.getDatabase(this).metroDao().save(data)
                    if (save != null){
                        Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
                    }else Toast.makeText(this,"Empty Data", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){_,_ ->
                    Toast.makeText(this,"Data not saved",Toast.LENGTH_SHORT).show()
                }.create()
            dialog.show()
        }
        //_________________________________________________________
        if (indexFirst == -1 || indexEnd == -1) {
        }else if (statStation == endStation){
            Snackbar.make(titleTv, "You are in the same station", Snackbar.LENGTH_SHORT).show()
            textView.text = ""
        } else {
            val count = abs(indexEnd - indexFirst)
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
            textView.text = "1-Number of Stations is $count\n"+
                    "2-Time of trip is $timeForTrip\n"+
                    "3-Price of your ticket is $ticketPrice\n"+
                    "4-Your Stations are \n${stations.joinToString(",")}"


            val dialog = AlertDialog.Builder(this)
                .setTitle("Save trip")
                .setMessage("Do you want save your trip?")
                .setPositiveButton("Yes"){ _,_ ->
                    val data = Metro(count,timeForTrip,ticketPrice, stations.joinToString(",").toString() )
                    val save = MetroDatabase.getDatabase(this).metroDao().save(data)
                    if (save != null){
                        Toast.makeText(this,"Data saved in history", Toast.LENGTH_SHORT).show()
                    }else Toast.makeText(this,"Empty Data", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){_,_ ->
                    Toast.makeText(this,"Data not saved",Toast.LENGTH_SHORT).show()
                }.create()
            dialog.show()


        }
        YoYo.with(Techniques.SlideInUp).duration(1000).playOn(textView)
    }



    //Location....
    fun myLocation(view: View){
        b = AirLocation(this, this,false,0,"")
        b.start()
    }

    override fun onFailure(locationFailedEnum: AirLocation.LocationFailedEnum) {
        Snackbar.make(textView, "Check your permissions", Snackbar.LENGTH_SHORT).show()
    }

    override fun onSuccess(locations: ArrayList<Location>) {
        mylocation.setOnClickListener {
            locations[0].accuracy
            val lat = locations[0].latitude
            val lon = locations[0].longitude

            val closestStation = findClosestMetroStation(lat, lon)
            closestStation?.let {
                val d = AlertDialog.Builder(this)
                    .setTitle("Open map")
                    .setMessage("Do you want to get station in map")
                    .setPositiveButton("Open"){_,_->
                        val g = Geocoder(this)
                        val address = g.getFromLocation(it.lat, it.long,1)
                        address?.get(0).let {
                            val i = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("geo:0,0?q= ${it?.getAddressLine(0)}")
                            )
                            startActivity(i)
                        }
                    }
                    .setNegativeButton("Close"){_,_ ->
                        startDropDown.setText(it.name)
                        cal()
                        Snackbar.make(textView , "You will found Start station in the top" , Snackbar.LENGTH_SHORT).show()
                    }.create()
                d.show()
            } ?: Snackbar.make(textView, "No metro stations found", Snackbar.LENGTH_SHORT).show()

        }
    }

    //Permissions....
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        b.onActivityResult(requestCode, resultCode, data) // ADD THIS LINE INSIDE onActivityResult
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        b.onRequestPermissionsResult(requestCode, permissions, grantResults) // ADD THIS LINE INSIDE onRequestPermissionResult
    }

    // Get Closer Station....
    private fun getDistance(latitude1: Double, longitude1: Double, latitude2: Double, longitude2: Double): Double {
        val earthRadius = 6371 // Radius of the earth in kilometers
        val dLat = Math.toRadians(latitude2 - latitude1)
        val dLon = Math.toRadians(longitude2 - longitude1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(latitude1)) * cos(Math.toRadians(latitude2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadius * c
    }

    private fun findClosestMetroStation(latitude: Double, longitude: Double): Stations? {
        var closestStation: Stations? = null
        var shortestDistance = Double.MAX_VALUE

        for (station in station) {
            val distance = getDistance(latitude, longitude, station.lat, station.long)
            if (distance < shortestDistance) {
                shortestDistance = distance
                closestStation = station
            }
        }
        return closestStation
    }

}

