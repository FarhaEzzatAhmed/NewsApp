package com.example.news_app.route.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.databinding.ItemCategoryBinding

class CategoriesRecyclerAdapter(val items:List<Category>):RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoriesRecyclerAdapter.ViewHolder>() {

    class CategoriesRecyclerAdapter{
        class ViewHolder(val itemBinding:ItemCategoryBinding)
            :RecyclerView.ViewHolder(itemBinding.root){
                fun bind(category:Category){
                    itemBinding.category = category
                    itemBinding.invalidateAll()
                }




        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesRecyclerAdapter.ViewHolder {
         val viewBinding = ItemCategoryBinding.inflate(
             LayoutInflater.from(parent.context),parent,false)
        return CategoriesRecyclerAdapter.ViewHolder(viewBinding)

    }

    override fun onBindViewHolder(holder: CategoriesRecyclerAdapter.ViewHolder, position: Int) {
          holder.bind(items[position])


        //holder.itemBinding.title.setText(items[position].name)
       // holder.itemBinding.container.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,items[position].backgroundcolorId ))
       // holder.itemBinding.image.setImageResource(items[position].imageId)

       // we use let instade of use if it is not null
        onItemClickListener?.let { clickListener->
            holder.itemBinding.container.setOnClickListener{
                clickListener.onItemClick(position,items[position])
            }

        }

        //holder.itemBinding.container.setOnClickListener{
            //onItemClickListener?.onItemClick(position,items[position])
        //}
    }

    override fun getItemCount(): Int = items.size
    var  onItemClickListener:OnItemClickListener? = null
    interface OnItemClickListener{
        fun  onItemClick(pos:Int,item:Category)


    }
}