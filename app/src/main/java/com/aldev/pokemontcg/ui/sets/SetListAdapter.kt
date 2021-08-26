package com.aldev.pokemontcg.ui.sets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aldev.pokemontcg.data.source.remote.response.Set
import com.aldev.pokemontcg.databinding.ItemSetBinding
import com.bumptech.glide.Glide

class SetListAdapter : RecyclerView.Adapter<SetListAdapter.ViewHolder>() {

    private val pokemonSets = mutableListOf<Set>()

    fun updateData(newPokemonSets: MutableList<Set>) {
        pokemonSets.clear()
        pokemonSets.addAll(newPokemonSets)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonSets[position])
    }

    override fun getItemCount(): Int = pokemonSets.size

    inner class ViewHolder(val binding: ItemSetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: Set) {

            Glide.with(binding.root)
                .load(item.images.logo)
                .into(binding.ivLogo)

            binding.tvSetName.text = item.name

            binding.root.setOnClickListener { view ->
                val action =
                    SetListFragmentDirections.actionSetListFragmentToPokemonCardListFragment(
                        item.name,
                        item.id
                    )
                view.findNavController().navigate(action)
            }

        }

    }

}