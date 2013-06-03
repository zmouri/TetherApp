package com.explodingsheep.tetherapp;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.explodingsheep.tetherapp.AuthAdapter.SecurityOption;

public class AuthAdapter extends ArrayAdapter<SecurityOption> implements SpinnerAdapter {
    // WifiConfiguration.KeyMgmt.WPA2_PSK is hidden from public API
    private static final int KeyMgmt_WPA2_PSK = 4;

    public final SecurityOption WPA2_PSK = new SecurityOption(R.string.wpa2_psk,
            WifiConfiguration.Protocol.RSN, WifiConfiguration.AuthAlgorithm.OPEN,
            WifiConfiguration.GroupCipher.CCMP, WifiConfiguration.PairwiseCipher.CCMP,
            KeyMgmt_WPA2_PSK);

    public AuthAdapter(Context context) {
        super(context, 0);
    }

    public void initializeOptions() {
        add(WPA2_PSK); // TODO only WPA2 support is implemented
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView authView = (TextView) LayoutInflater.from(getContext()).inflate(
                R.layout.auth_view, null);

        final SecurityOption option = getItem(position);
        final String authName = getContext().getResources().getString(option.getResId());
        authView.setText(authName);

        return authView;
    }

    public class SecurityOption {
        private final int mResId;
        private final int mProtocol;
        private final int mAuth;
        private final int mGroupCipher;
        private final int mPairCipher;
        private final int mKeyMgmt;

        private SecurityOption(int resId,
                int protocol,
                int auth,
                int groupCipher,
                int pairCipher,
                int keyMgmt) {
            mResId = resId;
            mProtocol = protocol;
            mAuth = auth;
            mGroupCipher = groupCipher;
            mPairCipher = pairCipher;
            mKeyMgmt = keyMgmt;
        }

        public int getResId() {
            return mResId;
        }

        public int getProtocol() {
            return mProtocol;
        }

        public int getAuth() {
            return mAuth;
        }

        public int getGroupCipher() {
            return mGroupCipher;
        }

        public int getPairCipher() {
            return mPairCipher;
        }

        public int getKeyMgmt() {
            return mKeyMgmt;
        }

        public String toString() {
            return getContext().getResources().getString(mResId);
        }
    }
}
