package com.example.evy.ocs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProductsActivity extends Activity {

    ListView list;
    String[] itemname ={
            "2018 Volvo S90",
            "2018 Jaguar XF",
            "2018 Lincoln Continental\n",
            "2018 Maserati Ghibli",
            "2018 Genesis G90",
            "2017 Lexus LS 460",
            "2018 Mercedes-Benz S-Class",
            "2018 Audi A7"
    };

    Integer[] imgid={
            R.drawable.volvos90,
            R.drawable.jaguarxf,
            R.drawable.lincolncontinental,
            R.drawable.maseratighibli,
            R.drawable.genesisg90,
            R.drawable.lexusls460,
            R.drawable.mercedesbenzsclass,
            R.drawable.audia7,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
