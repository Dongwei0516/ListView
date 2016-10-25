package com.example.dongwei.a1;

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

/**
 * Created by dongwei on 16/10/19.
 */

public class MainActivity extends Activity {
    private List<Map<String, Object>> mData;
    private ListView listView;
    Map<Integer,Boolean>checkItems = new HashMap<Integer,Boolean>();
    private int type;

    Button main_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_btn = (Button) findViewById(R.id.main_btn);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map map =checkItems;

                String res = "";

                ArrayList key = new ArrayList<>(map.keySet());

                for (int i = 0; i<key.size(); i++) {
                    if (i != key.size() - 1) {
                        res = res + "{" + key.get(i) + "}" + ",";
                    } else {
                        res = res + "{" + key.get(i) + "}";
                    }
                }


                Toast toast = Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        listView = (ListView) findViewById(R.id.MyListView);
        listView.setAdapter(adapter);
    }


    public final class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView info;
        public CheckBox item_cb;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;


        public MyAdapter(Context context) {

            this.mInflater = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Map<String, Object> getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public int getItemViewType(int position){
            if (position %2 == 0){
                return 0;
            }else {
                return 1;
            }
        }

        @Override
        public int getViewTypeCount(){
            return 2;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            holder = new ViewHolder();

            int type = getItemViewType(position);

            if (convertView == null) {
                switch (type){
                    case 0:
                       convertView = mInflater.inflate(R.layout.list_entry,parent ,false);
                        holder.img = (ImageView) convertView.findViewById(R.id.img);
                        holder.title = (TextView) convertView.findViewById(R.id.title);
                        holder.info = (TextView) convertView.findViewById(R.id.info);
                        holder.item_cb = (CheckBox) convertView.findViewById(R.id.item_cb);
                        convertView.setTag(holder);
                        break;
                    case 1:
                        convertView = mInflater.inflate(R.layout.list_entry_2,parent,false);
                        holder.img = (ImageView) convertView.findViewById(R.id.img);
                        holder.title = (TextView) convertView.findViewById(R.id.title);
                        holder.info = (TextView) convertView.findViewById(R.id.info);
                        holder.item_cb = (CheckBox) convertView.findViewById(R.id.item_cb);
                        convertView.setTag(holder);
                        break;

                }
//                holder = new ViewHolder();
//                convertView = mInflater.inflate(R.layout.list_entry, parent, false);
//                holder.img = (ImageView) convertView.findViewById(R.id.img);
//                holder.title = (TextView) convertView.findViewById(R.id.title);
//                holder.info = (TextView) convertView.findViewById(R.id.info);
//                holder.item_cb = (CheckBox) convertView.findViewById(R.id.item_cb);
//
//                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.img.setBackgroundResource((Integer) getItem(position).get("img"));
            holder.title.setText((String) mData.get(position).get("title"));
            holder.info.setText((String) mData.get(position).get("info"));
            holder.item_cb.setTag(position);
            if (checkItems.containsKey(position))
                holder.item_cb.setChecked(true);
            else
                holder.item_cb.setChecked(false);
            holder.item_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int itemId = Integer.parseInt(buttonView.getTag().toString());
                    if (isChecked) {

                        checkItems.put(itemId , isChecked);
                    } else {
                        checkItems.remove(itemId);
                    }
                }
            });

            return convertView;
        }
    }


    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "abc");
        map.put("info", "123");
        map.put("img", R.drawable.copyright);
        for (int i = 0; i < 1000; i++) {
            list.add(map);
        }
        return list;
    }
}


