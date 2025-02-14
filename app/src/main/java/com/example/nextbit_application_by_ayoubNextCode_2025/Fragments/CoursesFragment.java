package com.example.nextbit_application_by_ayoubNextCode_2025.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nextbit_application_by_ayoubNextCode_2025.Classes.Course;
import com.example.nextbit_application_by_ayoubNextCode_2025.Classes.CourseAdapter;
import com.example.nextbit_application_by_ayoubNextCode_2025.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rv;
    private ProgressBar pb;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    public CoursesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoursesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
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
        View v = inflater.inflate(R.layout.fragment_courses, container, false);
        rv = v.findViewById(R.id.recyclerViewCoursesFragment);
        pb = v.findViewById(R.id.progressBarCoursesFragment);
        initCourses();
        return v;
    }

    private void initCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Courses");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courses.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        if (issue != null) {
                            courses.add(issue.getValue(Course.class));
                        }
                    }
                    if (!courses.isEmpty()) {
                        CourseAdapter adapter = new CourseAdapter(courses);
                        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        rv.setHasFixedSize(true);
                        rv.setAdapter(adapter);
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
}