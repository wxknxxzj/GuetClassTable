package top.yvyan.guettable.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import top.yvyan.guettable.AboutActivity;
import top.yvyan.guettable.HelperActivity;
import top.yvyan.guettable.LoginActivity;
import top.yvyan.guettable.MySettingActivity;
import top.yvyan.guettable.R;
import top.yvyan.guettable.SetTermActivity;
import top.yvyan.guettable.ShareActivity;
import top.yvyan.guettable.data.AccountData;
import top.yvyan.guettable.data.GeneralData;
import top.yvyan.guettable.util.AppUtil;
import top.yvyan.guettable.util.ToastUtil;

public class PersonFragment extends Fragment implements View.OnClickListener {
    private static PersonFragment personFragment;

    private static final String TAG = "PersonFragment";
    private View view;

    private View person_userNameAndNo;
    private View person_login;
    private View person_setting;
    private Button buttonQuit;
    private View person_line_1;
    private View person_userInfo;
    private View person_userInfo_card;
    private TextView person_name;
    private TextView person_number;
    private TextView person_grade;
    private TextView person_term;
    private TextView person_week;
    private TextView profileVersion;

//    private View info;
    private View help;
    private View share;
    private View update;
    private View about;

    private AccountData accountData;
    private GeneralData generalData;
    private OnButtonClick onButtonClick;

    public PersonFragment() {
    }

    public static PersonFragment newInstance() {
        if (personFragment == null) {
            personFragment = new PersonFragment();
        }
        return personFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "createPersonFragmentView");
        view = inflater.inflate(R.layout.fragement_preson, container, false);
        accountData = AccountData.newInstance(getContext());
        generalData = GeneralData.newInstance(getContext());
        initView();
        updateView();
        person_setting = view.findViewById(R.id.person_setting);
        person_setting.setOnClickListener(this);

        return view;
    }

    private void initView() {
        person_userNameAndNo = view.findViewById(R.id.person_userNameAndNo);
        person_login = view.findViewById(R.id.person_login);
        person_login.setOnClickListener(this);
        person_name = view.findViewById(R.id.person_name);
        person_number = view.findViewById(R.id.person_number);
        buttonQuit = view.findViewById(R.id.btn_quit);
        buttonQuit.setOnClickListener(this);
        person_line_1 = view.findViewById(R.id.person_line_1);
        person_userInfo = view.findViewById(R.id.person_userInfo);
        person_userInfo_card = view.findViewById(R.id.person_userInfo_card);
        person_userInfo_card.setOnClickListener(this);
        person_grade = view.findViewById(R.id.person_grade);
        person_term = view.findViewById(R.id.person_term);
        person_week = view.findViewById(R.id.person_week);
        profileVersion = view.findViewById(R.id.tv_profile_version);
        profileVersion.setText(AppUtil.getAppVersionName(getContext()));

//        info = view.findViewById(R.id.person_detail_info);
//        info.setOnClickListener(this);

        help = view.findViewById(R.id.person_help);
        help.setOnClickListener(this);
        share = view.findViewById(R.id.person_share);
        share.setOnClickListener(this);
        update = view.findViewById(R.id.person_update);
        update.setOnClickListener(this);
        about = view.findViewById(R.id.person_about);
        about.setOnClickListener(this);
    }

    public void updateView() {
        if (accountData.getIsLogin()) {
            person_userNameAndNo.setVisibility(View.VISIBLE);
            person_line_1.setVisibility(View.VISIBLE);
            person_userInfo.setVisibility(View.VISIBLE);
            buttonQuit.setVisibility(View.VISIBLE);
            person_login.setVisibility(View.GONE);
            person_name.setText(generalData.getName());
            person_number.setText(generalData.getNumber());
            person_grade.setText(generalData.getGrade() + "级");
            person_term.setText(generalData.getTerm());
            person_week.setText("第" + generalData.getWeek() + "周");

        } else {
            person_userNameAndNo.setVisibility(View.GONE);
            person_line_1.setVisibility(View.GONE);
            person_userInfo.setVisibility(View.GONE);
            buttonQuit.setVisibility(View.GONE);
            person_login.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.person_userInfo_card:
            case R.id.person_login:
                if (accountData.getIsLogin()) {
                    intent = new Intent(getContext(), SetTermActivity.class);
                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.btn_quit:
                accountData.logoff();
                updateView();
                break;
            case  R.id.person_setting:
                intent = new Intent(getContext(), MySettingActivity.class);
                startActivity(intent);
                break;
            case R.id.person_help:
                intent = new Intent(getContext(), HelperActivity.class);
                startActivity(intent);
                break;
            case R.id.person_share:
                intent = new Intent(getContext(), ShareActivity.class);
                startActivity(intent);
                break;
            case R.id.person_update:
                Uri uri = Uri.parse(getContext().getResources().getString(R.string.downloadApp_url));
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.person_about:
                intent = new Intent(getContext(), AboutActivity.class);
                startActivity(intent);
                break;
            default:
                ToastUtil.showToast(getContext(), "尽请期待");
        }
    }

    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }
}
