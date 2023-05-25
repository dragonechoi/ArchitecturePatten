package com.cys.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.cys.mvvm.R
import com.cys.mvvm.databinding.ActivityMainBinding
import com.cys.mvvm.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    
    //3. MVVM [Model - View - ViewModel] : view와 model의 데이터를 연결해 (data binding) 놓아서 model 데이터가 변경될때 별도의 처리코드 없이 view 가 자동갱신되는 특징
    // 1) model - 다른 패턴의 model 과 같음 [Item , ItemModel]
    // 2) view - 사용자가 볼화면 , 클릭 이벤트를 처리하여 ViewModel 에게 model 제어 요청 [activity_main.xml , MainActivity.kt , Fragment....]
    // 3) viewmodel - 뷰와 모델을 연결하는 역할 , view 가 연결(binding) 한 데이터를 제어하도록 요청하는 코드가 있는 클래스

    //** View 는 ViewModel 을 참조하고, ViewModel 은 Model을 참조하고 있음.

    //MVVM 을 위해서는 [dataBinding : 데이터 바인딩 ] 기술을 이용하여 개발 하는것이 일반적임.
    // 데이터 바인딩은 뷰 바인딩과 다르게 xml 파일에  root 요소가 <layOut>  이어야 바인딩 클래스가 만들어짐.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //# view 역할
        //레이아웃 xml과 연결하는 바인딩 클래스 [activity_main.xml --> ActivityMainBinding]
        // data binding 기능으로 엑티비티에 setContentView()를 실행
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //# 뷰 모델 객체 생성하여 레이아웃 변수에 대입
        binding.vm= MyViewModel(this)


    }
}

//## - MVVM 장점
//1.MVP 처럼 view presenter가 1:1대응되어 있지 않아서 화면이 많아져도 ViewModel은 재사용 가능함. 결국 전체 파일 개수가 줄어듦
//2. 사용자 이벤트를 ViewModel이 모두 하고 있기 떄문에 화면이 변경 되어도 이벤트 처리에 대한 중복 코드가 필요 없음
//3.view는 ViewModel 을 참조하지만 viewModel 은 view 를 참조하지 않기에 view가 변경되어도 뷰모델에 영향이 없음
//4.Activity나 Fragment의 코드가 가장 간결함

//## MVVM 단점
//1.MVVM의 설계 학습을 어려워함.
//2.View 처리가 많아지면 ViewModel의 코드가 많아져서 결국 비대해 짐...