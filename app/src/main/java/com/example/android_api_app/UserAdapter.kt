package com.example.android_api_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.imageView)
        val name = itemView.findViewById<TextView>(R.id.textView)
        val email = itemView.findViewById<TextView>(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.user_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        Glide.with(context).load(user.avatar).into(holder.img)
        holder.name.text = user.first_name + user.last_name
        holder.email.text = user.email
    }
}