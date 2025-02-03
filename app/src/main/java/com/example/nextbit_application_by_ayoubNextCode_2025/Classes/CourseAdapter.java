package com.example.nextbit_application_by_ayoubNextCode_2025.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextbit_application_by_ayoubNextCode_2025.Activities.CourseDetailsActivity;
import com.example.nextbit_application_by_ayoubNextCode_2025.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private ArrayList<Course> courses;

    public CourseAdapter(ArrayList<Course> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.custom_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.name.setText(course.getName());
        holder.description.setText(course.getCourseDescription());
        holder.imgView.setImageResource(course.getImageResource());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name, description;
        ImageView imgView;
        Context context;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.course_name);
            description = itemView.findViewById(R.id.courseDescription);
            imgView = itemView.findViewById(R.id.imageView_course);
            context= itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Course course =courses.get(position);
            Intent i =new Intent(context, CourseDetailsActivity.class);
            i.putExtra("name",course.getName());
            i.putExtra("description",course.getCourseDescription());
            i.putExtra("available",course.isAvailable());
            i.putExtra("image",course.getImageResource());
            context.startActivity(i);
        }
    }
}   