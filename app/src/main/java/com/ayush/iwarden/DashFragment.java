package com.ayush.iwarden;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashFragment extends Fragment {

    private ArrayList<DDClass> dds;
    private DDRecyclerAdapter DDAdapter;
    RecyclerView recyclerView;

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    private ArrayList<Event> events;

    private ArrayList<NoticeClass> notices;
    private NoticeRecyclerAdapter NoteAdapter;
    RecyclerView noterecyclerView;

    private PieChart pieChart;
    private BarChart barChart;
    private PieChart catChart;

    Context mcontext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashFragment() {
        // Required empty public constructor
    }
    public DashFragment(Context context) {
        mcontext = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashFragment newInstance(String param1, String param2) {
        DashFragment fragment = new DashFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if(savedInstanceState==null) {
            dds = new ArrayList<>();
            events = new ArrayList<>();
            notices = new ArrayList<>();

            dds.add(new DDClass(R.drawable.ic_clean, "Cleaning", true));
            dds.add(new DDClass(R.drawable.ic_infra, "Infra", false));
            dds.add(new DDClass(R.drawable.ic_electrician, "Electric", true));
            dds.add(new DDClass(R.drawable.ic_pest, "Pest", true));
            dds.add(new DDClass(R.drawable.ic_clean, "Cleaning", false));
            dds.add(new DDClass(R.drawable.ic_add, "", true));

            DDAdapter = new DDRecyclerAdapter(mcontext, dds);


            events.add(new Event(Color.GREEN, RegularToEpoch("07/01/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/02/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/03/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/04/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/05/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.RED, RegularToEpoch("07/06/2022 09:00:00"), "Remaining Tasks"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/07/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/08/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.RED, RegularToEpoch("07/09/2022 09:00:00"), "Remaining Tasks"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/10/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/11/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/12/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/13/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/14/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.GREEN, RegularToEpoch("07/15/2022 09:00:00"), "All tasks done"));
            events.add(new Event(Color.RED, RegularToEpoch("07/16/2022 01:00:00"), "Remaining Tasks"));

            notices.add(new NoticeClass("Warden", Color.parseColor("#F44336"), R.drawable.ic_grievance3, mcontext.getDrawable(R.drawable.red_border), "18th July", "All the resident of the hostel are notified that the election for the mess commitee will be starting from 22/07/22. All the candidates are requested to be prepared and all the voters to be decisive..."));
            notices.add(new NoticeClass("Mess Committee", Color.parseColor("#4CAF50"), R.drawable.ic_grievance4, mcontext.getDrawable(R.drawable.green_border), "18th July", "All the resident of the hostel are notified that the election for the mess commitee will be starting from 22/07/22."));
            notices.add(new NoticeClass("Warden", Color.parseColor("#F44336"), R.drawable.ic_grievance3, mcontext.getDrawable(R.drawable.red_border), "18th July", "All the resident of the hostel are notified that the election for the mess commitee will be starting from 22/07/22. All the candidates are requested to be prepared and all the voters to be decisive..."));
            notices.add(new NoticeClass("Mess Committee", Color.parseColor("#4CAF50"), R.drawable.ic_grievance4, mcontext.getDrawable(R.drawable.green_border), "18th July", "All the resident of the hostel are notified that the election for the mess commitee will be starting from 22/07/22."));

            NoteAdapter = new NoticeRecyclerAdapter(notices);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(DDAdapter);

        compactCalendar = (CompactCalendarView)view.findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        compactCalendar.addEvents(events);

        noterecyclerView = (RecyclerView)view.findViewById(R.id.rcv2);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mcontext,LinearLayoutManager.VERTICAL,false);
        noterecyclerView.setLayoutManager(linearLayoutManager1);
        noterecyclerView.setAdapter(NoteAdapter);

        pieChart = view.findViewById(R.id.activity_main_piechart);
        pieChart.getLegend().setEnabled(false);
        setupPieChart();
        loadPieChartData();

        ProgressBar prg = view.findViewById(R.id.inbox_prog);
        prg.setProgress(100);

        ProgressBar prg2 = view.findViewById(R.id.default_prog);
        prg2.setProgress(100);

        barChart = view.findViewById(R.id.bar_chart);
        barChart.getLegend().setEnabled(false);
        setupBarChart();
        loadBarChartData();


        catChart = view.findViewById(R.id.grievances_piechart);
        catChart.getLegend().setEnabled(false);
        setupCatChart();
        loadCatChartData();


        return view;
    }


    private long RegularToEpoch(String regTime){
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.getDefault());
        long epoch = 0;
        try {
            epoch = date.parse(regTime).getTime();    //"01/01/1970 01:00:00"
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epoch;
    }

    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("75%");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);
    }

    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.25f, ""));
        entries.add(new PieEntry(0.75f, ""));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#E7E7E7"));
        colors.add(Color.parseColor("#21D0FC"));

//        for (int color: ColorTemplate.MATERIAL_COLORS) {
//            colors.add(color);
//        }
//
//        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
//            colors.add(color);
//        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(0);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);


    }


    private void setupBarChart(){
        barChart.setDrawValueAboveBar(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBorders(false);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setEnabled(false);
    }

    private void loadBarChartData(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0,85));
        entries.add(new BarEntry(1,83));
        entries.add(new BarEntry(2,88));
        entries.add(new BarEntry(3,91));
        entries.add(new BarEntry(4,72));
        entries.add(new BarEntry(5,80));
        entries.add(new BarEntry(6,75));

        BarDataSet barDataSet = new BarDataSet(entries,"1 Week Data");
        barDataSet.setStackLabels(null);

        BarData barData = new BarData();
        barData.setValueFormatter(new PercentFormatter());
        barData.setDrawValues(true);
        barData.addDataSet(barDataSet);


        barChart.setData(barData);
        barChart.invalidate();

    }


    private void setupCatChart(){
        catChart.setDrawHoleEnabled(true);
        catChart.setUsePercentValues(true);
        catChart.setEntryLabelTextSize(12);
        catChart.setEntryLabelColor(Color.BLACK);
        catChart.setCenterText("Top Complaints");
        catChart.setCenterTextSize(12);
        catChart.getDescription().setEnabled(false);
    }
    private void loadCatChartData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f, ""));
        entries.add(new PieEntry(0.18f, ""));
        entries.add(new PieEntry(0.15f, ""));
        entries.add(new PieEntry(0.10f, ""));
        entries.add(new PieEntry(0.37f, ""));


        ArrayList<Integer> colors = new ArrayList<>();
//        colors.add(Color.parseColor("#E7E7E7"));
//        colors.add(Color.parseColor("#21D0FC"));

        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Complaints Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(catChart));
        data.setValueTextSize(0);

        catChart.setData(data);
        catChart.invalidate();

        catChart.animateY(1400, Easing.EaseInOutQuad);
    }

}