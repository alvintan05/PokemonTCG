package com.aldev.pokemontcg.ui.detailcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.aldev.pokemontcg.data.source.remote.response.Card
import com.aldev.pokemontcg.databinding.FragmentPokemonCardDetailBinding
import com.aldev.pokemontcg.utils.PokemonDetailCardViewState
import com.bumptech.glide.Glide

class PokemonCardDetailFragment : Fragment() {

    private var _binding: FragmentPokemonCardDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: PokemonCardDetailViewModel
    private val args: PokemonCardDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonCardDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonCard = args.pokemonCard
        vm = ViewModelProvider(this)[PokemonCardDetailViewModel::class.java]

        vm.apply {
            viewState.observe(
                viewLifecycleOwner,
                Observer(this@PokemonCardDetailFragment::handleState)
            )
            setData(pokemonCard)
        }

    }

    private fun handleState(viewState: PokemonDetailCardViewState) {
        viewState.data?.let { showDetail(it) }
    }

    private fun showDetail(data: Card) {
        Glide.with(this)
            .load(data.images?.large)
            .into(binding.ivLogo)

        binding.tvName.text = data.name
        binding.tvRarity.text = data.rarity
    }

}