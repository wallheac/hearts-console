package com.designwork.cardgame.card;

import java.util.Arrays;

public enum Card {
    AceClubs(Suit.CLUBS, Rank.ACE, "/icons/AofC.jpg"),
    TwoClubs(Suit.CLUBS, Rank.TWO, "/icons/2ofC.jpg"),
    ThreeClubs(Suit.CLUBS, Rank.THREE, "/icons/3ofC.jpg"),
    FourClubs(Suit.CLUBS, Rank.FOUR, "/icons/4ofC.jpg"),
    FiveClubs(Suit.CLUBS, Rank.FIVE, "/icons/5ofC.jpg"),
    SixClubs(Suit.CLUBS, Rank.SIX, "/icons/6ofC.jpg"),
    SevenClubs(Suit.CLUBS, Rank.SEVEN, "/icons/7ofC.jpg"),
    EightClubs(Suit.CLUBS, Rank.EIGHT, "/icons/8ofC.jpg"),
    NineClubs(Suit.CLUBS, Rank.NINE, "/icons/9ofC.jpg"),
    TenClubs(Suit.CLUBS, Rank.TEN, "/icons/10ofC.jpg"),
    JackClubs(Suit.CLUBS, Rank.JACK, "/icons/JofC.jpg"),
    QueenClubs(Suit.CLUBS, Rank.QUEEN, "/icons/QofC.jpg"),
    KingClubs(Suit.CLUBS, Rank.KING, "/icons/KofC.jpg"),
    AceHearts(Suit.HEARTS, Rank.ACE, "/icons/AofH.jpg"),
    TwoHearts(Suit.HEARTS, Rank.TWO, "/icons/2ofH.jpg"),
    ThreeHearts(Suit.HEARTS, Rank.THREE, "/icons/3ofH.jpg"),
    FourHearts(Suit.HEARTS, Rank.FOUR, "/icons/4ofH.jpg"),
    FiveHearts(Suit.HEARTS, Rank.FIVE, "/icons/5ofH.jpg"),
    SixHearts(Suit.HEARTS, Rank.SIX, "/icons/6ofH.jpg"),
    SevenHearts(Suit.HEARTS, Rank.SEVEN, "/icons/7ofH.jpg"),
    EightHearts(Suit.HEARTS, Rank.EIGHT, "/icons/8ofH.jpg"),
    NineHearts(Suit.HEARTS, Rank.NINE, "/icons/9ofH.jpg"),
    TenHearts(Suit.HEARTS, Rank.TEN, "/icons/10ofH.jpg"),
    JackHearts(Suit.HEARTS, Rank.JACK, "/icons/JofH.jpg"),
    QueenHearts(Suit.HEARTS, Rank.QUEEN, "/icons/QofH.jpg"),
    KingHearts(Suit.HEARTS, Rank.KING, "/icons/KofH.jpg"),
    AceSpades(Suit.SPADES, Rank.ACE, "/icons/AofS.jpg"),
    TwoSpades(Suit.SPADES, Rank.TWO, "/icons/2ofS.jpg"),
    ThreeSpades(Suit.SPADES, Rank.THREE, "/icons/3ofS.jpg"),
    FourSpades(Suit.SPADES, Rank.FOUR, "/icons/4ofS.jpg"),
    FiveSpades(Suit.SPADES, Rank.FIVE, "/icons/5ofS.jpg"),
    SixSpades(Suit.SPADES, Rank.SIX, "/icons/6ofS.jpg"),
    SevenSpades(Suit.SPADES, Rank.SEVEN, "/icons/7ofS.jpg"),
    EightSpades(Suit.SPADES, Rank.EIGHT, "/icons/8ofS.jpg"),
    NineSpades(Suit.SPADES, Rank.NINE, "/icons/9ofS.jpg"),
    TenSpades(Suit.SPADES, Rank.TEN, "/icons/10ofS.jpg"),
    JackSpades(Suit.SPADES, Rank.JACK, "/icons/JofS.jpg"),
    QueenSpades(Suit.SPADES, Rank.QUEEN, "/icons/QofS.jpg"),
    KingSpades(Suit.SPADES, Rank.KING, "/icons/KofS.jpg"),
    AceDiamonds(Suit.DIAMONDS, Rank.ACE, "/icons/AofD.jpg"),
    TwoDiamonds(Suit.DIAMONDS, Rank.TWO, "/icons/2ofD.jpg"),
    ThreeDiamonds(Suit.DIAMONDS, Rank.THREE, "/icons/3ofD.jpg"),
    FourDiamonds(Suit.DIAMONDS, Rank.FOUR, "/icons/4ofD.jpg"),
    FiveDiamonds(Suit.DIAMONDS, Rank.FIVE, "/icons/5ofD.jpg"),
    SixDiamonds(Suit.DIAMONDS, Rank.SIX, "/icons/6ofD.jpg"),
    SevenDiamonds(Suit.DIAMONDS, Rank.SEVEN, "/icons/7ofD.jpg"),
    EightDiamonds(Suit.DIAMONDS, Rank.EIGHT, "/icons/8ofD.jpg"),
    NineDiamonds(Suit.DIAMONDS, Rank.NINE, "/icons/9ofD.jpg"),
    TenDiamonds(Suit.DIAMONDS, Rank.TEN, "/icons/10ofD.jpg"),
    JackDiamonds(Suit.DIAMONDS, Rank.JACK, "/icons/JofD.jpg"),
    QueenDiamonds(Suit.DIAMONDS, Rank.QUEEN, "/icons/QofD.jpg"),
    KingDiamonds(Suit.DIAMONDS, Rank.KING, "/icons/KofD.jpg");

    public final Suit suit;
    public final Rank rank;
    public final String iconId;

    Card(Suit suit, Rank rank, String iconId) {
        this.suit = suit;
        this.rank = rank;
        this.iconId = iconId;
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

    public String getIconId() {
        return iconId;
    }

    @Override
    public String toString() {
        return rank.getNumber() + " of " + suit.toString().charAt(0);
    }
}
