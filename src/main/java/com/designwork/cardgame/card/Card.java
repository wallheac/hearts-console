package com.designwork.cardgame.card;

import java.util.Arrays;

public enum Card {
    AceClubs(Suit.CLUBS, Rank.ACE),
    TwoClubs(Suit.CLUBS, Rank.TWO),
    ThreeClubs(Suit.CLUBS, Rank.THREE),
    FourClubs(Suit.CLUBS, Rank.FOUR),
    FiveClubs(Suit.CLUBS, Rank.FIVE),
    SixClubs(Suit.CLUBS, Rank.SIX),
    SevenClubs(Suit.CLUBS, Rank.SEVEN),
    EightClubs(Suit.CLUBS, Rank.EIGHT),
    NineClubs(Suit.CLUBS, Rank.NINE),
    TenClubs(Suit.CLUBS, Rank.TEN),
    JackClubs(Suit.CLUBS, Rank.JACK),
    QueenClubs(Suit.CLUBS, Rank.QUEEN),
    KingClubs(Suit.CLUBS, Rank.KING),
    AceHearts(Suit.HEARTS, Rank.ACE),
    TwoHearts(Suit.HEARTS, Rank.TWO),
    ThreeHearts(Suit.HEARTS, Rank.THREE),
    FourHearts(Suit.HEARTS, Rank.FOUR),
    FiveHearts(Suit.HEARTS, Rank.FIVE),
    SixHearts(Suit.HEARTS, Rank.SIX),
    SevenHearts(Suit.HEARTS, Rank.SEVEN),
    EightHearts(Suit.HEARTS, Rank.EIGHT),
    NineHearts(Suit.HEARTS, Rank.NINE),
    TenHearts(Suit.HEARTS, Rank.TEN),
    JackHearts(Suit.HEARTS, Rank.JACK),
    QueenHearts(Suit.HEARTS, Rank.QUEEN),
    KingHearts(Suit.HEARTS, Rank.KING),
    AceSpades(Suit.SPADES, Rank.ACE),
    TwoSpades(Suit.SPADES, Rank.TWO),
    ThreeSpades(Suit.SPADES, Rank.THREE),
    FourSpades(Suit.SPADES, Rank.FOUR),
    FiveSpades(Suit.SPADES, Rank.FIVE),
    SixSpades(Suit.SPADES, Rank.SIX),
    SevenSpades(Suit.SPADES, Rank.SEVEN),
    EightSpades(Suit.SPADES, Rank.EIGHT),
    NineSpades(Suit.SPADES, Rank.NINE),
    TenSpades(Suit.SPADES, Rank.TEN),
    JackSpades(Suit.SPADES, Rank.JACK),
    QueenSpades(Suit.SPADES, Rank.QUEEN),
    KingSpades(Suit.SPADES, Rank.KING),
    AceDiamonds(Suit.DIAMONDS, Rank.ACE),
    TwoDiamonds(Suit.DIAMONDS, Rank.TWO),
    ThreeDiamonds(Suit.DIAMONDS, Rank.THREE),
    FourDiamonds(Suit.DIAMONDS, Rank.FOUR),
    FiveDiamonds(Suit.DIAMONDS, Rank.FIVE),
    SixDiamonds(Suit.DIAMONDS, Rank.SIX),
    SevenDiamonds(Suit.DIAMONDS, Rank.SEVEN),
    EightDiamonds(Suit.DIAMONDS, Rank.EIGHT),
    NineDiamonds(Suit.DIAMONDS, Rank.NINE),
    TenDiamonds(Suit.DIAMONDS, Rank.TEN),
    JackDiamonds(Suit.DIAMONDS, Rank.JACK),
    QueenDiamonds(Suit.DIAMONDS, Rank.QUEEN),
    KingDiamonds(Suit.DIAMONDS, Rank.KING);

    public final Suit suit;
    public final Rank rank;

    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card getByNumber(Integer number) {
        return Arrays.stream(Card.values())
                .filter(card -> card.getRank().getNumber() == number)
                .findFirst()
                .orElse(null);
    }

    public String prettyPrint() {
        String rank;
        if (this.rank.getNumber() == 1 || this.rank.getNumber() == 11 || this.rank.getNumber() == 12
                || this.rank.getNumber() == 13) {
            rank = this.rank.toString();
        } else {
            rank = String.valueOf(this.rank.getNumber());
        }
        return rank + " of " + suit.toString();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank.getNumber() + " of " + suit.toString().charAt(0);
    }
}
