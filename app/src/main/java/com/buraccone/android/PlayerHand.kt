package com.buraccone.android

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayerHand(
    cards: List<Card>, // Your card model
    modifier: Modifier = Modifier
) {
    // Card dimensions
    val cardWidth = 60.dp
    val cardHeight = 90.dp

    // Calculate overlap amount
    val overlap = if (cards.size > 9) {
        // Squeeze more if more than 9 cards
        (cardWidth / 2) - ((cards.size - 9) * 2).dp
    } else {
        cardWidth / 2
    }

    Box(
        modifier = modifier
            .height(cardHeight)
            .width(400.dp)
    ) {
        cards.forEachIndexed { index, card ->
            CardView(
                card = card,
                modifier = Modifier
                    .offset(x = overlap * index)
                    .size(cardWidth, cardHeight) // Set fixed dimensions for cards
            )
        }
    }
}

@Composable
fun CardView(
    card: Card,
    modifier: Modifier = Modifier
) {
    val displayRank = when (card.rank) {
        "A" -> "A"
        "K" -> "K"
        "Q" -> "Q"
        "J" -> "J"
        "Joker" -> "Joker"
        else -> card.rank
    }

    val middleContent = when (card.rank) {
        "K" -> "👑" // King hat emoji in the middle for King cards
        else -> ""
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top-left rank and suit
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = displayRank,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = card.suitColor
                )
                Text(
                    text = card.suitSymbol,
                    fontSize = 12.sp,
                    color = card.suitColor
                )
            }

            // Middle content for special cards
            if (middleContent.isNotEmpty()) {
                Text(
                    text = middleContent,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

// Example data model
data class Card(val rank: String, val suitSymbol: String, val suitColor: Color)

@Preview
@Composable
fun PlayerHandPreview() {
    val sampleCards = listOf(
        Card("8", "♥", Color.Red),
        Card("7", "♥", Color.Red),
        Card("5", "♠", Color.Black),
        Card("7", "♠", Color.Black),
        Card("3", "♦", Color.Red),
        Card("A", "♦", Color.Red),
        Card("4", "♣", Color.Black),
        Card("6", "♣", Color.Black),
        Card("3", "♥", Color.Red),
        Card("10", "♥", Color.Red),
        Card("J", "♥", Color.Red)
    )
    PlayerHand(cards = sampleCards, modifier = Modifier)
}

@Preview
@Composable
fun PlayerHandPreview9Cards() {
    val sampleCards = listOf(
        Card("8", "♥", Color.Red),
        Card("7", "♥", Color.Red),
        Card("5", "♠", Color.Black),
        Card("7", "♠", Color.Black),
        Card("3", "♦", Color.Red),
        Card("A", "♦", Color.Red),
        Card("4", "♣", Color.Black),
        Card("6", "♣", Color.Black),
        Card("3", "♥", Color.Red)
    )
    PlayerHand(cards = sampleCards, modifier = Modifier)
}

@Preview
@Composable
fun PlayerHandPreview15Cards() {
    val sampleCards = listOf(
        Card("8", "♥", Color.Red),
        Card("7", "♥", Color.Red),
        Card("5", "♠", Color.Black),
        Card("7", "♠", Color.Black),
        Card("3", "♦", Color.Red),
        Card("A", "♦", Color.Red),
        Card("4", "♣", Color.Black),
        Card("6", "♣", Color.Black),
        Card("3", "♥", Color.Red),
        Card("10", "♥", Color.Red),
        Card("J", "♥", Color.Red),
        Card("Q", "♠", Color.Black),
        Card("K", "♠", Color.Black),
        Card("2", "♦", Color.Red),
        Card("9", "♣", Color.Black)
    )
    PlayerHand(cards = sampleCards, modifier = Modifier)
}

@Preview
@Composable
fun KingCardPreview() {
    val kingCard = Card("K", "♠", Color.Black)
    CardView(card = kingCard, modifier = Modifier.size(60.dp, 90.dp))
}
