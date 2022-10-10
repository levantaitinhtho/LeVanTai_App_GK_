package com.example.app_gk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app_gk.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    ListView lv_fruit;
    ArrayList<Fruit> fruits;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


      lv_fruit =  findViewById(R.id.list_view);
        fruits= new ArrayList<>();

        fruits.add(new Fruit("Nước sinh tố chuối", "Nước sinh tố chuối là một loại sinh tố giàu chất chống oxi" +
                " đẹp da và chế biến được đa dạng các món sinh tố, thức uống,...",R.drawable.chuoi));
        fruits.add(new Fruit("Sinh tố cherry", "Sinh tố cherry là một loại một loại thức uống bổ dưỡng cho bà bầu" +
                " có công dụng như: tăng cường miễn dịch, nuôi dưỡng não bộ thai nhi " +
                "giảm mệt mỏi chu kì mang thai,...",R.drawable.nho));
        fruits.add(new Fruit("Sinh tố dâu tây", "Sinh tố dâu tây là một loại sinh tố khá phổ biển " +
                "giúp điều trị đường huyết, làm đẹp da ở phụ nữ, có khả năng chống ung thư" +
                " và những người dạ dày nhạy cảm thì không nên dùng",R.drawable.chuoi));
        fruits.add(new Fruit("Nước ép dưa hấu"," Nước ép dưa hấu là một thức uống cực kì ngon được mọi người ưa chuộng  " +
                "vào cái hè nóng bức, giúp giải tỏa cơ thể, tăng sức đề kháng, ....",R.drawable.daytay));
        fruits.add(new Fruit("Soda Bạc Hà", "Soda bạc hà là một trong những thức uống giải nhiệt nhanh nhất trong mùa hè," +
                " với giá thành vô cùng rẻ mà mọi người có thể đưa vào danh sách thích của bản thân",R.drawable.dua));

        adapter = new Adapter(ListViewActivity.this,R.layout.activity_item,fruits);
        lv_fruit.setAdapter(adapter);
        lv_fruit.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ListViewActivity.this);
                dlgAlert.setMessage("Are you sure you want to delete?");
                dlgAlert.setTitle("Delete fruit");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fruits.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                });
                adapter.notifyDataSetChanged();
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                return  true;
            }
        });
        lv_fruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fr = fruits.get(i);
                    doOpenMainActivity(fr.getTitle(),fr.getDescription(),fr.getImage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void doOpenMainActivity(String title, String des, int image)
    {
        Intent myIntent;
        myIntent = new Intent( this, DetailActivity.class);
        myIntent.putExtra("title",title);
        myIntent.putExtra("des",des);
        myIntent.putExtra("image",image);
        startActivity(myIntent);
    }
    }