package pw.h57.popupbutton;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pw.h57.popupbuttonlibrary.PopupButton;
import pw.h57.popupbuttonlibrary.adapter.PopupAdapter;


public class MainActivity extends ActionBarActivity {
    private PopupButton btn;
    private PopupButton btn2;
    private LayoutInflater inflater;
    private List<String> cValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (PopupButton) findViewById(R.id.btn);
        inflater = LayoutInflater.from(this);

        View view = inflater.inflate(R.layout.popup,null);
        ListView lv = (ListView) view.findViewById(R.id.lv);
        final String[] arr = {"item01","item02","item03","item04","item05","item06","item07","item08","item09","item10"};
        final PopupAdapter adapter = new PopupAdapter(this,R.layout.popup_item,arr,R.drawable.normal,R.drawable.press);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setPressPostion(position);
                adapter.notifyDataSetChanged();
                btn.setText(arr[position]);
                btn.hidePopup();
            }
        });
        btn.setPopupView(view);

        View view2 = inflater.inflate(R.layout.popup2,null);
        ListView pLv = (ListView) view2.findViewById(R.id.parent_lv);
        final ListView cLv = (ListView) view2.findViewById(R.id.child_lv);
        List<String> pList = new ArrayList<>();
        final List<List<String>> cList = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            pList.add("p" + i);
            List<String> t = new ArrayList<>();
            for(int j = 0; j < 15; j++) {
                t.add(pList.get(i) + "-c" + j);
            }
            cList.add(t);
        }

        cValues = new ArrayList<>();
        cValues.addAll(cList.get(0));
        final PopupAdapter pAdapter = new PopupAdapter(this,R.layout.popup_item,pList,R.drawable.normal,R.drawable.press2);
        final PopupAdapter cAdapter = new PopupAdapter(this,R.layout.popup_item,cValues,R.drawable.normal,R.drawable.press);
        pAdapter.setPressPostion(0);

        pLv.setAdapter(pAdapter);
        cLv.setAdapter(cAdapter);

        pLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pAdapter.setPressPostion(position);
                pAdapter.notifyDataSetChanged();
                cValues.clear();
                cValues.addAll(cList.get(position));
                cAdapter.notifyDataSetChanged();
                cAdapter.setPressPostion(-1);
                cLv.setSelection(0);
            }
        });

        cLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cAdapter.setPressPostion(position);
                cAdapter.notifyDataSetChanged();
                btn2.setText(cValues.get(position));
                btn2.hidePopup();
            }
        });
        btn2 = (PopupButton) findViewById(R.id.btn2);
        btn2.setPopupView(view2);
    }

}
