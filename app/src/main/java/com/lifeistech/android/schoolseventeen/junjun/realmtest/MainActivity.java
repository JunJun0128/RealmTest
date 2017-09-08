package com.lifeistech.android.schoolseventeen.junjun.realmtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    Realm realm;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // xmlとの関連付け
        editText = (EditText)findViewById(R.id.editText);

        //realmの初期化
        realm = Realm.getDefaultInstance();
    }
    //Addボタンを押したときの処理
    public void add (View v){
        //トランザクション開始
        realm.beginTransaction();
        //インスタンスを生成
        User model = realm.createObject(User.class);

        //書き込みたいデータをインスタンスに入れる
        model.setAge(1);
        model.setName(editText.getText().toString());

        //トランザクション終了 (データを書き込む)
        realm.commitTransaction();

/*
        // データを挿入する
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm){
                TestDB u = realm.createObject(TestDB.class);
                u.setName("サンプル太郎");
                u.setAge(25);
            }
        });
 */


    }

    //Logボタンをおした時
    public void showLog(View v){
        //検索用のクエリ作成
        RealmQuery<User> query = realm.where(User.class);

//        query.equalTo("name", "test");
//        query.or().equalTo("id", 2);
//        query.or().equalTo("id", 3);

        //インスタンス生成し、その中にすべてのデータを入れる 今回なら全てのデータ
        RealmResults<User> results = query.findAll();

        //すべての値をログに出力
        for (User test:results){
            System.out.println(test.getName());
        }
    }

}
