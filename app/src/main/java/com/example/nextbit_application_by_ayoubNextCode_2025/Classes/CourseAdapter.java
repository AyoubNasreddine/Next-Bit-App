package com.example.nextbit_application_by_ayoubNextCode_2025.Classes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextbit_application_by_ayoubNextCode_2025.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    ArrayList <Course> courses;
    public CourseAdapter(ArrayList<Course> courses){
        this.courses =courses;
    }
    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.custom_course,null,false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course=courses.get(position);
        holder.name.setText(course.getName());
        holder.description.setText(course.getCourseDescription());
        holder.imgView.setImageResource(course.getImageResource());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView name,description;
        ImageView imgView ;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.course_name);
            description=itemView.findViewById(R.id.courseDescription);
            imgView=itemView.findViewById(R.id.imageView_course);
        }
    }
}
