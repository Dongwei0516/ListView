package com.example.dongwei.a2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private Button main_btn;
    private ListView listView;
    private List<Map<String,Object>> mData;
    Map<Integer,Boolean> checkItems = new HashMap<Integer, Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAdapter adapter = new MyAdapter(this);
        mData = getData();
        listView = (ListView)findViewById(R.id.MyListView);
        listView.setAdapter(adapter);

        main_btn = (Button)findViewById(R.id.main_btn);
        main_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String res = "";
                Map map = checkItems;
                ArrayList key = new ArrayList(map.keySet());

                for (int i = 0; i<key.size() ; i++){
                    if (i!=key.size() -1){
                        res += "{"+key.get(i)+"}"+",";
                    }else {
                        res += "{"+key.get(i)+"}";
                    }
                }
                Toast toast = Toast.makeText(MainActivity.this, res ,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private class MyAdapter extends BaseAdapter{
        private LayoutInflater mLayoutInflater;

        public MyAdapter(Context context){
            this.mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder{
            public ImageView img;
            public TextView title;
            public TextView info;
            public CheckBox item_cb;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.list_entry, null);
                holder.img = (ImageView)convertView.findViewById(R.id.img);
                holder.title = (TextView)convertView.findViewById(R.id.title);
                holder.info = (TextView)convertView.findViewById(R.id.info);
                holder.item_cb = (CheckBox)convertView.findViewById(R.id.item_cb);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
            holder.title.setText((String) mData.get(position).get("title"));
            holder.info.setText((String)mData.get(position).get("info"));
            holder.item_cb.setTag(position);
            if (checkItems.containsKey(position)){
                holder.item_cb.setChecked(true);
            }else {
                holder.item_cb.setChecked(false);
            }
            holder.item_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int itemId = Integer.parseInt(buttonView.getTag().toString());
                    if (isChecked){
                        checkItems.put(itemId, isChecked);
                    }else{
                        checkItems.remove(itemId);
                    }
                }
            });
            return convertView;
        }
    }

    private List<Map<String,Object>>getData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object>map = new HashMap<String, Object>();
        map.put("img",R.drawable.copyright);
        map.put("title","abc");
        map.put("info","123");
        for (int i =0;i<1000;i++){
            list.add(map);
        }
        return list;
    }
}
