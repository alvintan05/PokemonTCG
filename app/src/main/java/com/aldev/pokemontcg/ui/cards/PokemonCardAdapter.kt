package com.aldev.pokemontcg.ui.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aldev.pokemontcg.data.source.remote.response.Card
import com.aldev.pokemontcg.databinding.ItemCardBinding
import com.bumptech.glide.Glide

class PokemonCardAdapter : RecyclerView.Adapter<PokemonCardAdapter.ViewHolder>() {
    private val pokemonCards = mutableListOf<Card>()

    fun updateData(newPokemonCards: MutableList<Card>) {
        pokemonCards.clear()
        pokemonCards.addAll(newPokemonCards)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonCards[position])
    }

    override fun getItemCount(): Int = pokemonCards.size

    inner class ViewHolder(val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: Card) {

            Glide.with(binding.root)
                .load(item.images?.small)
                .into(binding.ivLogo)

            binding.tvCardName.text = item.name
            binding.tvRarity.text = item.rarity

            binding.root.setOnClickListener { view ->
                val action =
                    PokemonCardListFragmentDirections.actionPokemonCardListFragmentToPokemonCardDetailFragment(
                        item,
                        item.name!!
                    )
                view.findNavController().navigate(action)
            }

        }

    }
}