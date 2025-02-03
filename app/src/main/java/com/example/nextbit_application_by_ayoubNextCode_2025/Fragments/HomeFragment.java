package com.example.nextbit_application_by_ayoubNextCode_2025.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nextbit_application_by_ayoubNextCode_2025.Activities.CourseDetailsActivity;
import com.example.nextbit_application_by_ayoubNextCode_2025.Classes.Course;
import com.example.nextbit_application_by_ayoubNextCode_2025.Classes.CourseAdapter;
import com.example.nextbit_application_by_ayoubNextCode_2025.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private RecyclerView bestCoursesRecyclerView, futureCoursesRv;
    private ProgressBar pb, pb2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        bestCoursesRecyclerView = v.findViewById(R.id.best_courses_recyclerView);
        pb = v.findViewById(R.id.progressBar1);
        futureCoursesRv = v.findViewById(R.id.up_coming_courses_recyclerView);
        pb2 = v.findViewById(R.id.progressBar2);
        initBestCourses();
        initFutureCourses();
        return v;
    }

    public void initBestCourses() {
        ArrayList<Course> bestCourses = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        //here changes
        reference = database.getReference("BestCourses");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bestCourses.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        if (issue != null) {
                            bestCourses.add(issue.getValue(Course.class));
                        }
                    }
                    if (!bestCourses.isEmpty()) {
                        CourseAdapter adapter = new CourseAdapter(bestCourses);
                        bestCoursesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        bestCoursesRecyclerView.setHasFixedSize(true);
                        bestCoursesRecyclerView.setAdapter(adapter);
                        pb.setVisibility(View.INVISIBLE);
                    }
                } else {
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "No courses Available !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "something went wrong !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initFutureCourses() {
        ArrayList<Course> futureCourses = new ArrayList<>();
        reference = database.getReference("Courses");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        if (issue != null) {
                            Course course = issue.getValue(Course.class);
                            if (!(course.isAvailable())) {
                                futureCourses.add(course);
                            }
                        }
                    }
                    if (!futureCourses.isEmpty()) {
                        CourseAdapter adapter = new CourseAdapter(futureCourses);
                        futureCoursesRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        futureCoursesRv.setHasFixedSize(true);
                        futureCoursesRv.setAdapter(adapter);
                        pb2.setVisibility(View.INVISIBLE);
                    }
                } else {
                    pb2.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "No future Courses Available !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //
            }
        });
    }
}
