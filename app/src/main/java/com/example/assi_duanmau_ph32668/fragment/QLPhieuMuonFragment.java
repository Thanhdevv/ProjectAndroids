package com.example.assi_duanmau_ph32668.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assi_duanmau_ph32668.DAO.PhieuMuonDAO;
import com.example.assi_duanmau_ph32668.DAO.ThanhVienDAO;
import com.example.assi_duanmau_ph32668.DAO.SachDAO;
import com.example.assi_duanmau_ph32668.R;
import com.example.assi_duanmau_ph32668.adapter.PhieuMuonAdapter;
import com.example.assi_duanmau_ph32668.model.PhieuMuon;
import com.example.assi_duanmau_ph32668.model.Sach;
import com.example.assi_duanmau_ph32668.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class QLPhieuMuonFragment extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    RecyclerView recycleQLPhieuMuon;
    ArrayList<PhieuMuon> list;
    PhieuMuonAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlphieumuon, container, false);
        recycleQLPhieuMuon = view.findViewById(R.id.recycleQLPhieuMuon);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        //data

        //adapter
        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;

    }
private void loadData(){
    phieuMuonDAO = new PhieuMuonDAO(getContext());
    list = phieuMuonDAO.getDSPhieuMuon();
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    recycleQLPhieuMuon.setLayoutManager(linearLayoutManager);
    adapter = new PhieuMuonAdapter(list, getContext());
    recycleQLPhieuMuon.setAdapter(adapter);
}
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_phieumuon, null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        //show tv
        getDataThanhVien(spnThanhVien);
        //show sach
        getSach(spnSach);
        builder.setView(view);
        //click them
        builder.setPositiveButton("Them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //lay ma tv
                HashMap<String, String>hsTV = (HashMap<String, String>) spnThanhVien.getSelectedItem();
                int matv = Integer.parseInt(hsTV.get("matv"));
                //lay ma sach
                HashMap<String, String> hsSach = (HashMap<String, String>) spnSach.getSelectedItem();
                int masach = Integer.parseInt(hsSach.get("masach"));
                //lay tien
                int tien = Integer.parseInt(edtTien.getText().toString());
                themPhieuMuon(matv, masach, tien);
            }
        });
        //
        builder.setNegativeButton("huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    //hien thi tv

    private void getDataThanhVien(Spinner spnThanhVien){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();

        ArrayList<HashMap<String, String>> listHM = new ArrayList<>();
        for (ThanhVien tv: list){
           HashMap<String, String> hs = new HashMap<>();
           hs.put("matv", String.valueOf(tv.getMatv()));
           hs.put("hoten", tv.getHoten());
           listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new  int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);
    }

    private void getSach(Spinner spnSach){
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list = sachDAO.getDSDauSAch();

        ArrayList<HashMap<String, String>> listHM = new ArrayList<>();
        for (Sach sc: list){
            HashMap<String, String> hs = new HashMap<>();
            hs.put("masach", String.valueOf(sc.getMasach()));
            hs.put("tensach", sc.getTensach());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new  int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }

    private void themPhieuMuon(int matv, int masach, int tien){
        //lay matt
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt", "");
        //lay ngay
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        PhieuMuon phieuMuon = new PhieuMuon(matv, matt, masach, ngay, 0, tien);


        boolean kiemtra = phieuMuonDAO.themPhieuMuon(phieuMuon);
        if (kiemtra){
            Toast.makeText(getContext(), "Successfully", Toast.LENGTH_SHORT).show();
            loadData();

        }else {
            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
        }
    }

}
