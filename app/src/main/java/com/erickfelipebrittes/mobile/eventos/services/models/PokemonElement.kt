package com.erickfelipebrittes.mobile.eventos.services.models

import com.google.gson.annotations.SerializedName

data class PokemonElement (
    val abilities: List<Ability>? = null,
    val base_experience: Long? = null,
    val forms: List<Species>? = null,
    val game_indices: List<GameIndex>? = null,
    val height: Long? = null,
    val held_items: List<Any?>? = null,
    val id: Long? = null,
    val is_default: Boolean? = null,
    val location_area_encounters: String? = null,
    val moves: List<Move>? = null,
    val name: String? = null,
    val order: Long? = null,
    val past_types: List<Any?>? = null,
    val species: Species? = null,
    val sprites: Sprites? = null,
    val stats: List<Stat>? = null,
    val types: List<Type>? = null,
    val weight: Long? = null
)

data class Ability (
    val ability: Species? = null,
    val is_hidden: Boolean? = null,
    val slot: Long? = null
)

data class Species (
    val name: String? = null,
    val url: String? = null
)

data class GameIndex (
    val game_index: Long? = null,
    val version: Species? = null
)

data class Move (
    val move: Species? = null,
    val version_group_details: List<VersionGroupDetail>? = null
)

data class VersionGroupDetail (
    val level_learned_at: Long? = null,
    val move_learn_method: Species? = null,
    val version_group: Species? = null
)

data class GenerationV (
    val black_white: Sprites? = null
)

data class GenerationIv (
    val diamond_pearl: Sprites? = null,
    @SerializedName("heartgold-soulsilver")
    val heartgold_soulsilver: Sprites? = null,
    val platinum: Sprites? = null
)

data class Versions (
    val generationI: GenerationI? = null,
    val generationIi: GenerationIi? = null,
    val generationIii: GenerationIii? = null,
    val generationIv: GenerationIv? = null,
    val generationV: GenerationV? = null,
    val generationVi: Map<String, GenerationVi>? = null,
    val generationVii: GenerationVii? = null,
    val generationViii: GenerationViii? = null
)

data class Sprites (
    val back_default: String? = null,
    val back_female: Any? = null,
    val back_shiny: String? = null,
    val back_shiny_female: Any? = null,
    val front_default: String? = null,
    val front_female: Any? = null,
    val front_shiny: String? = null,
    val front_shiny_female: Any? = null,
    val other: Other? = null,
    val versions: Versions? = null,
    val animated: Sprites? = null
)

data class GenerationI (
    val redBlue: RedBlue? = null,
    val yellow: RedBlue? = null
)

data class RedBlue (
    val back_default: String? = null,
    val back_gray: String? = null,
    val front_default: String? = null,
    val front_gray: String? = null
)

data class GenerationIi (
    val crystal: Crystal? = null,
    val gold: Crystal? = null,
    val silver: Crystal? = null
)

data class Crystal (
    val back_default: String? = null,
    val back_shiny: String? = null,
    val front_default: String? = null,
    val front_shiny: String? = null
)

data class GenerationIii (
    val emerald: Emerald? = null,
    val firered_leafgreen: Crystal? = null,
    val ruby_sapphire: Crystal? = null
)

data class Emerald (
    val front_default: String? = null,
    val front_shiny: String? = null
)

data class GenerationVi (
    val front_default: String? = null,
    val front_female: Any? = null,
    val front_shiny: String? = null,
    val front_shiny_female: Any? = null
)

data class GenerationVii (
    val icons: DreamWorld? = null,
    val ultra_sun_ultra_moon: GenerationVi? = null
)

data class DreamWorld (
    val front_default: String? = null,
    val front_female: Any? = null
)

data class GenerationViii (
    val icons: DreamWorld? = null
)

data class Other (
    val dream_world: DreamWorld? = null,
    val official_artwork: OfficialArtwork? = null
)

data class OfficialArtwork (
    val front_default: String? = null
)

data class Stat (
    val base_stat: Long? = null,
    val effort: Long? = null,
    val stat: Species? = null
)

data class Type (
    val slot: Long? = null,
    val type: Species? = null
)