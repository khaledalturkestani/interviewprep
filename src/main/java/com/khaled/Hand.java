package com.khaled;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by khaledalturkestani on 8/2/17.
 */
public class Hand {
    private Integer[] cards = new Integer[4];

    public Hand(int[] cards) throws Exception {
        if (cards == null)
            throw new Exception("Null cards array");

        if (cards.length != 4)
            throw new Exception("Cards array must be of length 4");

        for (int i = 0; i < cards.length; i++) {
            if (cards[i] < 1 || cards[i] > 10)
                throw new Exception("Cards must be in the range [1, 10]");
            this.cards[i] = cards[i];
        }

        Arrays.sort(this.cards, Collections.reverseOrder());
    }

    public static void main(String[] args) throws Exception {
        int arr[] = {1, 1, 1,1 };
        Hand hand = new Hand(arr);
    }
}


class Solution {
    public static class Hand {
        private Integer[] cards = new Integer[4];

        public Hand(int[] cards) throws Exception {
            if (cards == null)
                throw new Exception("Null cards array");

            if (cards.length != 4)
                throw new Exception("Cards array must be of length 4");

            for (int i = 0; i < cards.length; i++) {
                if (cards[i] < 1 || cards[i] > 10)
                    throw new Exception("Cards must be in the range [1, 10]");
                this.cards[i] = cards[i];
            }

            Arrays.sort(this.cards, Collections.reverseOrder());
        }

        public Integer[] getCards() {
            return this.cards;
        }
    }

    public enum HandCategory {
        FOUR_OF_A_KIND,
        THREE_OF_A_KIND,
        TWO_PAIRS,
        ONE_PAIR,
        HIGH_CARD;
    }

    public static HandCategory getHandCategory(Hand h) {
        int[] counts = new int[10];
        int numPairs = 0;
        Integer[] cards = h.getCards();
        int i = 1;

        for (i= 1; i < cards.length; i++) {
            counts[cards[i]-1]++;
        }

        for (int j = 0; j < counts.length; j++) {
            if (counts[j] == 4)
                return HandCategory.FOUR_OF_A_KIND;
            if (counts[j] == 3)
                return HandCategory.THREE_OF_A_KIND;
            if (counts[j] == 2)
                numPairs++;
        }

        if (numPairs == 2)
            return HandCategory.TWO_PAIRS;

        if (numPairs == 1)
            return HandCategory.ONE_PAIR;

        return HandCategory.HIGH_CARD;
    }

    // int[0] is value of triple. int[1] is other card
    public static int[] getThreeOfAKind(Integer[] cards) {
        int[] result = new int[2];
        if (cards[1] != cards[0]) {
            result[0] = cards[1];
            result[1] = cards[0];
        } else {
            result[0] = cards[0];
            result[1] = cards[3];
        }
        return result;
    }

    // int[0] is value of pair.
    // int[1] is value of higher card.
    // int[2] is value of lower card.
    public static int[] getSinglePair(Integer[] cards) {
        int[] result = new int[3];
        if (cards[0] == cards[1]) {
            result[0] = cards[0];
            result[1] = (cards[2] > cards[3]) ? cards[2] : cards[3];
            result[2] = (cards[2] < cards[3]) ? cards[2] : cards[3];
        } else if (cards[1] == cards[2]) {
            result[0] = cards[1];
            result[1] = (cards[0] > cards[3]) ? cards[0] : cards[3];
            result[2] = (cards[0] < cards[3]) ? cards[0] : cards[3];
        } else {
            result[0] = cards[2];
            result[1] = (cards[0] > cards[1]) ? cards[0] : cards[1];
            result[2] = (cards[0] < cards[1]) ? cards[0] : cards[1];
        }
        return result;
    }


