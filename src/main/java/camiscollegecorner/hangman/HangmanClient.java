package camiscollegecorner.hangman;

import java.util.List;

/** An interface specifying a Hangman game. */
public interface HangmanClient {

	/** Returns the secret phrase. */
	String getSecretPhrase();

	/** Returns the secret phrase as should be displayed to the client. All spaces in the secret phrase should be
	 * preserved. All characters that have yet to be guessed correctly by the client should be replaced with
	 * character '_'. For example, if the secret phrase is "bananas and peas" and the client has not guessed any
	 * characters correctly, this method should return "_______ ___ ____". Additionally, if the secret phrase is "apple
	 * sauce is good" and the client has correctly guessed characters 'p' and 's', this method should return "_pp__
	 * s____ _s ____".  */
	String getSecretPhraseAsDisplayed();

	/** Returns the maximum health of the "hanging man". This is equivalent to the number of incorrect guesses the
	 * client can exhaust before the game concludes in a loss. */
	int getMaxHealth();

	/** Returns the current health of the "hanging man". This is equivalent to the number of incorrect guesses the
	 * client has already exhausted. */
	int getCurrentHealth();

	/** Returns a list of all of the previously guessed characters. */
	List<Character> getAllGuesses();

	/** Simulates a guess being placed by the client. */
	boolean guess(char c);
}