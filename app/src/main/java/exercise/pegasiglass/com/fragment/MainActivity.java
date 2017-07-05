package exercise.pegasiglass.com.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button first_fragment;
    private Button second_fragment;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private ContentFragment one_fragment;
    private FriendFragment two_fragment;
    private Button control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        setDefaultFragment();
    }

    private void initView() {
        first_fragment = (Button) findViewById(R.id.first_fragment);
        second_fragment = (Button) findViewById(R.id.second_fragment);
        control = (Button) findViewById(R.id.control);
    }

    private void initListener() {
        first_fragment.setOnClickListener(this);
        second_fragment.setOnClickListener(this);
        control.setOnClickListener(this);
    }

    private void setDefaultFragment() {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        one_fragment = ContentFragment.newInstance("first","fragment");
        transaction.add(R.id.id_content, one_fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first_fragment:
//                if (one_fragment == null){
//                    one_fragment = ContentFragment.newInstance("first","fragment");
//                }
                one_fragment = ContentFragment.newInstance("first","fragment");
                addFragment(one_fragment,"fragment1");
                break;
            case R.id.second_fragment:
//                if (two_fragment == null){
//                    two_fragment = FriendFragment.newInstance("two","fragment");
//                }
                two_fragment = FriendFragment.newInstance("two","fragment");
                addFragment(two_fragment,"fragment2");
                break;
            case R.id.control:
                FragmentManager manager = getSupportFragmentManager();
                Fragment fragment = manager.findFragmentByTag("fragment1");
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.show(fragment);
//                transaction.addToBackStack("hide fragment1");
                transaction.commit();
                Log.e("MainActivity", "进入control了 ");
                break;
        }
    }
    private void addFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.id_content,fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }
}
