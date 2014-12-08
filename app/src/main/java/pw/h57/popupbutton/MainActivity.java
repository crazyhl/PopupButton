package pw.h57.popupbutton;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import pw.h57.popupbuttonlibrary.PopupButton;
import pw.h57.popupbuttonlibrary.adapter.PopupAdapter;


public class MainActivity extends ActionBarActivity {
    private PopupButton btn;
    private PopupWindow window;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (PopupButton) findViewById(R.id.btn);
        window = btn.getPopupWindow();
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
                window.dismiss();
            }
        });
        btn.setPopupView(view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
