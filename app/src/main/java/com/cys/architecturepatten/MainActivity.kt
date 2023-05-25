package com.cys.architecturepatten

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cys.architecturepatten.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    //    Architecture Pattern 그냥 작성하는 flat design
    //    1) 장점 : 구조가 간단하여 구현하기 쉽고 하나의 문서에 대부분의 기능코드와 UI코드가 있어서 전체 기능이 한눈에 들어옴.
    //    2)단점 : ACtivity, Fragment에 모든 기능코드가 있어서 규모가 커지면 파일안에 너무많은 코드가 있어서 비대해짐. 유지보수가 어려움
    //             똑같은 데이터를 제어하는 코드를 다른 화면에서 사용하게 되더라도 같은 코드를 또 작성해야 함. 재사용이 어렵다!!

    //  그래서 등장한 Architecture Pattern
    // 작성 코드의 역할에 따라 구분하여서 작성하는 방법을 규격화 한 패턴
    // 대표적인 패턴  : MVC , MVP , MVVM
    // 차례대로 실습...  새로운  모듈을 만들어서 실습 진행 및 비교

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //# 화면 작업을 하는 영역
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //# 클릭 이벤트 처리 
        binding.btnSave.setOnClickListener { saveBtn() }
        binding.btnLoad.setOnClickListener { loadBtn() }
    }

    // # 클릭이벤트를 위해 데이터 제어(저장/읽기/삭제/변경 : CRUD)작업을 하는 비니지느 로직 코드 기능 메소드 두개 [ex. 네트워크 통신,DB 작업 등]
    private fun saveBtn() {
        val pref: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        pref.edit().apply() {
            //프리퍼런스 작업
            putString("mam", binding.etName.text.toString())
            putString("email", binding.etEmail.text.toString())
            commit()
        }
    }

    private fun loadBtn() {
        val pref: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        var name: String? = pref.getString("mam", "")
        var email: String = pref.getString("email", "")!!

        binding.tv.text = "$name : $email"
    }
}