package com.example.metro.Avtivity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.drm.ProcessedData
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
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
    private val station = arrayOf(
        Stations("المرج الجديدة", 30.164172735692873, 31.338450144767485),
        Stations("المرج", 30.157480208340775, 31.336619953988272),
        Stations("عزبة النخل", 30.139503542162668, 31.324443653987665),
        Stations("عين شمس", 30.131248374291626, 31.31895182145575),
        Stations("المطرية", 30.1215775562348, 31.313743753986984),
        Stations("حلمية الزيتون", 30.1138692198755, 31.313855205196177),
        Stations("حدائق الزيتون", 30.108556212446903, 31.31031072995415),
        Stations("سراي القبة", 30.10060986125597, 31.30507717534575),
        Stations("حمامات القبة", 30.09145916402009, 31.298921925150385),
        Stations("كوبري القبة", 30.089864120369374, 31.29427485251411),
        Stations("منشية الصدر", 30.08223583607714, 31.28751303864376),
        Stations("الدمرداش" , 30.07760368459885, 31.277800996314518),
        Stations("غمرة", 30.06927966891068, 31.264584609807944),
        Stations("الشهداء", 30.06134066046398, 31.245996952136622),
        Stations("أحمد عرابي", 30.05690205572991, 31.2421185693264),
        Stations("جمال عبد الناصر", 30.053490505252686, 31.23886160524472 ),
        Stations("أنور السادات", 30.044377544671992, 31.23440243864253 ),
        Stations("سعد زغلول", 30.037293337890496, 31.238426369325797 ),
        Stations("سيدة زينب",  30.02953776954474, 31.235387880970915),
        Stations("الملك الصالح",  30.017878090939462, 31.231060967476942),
        Stations("مارجرجس",  30.006358919055284, 31.229553694463828),
        Stations("الزهراء",29.995686095820805, 31.23118752514699),
        Stations("دار السلام",29.98232618205956, 31.24228258466549),
        Stations("حدائق المعادي",29.970475679905103, 31.250362246686457),
        Stations("المعادي",29.962071658345927, 31.257722013766138),
        Stations("ثكنات المعادي",29.953555782324297, 31.26294360980377),
        Stations("طرة البلد",29.94696779610753, 31.272979996309708),
        Stations("كوتسيكا", 29.936519302020102, 31.28185278281576),
        Stations("طرة الأسمنت", 29.92616034052604, 31.28752293863823),
        Stations("المعصرة", 29.906320180562815, 31.299494338637462),
        Stations("حدائق حلوان", 29.897310237588346, 31.303938300282685),
        Stations("وادي حوف", 29.87930259063996, 31.313599303978332),
        Stations("جامعة حلوان", 29.869683965818957, 31.320088353977816),
        Stations("عين حلوان",29.862804464728345, 31.324875396306805),
        Stations("حلوان", 29.849646809846522, 31.33406909711005),
        Stations("المنيب", 29.982859144474993, 31.2126282205509),
        Stations("ساقية مكي", 29.99574465502056, 31.208688182817887),
        Stations("ام المصريين", 30.005876856652552, 31.208098738640988),
        Stations("الجيزة", 30.010942113485196, 31.207254225934772),
        Stations("فيصل", 30.01730821304806, 31.203983525147795),
        Stations("جامعة القاهرة", 30.023262385058064, 31.211311266103905),
        Stations("البحوث", 30.036039841180813, 31.20017012514852),
        Stations("الدقي", 30.038911399119115, 31.212158868053763),
        Stations("اوبرا",30.042152000032797, 31.22500085398409),
        Stations("محمد نجيب", 30.04549965713415, 31.244182976996694),
        Stations("العتبة", 30.0591710616566, 31.258665552714607),
        Stations("مسرة", 30.0710466403998, 31.24511335583321),
        Stations("رود الفرج", 30.103749860516523, 31.18448622850022),
        Stations("سانت تريزا", 30.088179671410828, 31.245471238643933),
        Stations("الخلفاوي", 30.100311678164733, 31.245056700143653),
        Stations("المظلات", 30.104387753200694, 31.245641396315428),
        Stations("كلية الزراعة", 30.113942455671886, 31.24865916748033),
        Stations("شبرا الخيمة", 30.124847679586587, 31.243141117217267),
        Stations("عدلي منصور", 30.162153247623404, 31.42298481637837),
        Stations("الهايكستب",30.145345393616847, 31.404407727414608),
        Stations("عمر بن الخطاب", 30.14063018793476, 31.394333867481244),
        Stations("قباء", 30.13504966571368, 31.38375572515202),
        Stations("هشام بركات", 30.131086791334248, 31.372968182822607),
        Stations("النزهة",30.128188722316402, 31.360173596316276),
        Stations( "نادي الشمس", 30.12566956817781, 31.348900453987152),
        Stations("ألف مسكن", 30.11921714086943, 31.34017626748042),
        Stations("هيلوبوليس", 30.110176119129754, 31.33847015861617),
        Stations("هارون", 30.101593142977435, 31.332959225429157),
        Stations("الأهرام", 30.091929480114224, 31.326283509808633),
        Stations("كلية البنات", 30.083644838916275, 31.328758234947617),
        Stations("الاستاد", 30.07398700896258, 31.317101998908335),
        Stations("أرض المعارض", 30.073507658197432, 31.300958538643414),
        Stations("العباسية", 30.072207832485418, 31.283374999727084),
        Stations("عبدو باشا", 30.064994563353856, 31.27474999972708),
        Stations("باب الشعرية", 30.054385420268495, 31.255855538642773),
        Stations("جمال عبد الناصر", 30.053722849019938, 31.238732996313626),
        Stations("ماسبيرو", 30.055953297574977, 31.232144811655505),
        Stations("صفاء حجازي", 30.062656263681983, 31.22260052357984),
        Stations("كيت كات", 30.066721943701488, 31.213018009807875)
        )

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
                val routs = (rout1 + rout2).joinToString(", ")



                //textView.text=("عدد المحطات= $couunt محطة \n مدة الرحلة = $timeOfTrip دقيقة \n سعر التذكرة: $ticketPrice جنية \n  يجب الذهاب من الخط الاول من  $rout1 ---->الي الخط التاني من $rout2 \n وجهة الوصول = ${line2.last()} \n")
                textView.text = "1-Number of Stations is $couunt\n"+
                        "2-Time of trip is $timeOfTrip\n"+
                        "3-Price of your ticket is $ticketPrice\n"+
                        "4-Your Stations are \n${routs}"


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
        }

        YoYo.with(Techniques.SlideInUp).duration(1000).playOn(textView)

    }



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

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        b.onActivityResult(requestCode, resultCode, data) // ADD THIS LINE INSIDE onActivityResult
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        b.onRequestPermissionsResult(requestCode, permissions, grantResults) // ADD THIS LINE INSIDE onRequestPermissionResult
    }


    // get closer station
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

