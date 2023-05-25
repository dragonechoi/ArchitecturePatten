package com.cys.mvp.presenter

import com.cys.mvp.model.Item
import com.cys.mvp.model.ItemModel

//presenter 라면 가져야 할 기능을 기술한 인터페이스 를 구현 하여 실제 기능을 작성 하는 클래스
class MainPresenter :MainContract.Presenter {

    // presenter 는 view 와 model 을 연결해야 하기에 각각의 참조변수를 멤버로 보유
    var view:MainContract.View?=null //1.view 역할을 수행하는 클래스는 MainContract.view 인터페이슬를 구현하고 있기에 특정 Activity/Fragment 를 직접 자료형으로  참조하고있지 않음 , 약한 결합
    var model:ItemModel?=null        //2.model 역할을 수행하는 클래스 참조변수

    //presenter가 연결할 2개의 참조변수를 생성 및 전달 받는 메소드
    fun initial(view:MainContract.View){
        this.view
        model=ItemModel(view.getContext())
    }

    //view 의 save 버튼클릭 이벤트를 대신 처리해주는 기능 메소드
    override fun clickSave(name: String, email: String) {
       model?.saveData(name, email) //model 에게 저장요청
    }

    override fun clickLoad() {
//        model에게 data 요청
       var item:Item?= model?.loadData()

//        view에게 데이터 출력 요청
        item?.let {
            view?.showData(it)
        }

    }
}