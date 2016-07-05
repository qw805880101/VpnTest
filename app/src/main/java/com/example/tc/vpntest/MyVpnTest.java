package com.example.tc.vpntest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by tc on 2016/7/5.
 */
public class MyVpnTest extends Activity implements View.OnClickListener {

    private Dialog mShowingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = VpnService.prepare(this);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            onActivityResult(0, RESULT_OK, null);
        }
        findviewByid();
    }

    private void findviewByid() {
        Button btnStart = (Button) findViewById(R.id.BTN_START);
        Button btnStop = (Button) findViewById(R.id.BTN_STOP);
        Button btnConnectSvr = (Button) findViewById(R.id.BTN_GETVPNSTATUS);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnConnectSvr.setOnClickListener(this);

    }

    private void startVpn(){
        // 获取参数
        EditText edtIP = (EditText) findViewById(R.id.edtip);
        EditText edtPort = (EditText) findViewById(R.id.edtport);
        EditText edtUname = (EditText) findViewById(R.id.edtuname);
        EditText edtUpwd = (EditText) findViewById(R.id.edtupwd);

        String sIP, sUname, sUpwd;
        int port;

        sIP = edtIP.getText().toString();
        sUname = edtUname.getText().toString();
        sUpwd = edtUpwd.getText().toString();

        if (sIP == null || sIP.length() == 0) {
            showErrorDialog("VPN地址不能为空");
            return;
        }

        try {
            port = Integer.parseInt(edtPort.getText().toString());
        } catch (NumberFormatException e) {
            showErrorDialog("VPN端口有效范围是1-65535");
            return;
        }

        if ((port <= 0) || (port > 65535)) {
            showErrorDialog("VPN端口有效范围是1-65535");
            return;
        }

        if (sUname == null || sUname.length() == 0) {
            showErrorDialog("用户名不能为空");
            return;
        }
        if (sUpwd == null || sUpwd.length() == 0) {
            showErrorDialog("用户密码不能为空");
            return;
        }
    }

    private void showErrorDialog(String sErrInfo) {

        mShowingDialog = new AlertDialog.Builder(this).setTitle("错误")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(sErrInfo)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        return;
                    }
                }).create();
        mShowingDialog.show();
        return;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null){

        }
    }

    @Override
    public void onClick(View v) {

    }
}
