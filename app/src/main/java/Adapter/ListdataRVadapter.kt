package Adapter

import Interface.cardListener
import Model.Hewan
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.newpets.R
import com.example.newpets.databinding.AnimalsbannerBinding

class ListdataRVadapter (val listhewan : ArrayList<Hewan>, val cardListener: cardListener) :
    RecyclerView.Adapter<ListdataRVadapter.viewHolder>() {


    class viewHolder(val itemView: View, val cardListener: cardListener) :
        RecyclerView.ViewHolder(itemView) {

        val binding = AnimalsbannerBinding.bind(itemView)

        fun setData(data: Hewan) {
            binding.textView.text = data.nama
            binding.textView3.text = data.jenis
            binding.textView4.text = "Umur = " + data.umur.toString()

            if (data.imageUri.isNotEmpty()) {
                binding.imageView.setImageURI(Uri.parse(data.imageUri))
            }
            binding.button2.setOnClickListener {
                cardListener.onEditClick(adapterPosition)
            }
            binding.button3.setOnClickListener {

                //remove
                cardListener.onDeleteClick(adapterPosition)
            }
            binding.button4.setOnClickListener {
                Toast.makeText(itemView.context, data.animalsuara, Toast.LENGTH_SHORT).show();
            }
            binding.button5.setOnClickListener {
                Toast.makeText(itemView.context, data.animalmakan, Toast.LENGTH_SHORT).show();
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.animalsbanner, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listhewan[position])
    }

    override fun getItemCount(): Int {
        return listhewan.size
    }
}