    public static int compareHands(Hand h1, Hand h2) {

        HandCategory hc1 = getHandCategory(h1);
        HandCategory hc2 = getHandCategory(h2);
        if (hc1.ordinal() < hc2.ordinal())
            return 1;

        if (hc1.ordinal() > hc2.ordinal())
            return -1;

        switch (hc1) {
            case FOUR_OF_A_KIND:
                if (h1.getCards()[0] > h2.getCards()[0])
                    return 1;
                if (h1.getCards()[0] < h2.getCards()[0])
                    return -1;
                return 0;
            case THREE_OF_A_KIND:
                int[] h1Triple = getThreeOfAKind(h1.getCards());
                int[] h2Triple = getThreeOfAKind(h2.getCards());
                if (h1Triple[0] > h2Triple[0])
                    return 1;
                if (h1Triple[0] < h2Triple[0])
                    return -1;
                if (h1Triple[1] > h2Triple[1])
                    return 1;
                if (h1Triple[1] < h2Triple[1])
                    return -1;
                return 0;

            case TWO_PAIRS:
                if (h1.getCards()[0] > h2.getCards()[0])
                    return 1;
                if (h1.getCards()[0] < h2.getCards()[0])
                    return -1;
                if (h1.getCards()[3] > h2.getCards()[3])
                    return 1;
                if (h1.getCards()[3] < h2.getCards()[3])
                    return -1;
                return 0;
            case ONE_PAIR:
                int[] p1Detail = getSinglePair(h1.getCards());
                int[] p2Detail = getSinglePair(h2.getCards());
                if (p1Detail[0] > p2Detail[0])
                    return 1;
                if (p1Detail[0] < p2Detail[0])
                    return -1;
                if (p1Detail[1] > p2Detail[1])
                    return 1;
                if (p1Detail[1] < p2Detail[1])
                    return -1;
                if (p1Detail[2] > p2Detail[2])
                    return 1;
                if (p1Detail[2] < p2Detail[2])
                    return -1;
                return 0;
            case HIGH_CARD:
            default:
                for (int i =0; i < h1.getCards().length; i++) {
                    if (h1.getCards()[i] > h2.getCards()[i])
                        return 1;
                    if (h1.getCards()[i] < h2.getCards()[i])
                        return -1;
                }
                return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        int[] arr1 = {1, 2, 4, 3};
        Hand h1 = new Hand(arr1);
        int[] arr2 = {2, 4, 6, 9};
        Hand h2 = new Hand(arr2);

        System.out.println("Compare rusult: " + compareHands(h1, h2));

        // ArrayList<String> strings = new ArrayList<String>();
        // strings.add("Hello, World!");
        // strings.add("Welcome to CoderPad.");
        // strings.add("This pad is running Java 8.");

        // for (String string : strings) {
        //   System.out.println(string);
        // }
    }
}


/*
Your previous Plain Text content is preserved below:

Simplified Poker Rank
---------------------

GIVENS
------
1. You are given a deck of 50 cards that has numbered
   cards with ranks from 1 to 10 (5 of each card.)

2. A poker game is played using this deck between two
   players.  Each poker hand will consist of 4 cards.

   The following are possible poker hands from lowest ranked to highest:

   - High card (no pairs)
   - One pair
   - Two pairs (higher pair is most significant)
   - Three of a kind
   - Four of a kind

   If two hands are equal in terms of the above high-level poker hands,
   then the remaining cards will dictate the winner.

   For example, 3656 vs 6693.  Both hands have a single pair of 6's.
   However, after the pair of 6's, we have the first hand with a 53 and
   the second hand with a 93, thus the second hand is the winner.

   If there are any questions regarding how hands should be compared,
   please consult with the moderator.


CHALLENGE
---------

Your challenge is to create a simulator for a single hand of this
simplified poker game, which first deals hands to two players from the
deck. And, then determines which player is the winner (or if there is
a draw).  The design of this is completely up to you.  You have full
control over how you would like to represent data and also how to
present results of the hand.


ADDITIONAL COMPARISON EXAMPLES
------------------------------

4249 >  3132
4446 <  5553
4554 == 5544
9911 >  8877
 */